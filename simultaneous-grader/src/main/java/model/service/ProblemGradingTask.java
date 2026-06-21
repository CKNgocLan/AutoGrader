package model.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import common.constant.CompilationConfigure;
import common.constant.Constants;
import common.constant.FileExtension;
import common.constant.csv.ProblemResultHeader;
import common.message.GradingMessage;
import common.util.PathUtils;
import common.util.ReportUtils;
import model.component.Student;
import model.component.StudentList;
import model.component.TestCase;
import model.component.testSuite.TestSuite;
import model.component.testSuite.TestSuiteFactory;
import model.exception.CompilationErrorException;
import model.resultReport.ProblemResultDetails;
import model.resultReport.TestCaseResult;

public class ProblemGradingTask implements Callable<ProblemResultDetails> {
	private File directory;
	private TestSuite testSuite;
	private Student student;
	private String topicName;
	private String problemName;
	private double weight;

	public ProblemGradingTask(File problemDir, TestSuiteFactory testSuiteFactory, double weight) {
		this.directory = problemDir;
		this.testSuite = testSuiteFactory.getTestSuite();
		this.student = StudentList.findByStudentDirectory(this.directory.getParentFile());
		this.topicName = testSuiteFactory.getTopic();
		this.problemName = testSuiteFactory.getProblem();
		this.weight = weight;
	}

	@Override
	public ProblemResultDetails call() {
		List<TestCaseResult> results;
		try {
			results = gradeTestCases();
		} catch (CompilationErrorException e) {
//			return new ProblemResultDetails(problemName, student, 0, weight, GradingMessage.COMPILATION_ERROR.getContent());
			return ProblemResultDetails.compilationError(problemName, student);
		} catch (Exception e) {
			return ProblemResultDetails.exception(problemName, student, e);
		}

		// TODO Save results into Excel file
		saveResultAsExcel(results);

		// TODO Save results into CSV file
		// saveResultAsCSV(results.stream().map(result -> result.toCSVRow()).toList());
		
		return new ProblemResultDetails(problemName, student, results == null || results.isEmpty() ?
				0 : (results.stream().filter(result -> result.passed() != null && result.passed()).toList().size() / results.size()) * 100, weight);
	}

	private List<TestCaseResult> gradeTestCases() throws CompilationErrorException {
		System.out.println("Grading Test Cases \"%s\" of %s".formatted(this.directory.getName(), student.fullName()));

		// Step 1: Compile all student's .java files
		if (!combineJavaFiles()) {
			throw new CompilationErrorException();
		}

		// Step 2: Retrieve test cases
		List<TestCase> testCases = retrieveTestCases();
		if (testCases == null || testCases.isEmpty()) {
			return List.of();
		}

		// Step 3: Run test cases
		return runTestCases(testCases);
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

			ProcessBuilder pb = new ProcessBuilder();
			List<String> cmd = new ArrayList<>();
			cmd.add("javac");
			cmd.add(CompilationConfigure.JAVAC_J_XMX128);
			cmd.add("-d");
			cmd.add(PathUtils.targetClasses());
			cmd.addAll(javaFiles);
			pb.command(cmd);
			pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			pb.redirectError(ProcessBuilder.Redirect.INHERIT);

			Process process = pb.start();
			int exitCode = process.waitFor();

			if (exitCode != 0) {
				GradingMessage.COMPILATION_ERRORS_WITH_MESSAGE.printErrorContent(Constants.EMPTY_STRING);
				try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
					String line;
					while ((line = br.readLine()) != null) {
						System.err.println(Constants.SPACE.repeat(5) + line);
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
	private void saveResultAsExcel(List<TestCaseResult> results) {
		ReportUtils.generateProblemReportToExcel(student, topicName, problemName, results);
	}

	/**
	 * Step 5: Save result into CSV file
	 */
	private void saveResultAsCSV(List<String> resultRow) {
		ReportUtils.generateEachProblemResultToCSV(directory, ProblemResultHeader.getCSVHeaders(), resultRow);
	}
}
