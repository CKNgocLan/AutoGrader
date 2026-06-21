package model.service;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import common.constant.Constants;
import common.constant.ProblemName;
import common.constant.TopicName;
import common.message.ProgressMessage;
import common.util.ReportUtils;
import common.util.StringUtils;
import common.util.ValueUtils;
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
	private List<String> problemList;
	private HashMap<String, TestSuiteFactory> factoryMapper = new HashMap<String, TestSuiteFactory>();

	public StudentThreadPool(String topic, File studentDir) {
		this.topic = topic;
		this.directory = studentDir;

		this.student = StudentList.findByStudentDirectory(studentDir);
		this.service = Executors.newFixedThreadPool(Constants.THREAD_POOL_SIZE);
		this.taskList = new ArrayList<ProblemGradingTask>();
		this.factoryMapper = TestSuiteFactoryMapper.getFactoryMapper(topic);
		this.problemList = ProblemName.getProblems(topic);
	}

	public List<ProblemResultDetails> submit() throws NoSuchElementException {
		List<ProblemResultDetails> resultList = new ArrayList<ProblemResultDetails>();
		HashMap<String, ProblemResultDetails> resultMapper = new HashMap<String, ProblemResultDetails>();
		for (String problemName : problemList) {
			File matchedFile = matchProblemDirectory(problemName);

			if (matchedFile == null) {
				resultMapper.put(problemName, ProblemResultDetails.notFoundSubmission(problemName, student));
				continue;
			}

			this.taskList.add(new ProblemGradingTask(matchedFile
					, factoryMapper.get(problemName)
					, TestSuiteFactoryMapper.getWeights(topic).get(problemName)));
		}

		List<Future<ProblemResultDetails>> futureList = taskList.stream().map(task -> service.submit(task)).toList();
		while (futureList.stream().filter(future -> !future.isDone()).toList().size() > 0) {}

		System.out.println(ProgressMessage.GRADING_COMPLETE.getContent(student.fullName()));

		try {
			for (Future<ProblemResultDetails> future : futureList) {
				resultMapper.put(future.get().getName(), future.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (!this.service.isShutdown()) {
				this.service.shutdown();
			}
		}

		saveResultAsCSV(resultMapper);

		return resultList;
	}

	private File matchProblemDirectory(String problemName) {
		return Stream.of(directory.listFiles()).filter(probDir -> probDir.isDirectory()
				&& StringUtils.compareAsLowerCaseNoSpace(probDir.getName(), problemName)).findFirst()
				.orElse(null);
	}

	private void saveResultAsCSV(HashMap<String, ProblemResultDetails> resultMapper) {
		List<Object> dataRow = new ArrayList<>();
		dataRow.add(student.number());
		dataRow.add(student.fullName());

		// total
		dataRow.add(ValueUtils.roundDouble(resultMapper.values().stream().mapToDouble(details -> details.getPassedPercent() * details.getWeight()).sum()));

		// problem
		for (String problem : problemList) {
			resultMapper.entrySet().stream()
					.filter(entry -> entry.getKey().contains(problem))
					.forEach(entry -> {
						dataRow.add(entry.getValue().getPassedPercent());
						dataRow.add(entry.getValue().getNote());
					});
		}

		ReportUtils.writeStudentResultToCSV(directory.getParentFile(), topic, student,
				ReportUtils.convertToCsvRow(dataRow.stream().toArray()));
	}

	@Deprecated
	private void saveResultAsCSV(List<ProblemResultDetails> problemResultList) {
		List<Object> dataRow = new ArrayList<>();
		dataRow.add(student.number());
		dataRow.add(student.fullName());

		// total
		dataRow.add(problemResultList.stream().mapToDouble(details -> details.getPassedPercent() * details.getWeight())
				.sum());

		String[] headerArray = TopicName.problemHeaderArray(topic);
		for (ProblemResultDetails problemResult : problemResultList) {
			dataRow.add(problemResult.getPassedPercent());
			dataRow.add(problemResult.getNote());
		}

		ReportUtils.writeStudentResultToCSV(directory.getParentFile(), topic, student,
				ReportUtils.convertToCsvRow(dataRow.stream().toArray()));
	}
}
