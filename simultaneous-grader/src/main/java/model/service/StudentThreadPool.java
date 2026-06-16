package model.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import common.constant.Constants;
import common.message.ProgressMessage;
import model.component.Student;
import model.component.StudentList;
import model.component.testSuite.TestSuiteFactory;
import model.resultReport.ProblemResult;

/*
 * Student Thread Pool
 */
public class StudentThreadPool {
	private Student student;
	private ExecutorService service;
	private File directory;
	private String topic;
	private List<ProblemGradingTask> taskList;

	public StudentThreadPool(String topic, File studentDir) {
		this.topic = topic;
		this.student = StudentList.findByStudentDirectory(studentDir);
		this.directory = studentDir;
		this.service = Executors.newFixedThreadPool(Constants.THREAD_POOL_SIZE);
		this.taskList = new ArrayList<ProblemGradingTask>();
	}

	public String getTopic() {
		return this.topic;
	}

	public void addTask(ProblemGradingTask task) {
		this.taskList.add(task);
	}

	public void addTask(TestSuiteFactory testSuiteFactory) throws NoSuchElementException {
		File problemDirectory = Stream.of(directory.listFiles()).filter(probDir -> probDir.isDirectory() && probDir.getName().equals(this.topic)).findFirst().orElseThrow();
		testSuiteFactory.createTestSuite();
	}

	public List<ProblemResult> submit() {
		List<Future<ProblemResult>> futureList = taskList.stream().map(task -> service.submit(task)).toList();
		
		while(futureList.stream().filter(future -> !future.isDone()).toList().size() > 0) {
		}
		System.out.println(ProgressMessage.GRADING_COMPLETE.getContent());

		List<ProblemResult> resultList = new ArrayList<ProblemResult>();
		try {
			for (Future<ProblemResult> future : futureList) {
				resultList.add(future.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		this.service.shutdown();
		return resultList;
	}
}
