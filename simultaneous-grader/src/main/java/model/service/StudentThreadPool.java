package model.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import common.constant.Constants;
import common.constant.ProblemName;
import common.constant.csv.StudentHeader;
import common.constant.csv.TopicResultHeader;
import common.message.ProgressMessage;
import common.util.ReportUtils;
import common.util.StringUtils;
import model.component.Student;
import model.component.StudentList;
import model.component.testSuite.TestSuiteFactory;
import model.resultReport.ProblemResultDetails;

/*
 * Student Thread Pool
 */
public class StudentThreadPool {
	private Student student;
	private ExecutorService service;
	private File directory;
	private String topic;
	private List<ProblemGradingTask> taskList;
	private HashMap<String, TestSuiteFactory> factoryMapper = new HashMap<String, TestSuiteFactory>();

	public StudentThreadPool(String topic, File studentDir, HashMap<String, TestSuiteFactory> factoryMapper) {
		this.topic = topic;
		this.student = StudentList.findByStudentDirectory(studentDir);
		this.directory = studentDir;
		this.service = Executors.newFixedThreadPool(Constants.THREAD_POOL_SIZE);
		this.taskList = new ArrayList<ProblemGradingTask>();
		this.factoryMapper = factoryMapper;
	}

	public String getTopic() {
		return this.topic;
	}

	public void addTask(ProblemGradingTask task) {
		this.taskList.add(task);
	}

	public StudentThreadPool putTestSuiteFactory(String problemName, TestSuiteFactory testSuiteFactory) {
		this.factoryMapper.put(problemName, testSuiteFactory);
		return this;
	}

	private void addTaskThroughFactory() throws NoSuchElementException {
		for(String problemName: ProblemName.getProblems(topic)) {
			this.taskList.add(new ProblemGradingTask(matchProblemDirectory(problemName), factoryMapper.get(problemName)));
		}
	}

	private File matchProblemDirectory(String problemName) {
		return Stream.of(directory.listFiles()).filter(probDir -> probDir.isDirectory() && probDir.getName().equals(problemName)).findFirst().orElseThrow();
	}

	public List<ProblemResultDetails> submit() {
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

	private void createTopicResultCSVFile(List<ProblemResultDetails> problemResultList) {
		List<String> headerRow = new ArrayList<>();
		headerRow.add(StudentHeader.ID_NUMBER);
		headerRow.add(StudentHeader.FULL_NAME);
		for (ProblemResultDetails problemResult : problemResultList) {
			headerRow.add(problemResult.name());
		}
		headerRow.add(TopicResultHeader.AVERAGE);
		ReportUtils.generateAllStudentResultsToCSV(directory.getParentFile()
				, topic
				, student
				, headerRow.stream().toArray(String[]::new)
				, null
		);
	}

	private void saveResultAsCSV(List<ProblemResultDetails> problemResultList) {
		createTopicResultCSVFile(problemResultList);
		List<String> headerRow = new ArrayList<>();
		headerRow.add(StudentHeader.ID_NUMBER);
		headerRow.add(StudentHeader.FULL_NAME);

		List<Object> dataRow = new ArrayList<>();
		dataRow.add(student.idNumber());
		dataRow.add(student.fullName());

		float totalPercentage = 0;
		
		for (ProblemResultDetails problemResult : problemResultList) {
			totalPercentage += problemResult.passedPercent();
			headerRow.add(problemResult.name());
			dataRow.add(problemResult.passedPercent());
		}
		headerRow.add(TopicResultHeader.AVERAGE);
		dataRow.add(Float.valueOf(totalPercentage/problemResultList.size()));

		// TODO write append passed percent
		ReportUtils.generateAllStudentResultsToCSV(directory.getParentFile()
				, topic
				, student
				, null // headerRow.stream().toArray(String[]::new)
				, ReportUtils.convertToCsvRow(dataRow.stream().toArray())
		);
	}
}
