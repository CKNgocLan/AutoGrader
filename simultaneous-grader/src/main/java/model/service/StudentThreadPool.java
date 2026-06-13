package model.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import common.constant.Constants;
import model.component.Student;
import model.component.StudentList;
import model.resultReport.ProblemResult;

/*
 * Student Thread Pool
 */
public class StudentThreadPool {
	private Student student;
	private ExecutorService service;
	private String topic;
	private List<ProblemGradingTask> taskList;

	public StudentThreadPool(String topic, File studentDir) {
		this.topic = topic;
		this.student = StudentList.findByStudentDirectory(studentDir);
		this.service = Executors.newFixedThreadPool(Constants.THREAD_POOL_SIZE);
		this.taskList = new ArrayList<ProblemGradingTask>();
	}

	public void addTask(ProblemGradingTask task) {
		this.taskList.add(task);
	}

	public List<ProblemResult> submit() {
		List<Future<ProblemResult>> futureList = taskList.stream().map(task -> service.submit(task)).toList();
		
		while(futureList.stream().filter(future -> !future.isDone()).toList().size() > 0) {
		}
		System.out.println("Grading Complete!");

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
