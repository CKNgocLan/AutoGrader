package model.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import common.constant.Constants;
import common.constant.ProblemName;
import common.message.ExceptionMessage;
import common.message.ProgressMessage;
import common.util.ReportUtils;
import common.util.StringUtils;
import model.component.Student;
import model.component.StudentList;
import model.component.testSuite.TestSuiteFactory;
import model.exception.NotFoundProblemSubmissionException;
import model.resultReport.ProblemResultDetails;
import model.service.mapper.TestSuiteFactoryMapper;

/**
 * Student Thread Pool
 */
public class StudentThreadPool {
	private Student student;
	private ExecutorService service;
	private File directory;
	private String topic;
	private List<ProblemGradingTask> taskList;
	private HashMap<String, TestSuiteFactory> factoryMapper = new HashMap<String, TestSuiteFactory>();

	public StudentThreadPool(String topic, File studentDir) {
		this.topic = topic;
		this.directory = studentDir;

		this.student = StudentList.findByStudentDirectory(studentDir);
		this.service = Executors.newFixedThreadPool(Constants.THREAD_POOL_SIZE);
		this.taskList = new ArrayList<ProblemGradingTask>();
		this.factoryMapper = TestSuiteFactoryMapper.getFactoryMapper(topic);
	}

//	public String getTopic() {
//		return this.topic;
//	}
//
//	public void addTask(ProblemGradingTask task) {
//		this.taskList.add(task);
//	}

	public List<ProblemResultDetails> submit() throws NoSuchElementException, NotFoundProblemSubmissionException {
		addTaskThroughFactory();
		List<Future<ProblemResultDetails>> futureList = taskList.stream().map(task -> service.submit(task)).toList();
		
		while(futureList.stream().filter(future -> !future.isDone()).toList().size() > 0) {
		}
		System.out.println(ProgressMessage.GRADING_COMPLETE.getContent(StringUtils.encloseDoubleQuote(student.fullName())));

		List<ProblemResultDetails> resultList = new ArrayList<ProblemResultDetails>();
		try {
			for (Future<ProblemResultDetails> future : futureList) {
				resultList.add(future.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		this.service.shutdown();

		saveResultAsCSV(resultList);

		return resultList;
	}

	private void addTaskThroughFactory() throws NoSuchElementException, NotFoundProblemSubmissionException {
		for(String problemName: ProblemName.getProblems(topic)) {
			this.taskList.add(new ProblemGradingTask(matchProblemDirectory(problemName), factoryMapper.get(problemName)));
		}
	}

	private File matchProblemDirectory(String problemName) throws NotFoundProblemSubmissionException {
		return Stream.of(directory.listFiles())
				.filter(probDir -> probDir.isDirectory() && probDir.getName().equals(problemName)).findFirst()
				.orElseThrow(NotFoundProblemSubmissionException.toSupplier(student, problemName));
	}

	private void saveResultAsCSV(List<ProblemResultDetails> problemResultList) {
		List<Object> dataRow = new ArrayList<>();
		dataRow.add(student.number());
		dataRow.add(student.fullName());
		dataRow.add(problemResultList.stream().mapToDouble(details -> details.passedPercent()).average().getAsDouble());

		for (ProblemResultDetails problemResult : problemResultList) {
			dataRow.add(problemResult.passedPercent());
		}

		ReportUtils.writeStudentResultToCSV(directory.getParentFile()
				, topic
				, student
				, ReportUtils.convertToCsvRow(dataRow.stream().toArray())
		);
	}
}
