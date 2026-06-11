package model.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import common.constant.Constants;
import common.constant.FileExtension;
import common.message.GradingMessage;
import common.util.PathUtils;
import model.component.TestCase;
import model.component.testSuite.TestSuite;
import model.component.testSuite.TestSuiteFactory;

public class ProblemGradingTask implements Runnable {
	private File directory;
	private SingleThreadStudentExecutor studentService;
	private TestSuite testSuite;

	public ProblemGradingTask(SingleThreadStudentExecutor studentService) {
		this.studentService = studentService;
	}

	public ProblemGradingTask(File problemDir, TestSuiteFactory testSuiteFactory) {
		this.directory = problemDir;
		this.testSuite = testSuiteFactory.createTestSuite();
	}

	public Thread toThread() {
		return new Thread(this);
	}

	@Override
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
		runTestCases();

		// Step 4: Save results into CSV file
		saveResultsAsCSV();
	}

	/**
	 * Step 1: Compile all student's .java files
	 */
	private boolean combineJavaFiles() {
        try {
            List<String> javaFiles = new ArrayList<>();
            Files.walk(directory.toPath())
                 .filter(p -> p.toString().endsWith(FileExtension.JAVA))
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
                    	System.err.println("   " + line);
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
	private void runTestCases() {
		
	}

	/**
	 * Step 4: Save results into CSV file
	 */
	private void saveResultsAsCSV() {
		
	}
}
