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
	private HashMap<String, TestSuiteFactory> factoryMapper = new HashMap<String, TestSuiteFactory>();

	public StudentThreadPool(String topic, File studentDir) {
		this.topic = topic;
		this.directory = studentDir;

		this.student = StudentList.findByStudentDirectory(studentDir);
		this.service = Executors.newFixedThreadPool(Constants.THREAD_POOL_SIZE);
		this.taskList = new ArrayList<ProblemGradingTask>();
		this.factoryMapper = TestSuiteFactoryMapper.getFactoryMapper(topic);
	}

	public List<ProblemResultDetails> submit() throws NoSuchElementException {
		// addTaskThroughFactory();

		List<ProblemResultDetails> resultList = new ArrayList<ProblemResultDetails>();
		for (String problemName : ProblemName.getProblems(topic)) {
			File matchedFile = matchProblemDirectory(problemName);
			if (matchedFile == null) {
				resultList.add(ProblemResultDetails.notFoundSubmission(problemName, student));
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
				resultList.add(future.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (!this.service.isShutdown()) {
				this.service.shutdown();
			}
		}

		saveResultAsCSV(resultList);

		return resultList;
	}

	@Deprecated
	private void addTaskThroughFactory() throws NoSuchElementException, NotFoundProblemSubmissionException {
		for (String problemName : ProblemName.getProblems(topic)) {
			File matchedFile = matchProblemDirectory(problemName);
			if (matchedFile == null) {
				continue;
			}

			this.taskList.add(new ProblemGradingTask(matchedFile
					, factoryMapper.get(problemName)
					, TestSuiteFactoryMapper.getWeights(topic).get(problemName)));
		}
	}

	private File matchProblemDirectory(String problemName) {
		return Stream.of(directory.listFiles()).filter(probDir -> probDir.isDirectory()
				&& StringUtils.compareAsLowerCaseNoSpace(probDir.getName(), problemName)).findFirst()
				.orElse(null);
//				.orElseThrow(NotFoundProblemSubmissionException.toSupplier(student, problemName));
	}

	private void saveResultAsCSV(List<ProblemResultDetails> problemResultList) {
		List<Object> dataRow = new ArrayList<>();
		dataRow.add(student.number());
		dataRow.add(student.fullName());

		// total
		dataRow.add(problemResultList.stream().mapToDouble(details -> details.getPassedPercent() * details.getWeight())
				.sum());

		String[] headerArray = TopicName.problemHeaderArray(topic);
		for (ProblemResultDetails problemResult : problemResultList) {
//			Stream.of(headerArray).filter(problemHeader -> problemHeader.equals(problemResult.getName()));
			dataRow.add(problemResult.getPassedPercent());
			dataRow.add(problemResult.getNote());
		}

		ReportUtils.writeStudentResultToCSV(directory.getParentFile(), topic, student,
				ReportUtils.convertToCsvRow(dataRow.stream().toArray()));
	}
}
