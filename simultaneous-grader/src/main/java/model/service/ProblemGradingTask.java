package model.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import common.constant.Constants;
import common.constant.FileExtension;
import common.constant.ProblemName;
import common.constant.TopicName;
import common.message.GradingMessage;
import common.util.PathUtils;
import common.util.ReportUtils;
import common.util.ThreadServiceUtils;
import model.component.Student;
import model.component.StudentList;
import model.component.TestCase;
import model.component.testSuite.TestSuite;
import model.component.testSuite.TestSuiteFactory;
import model.resultReport.ProblemResult;
import model.resultReport.TestCaseResult;

public class ProblemGradingTask implements Callable<ProblemResult> {
	private File directory;
	private TestSuite testSuite;
	private Student student;
//	private StudentThreadPool studentService;
//
//	public ProblemGradingTask(StudentThreadPool studentService) {
//		this.studentService = studentService;
//	}

	public ProblemGradingTask(File problemDir, TestSuiteFactory testSuiteFactory) {
		this.directory = problemDir;
		this.testSuite = testSuiteFactory.createTestSuite();
		this.student = StudentList.findByStudentDirectory(this.directory.getParentFile());
	}

	@Override
	public ProblemResult call() throws Exception {
		System.out.println("Running Problem Task: %s".formatted(this.directory.getName()));

		// TODO declare grading step order
		// Step 1: Compile all student's .java files
		combineJavaFiles();

		// Step 2: Retrieve test cases
		List<TestCase> testCases = retrieveTestCases();
		if (testCases == null || testCases.isEmpty()) {
			// TODO throw exception when test case list is invalid
			return ThreadServiceUtils.createUnexpectedProblemResult(student);
		}

		// Step 3: Run test cases
		List<TestCaseResult> results = runTestCases(testCases);

		// Step 4: Save results into CSV file
		saveResults(results);
		
		return new ProblemResult(student, results.stream().filter(result -> result.passed()).toList().size(), results);
	}

//	@Override
	public void run() {
		System.out.println("Running Problem Task: %s".formatted(this.directory.getName()));

		// TODO declare grading step order
		// Step 1: Compile all student's .java files
		combineJavaFiles();

		// Step 2: Retrieve test cases
		List<TestCase> testCases = retrieveTestCases();
		if (testCases == null || testCases.isEmpty()) {
			// TODO throw exception when test case list is invalid
			return;
		}

		// Step 3: Run test cases
		List<TestCaseResult> results = runTestCases(testCases);

		// Step 4: Save results into CSV file
		saveResults(results);
	}

	/**
	 * Step 1: Compile all student's .java files
	 */
	private boolean combineJavaFiles() {
		try {
			List<String> javaFiles = new ArrayList<>();
			Files.walk(directory.toPath()).filter(p -> p.toString().endsWith(FileExtension.JAVA.extension()))
					.forEach(p -> javaFiles.add(p.toString()));

			if (javaFiles.isEmpty()) {
				GradingMessage.NO_JAVA_FILE_FOUND_IN_FOLDER.printErrorContent(directory.getName());
				return false;
			}

			// TODO check and remove package statement

			ProcessBuilder pb = new ProcessBuilder();
			List<String> cmd = new ArrayList<>();
			cmd.add("javac");
			cmd.add("-d");
			cmd.add(PathUtils.targetClasses());
			cmd.addAll(javaFiles);
			pb.command(cmd);

			Process process = pb.start();
			int exitCode = process.waitFor();

			if (exitCode != 0) {
				GradingMessage.COMPILATION_ERRORS_WITH_MESSAGE.printErrorContent(Constants.EMPTY_STRING);
				try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
					String line;
					while ((line = br.readLine()) != null) {
						System.err.println(Constants.SPACE.repeat(3) + line);
					}
				}
				return false;
			}

			return true;
		} catch (Exception e) {
			GradingMessage.COMPILATION_ERRORS_WITH_MESSAGE.printErrorContent(e.getMessage());
			return false;
		}
	}

	/**
	 * Step 2: Retrieve test cases
	 */
	private List<TestCase> retrieveTestCases() {
		return this.testSuite.getTestCases();
	}

	/**
	 * Step 3: Run test cases
	 */
	private List<TestCaseResult> runTestCases(List<TestCase> testCases) {
		List<TestCaseResult> results = new ArrayList<TestCaseResult>();
		for (TestCase tc : testCases) {
			if (tc.runTest()) {
				results.add(new TestCaseResult(tc.getName(), tc.getPoints(), tc.getPoints(), true, tc.getFeedback()));
			} else {
				results.add(new TestCaseResult(tc.getName(), tc.getPoints(), 0, false, tc.getFeedback()));
			}
		}

		return results;
	}

	/**
	 * Step 4: Save result into Excel file
	 */
	private void saveResults(List<TestCaseResult> results) {
		ReportUtils.generateExcelReport(directory.getName(), TopicName.L3, ProblemName.P1, results);
	}

	/**
	 * Step 5: Save result into CSV file
	 */
}
