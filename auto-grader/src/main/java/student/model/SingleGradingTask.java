package student.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import student.TestSuiteRouter;
import student.constant.Constants;
import student.constant.ExceptionMessage;
import student.constant.FileExtension;
import student.constant.GradingMessage;
import student.constant.TestCaseResult;
import student.util.PathUtils;

public class SingleGradingTask implements IRunnableSingleGradingTask {
	private JTextArea logArea;
	private File directory;
    private TestSuiteRouter testSuiteRouter;
    private String lab;
//    private String selectedQuestion;

    @Override
    public void run() {
//    	System.out.println(submissionDirectory.getName());
    	for (File subDirectory : directory.listFiles()) {
//    		System.out.println(MessageFormat.format("---{0}", subSubmissionDirectory.getAbsolutePath()));
    		
    		// Step 1: Compile all student's .java files
    		boolean compileSuccess = compileStudentFiles(subDirectory);
            if (!compileSuccess) {
            	GradingMessage.COMPILATION_FAILED.printErrorContent();
				Thread.currentThread().interrupt();
				return;
            }
			GradingMessage.FOLDER_COMPILATION_SUCCESSFUL.printContent(subDirectory.getAbsolutePath());

			// Step 2: Run tests
			List<ITestCase> tests = testSuiteRouter.invokeAllTests(lab, subDirectory.getName());
			if (tests == null) {
				GradingMessage.ERROR_CHECK_TERMINAL.printErrorContent();
				Thread.currentThread().interrupt();
				return;
			} else if (tests.isEmpty()) {
				GradingMessage.TEST_SUITE_UNDER_CONSTRUCTION.printContent();
				Thread.currentThread().interrupt();
				return;
			}
			
			GradingMessage.RUNNING_TEST_CASES_NEWLINE.printContent();
            List<Integer> scores = new ArrayList<>();
            List<Boolean> passedList = new ArrayList<>();
            List<TestResult> results = new ArrayList<>();

            for (ITestCase test : tests) {
//                log("→ " + test.getName() + " (" + test.getPoints() + " pts) ... ");
//                log("→ " + test.getName() + " ... ");
            	System.out.println("→ " + test.getName() + " ... ");
                boolean passed = test.runTest();
                int points = passed ? test.getPoints() : 0;
                scores.add(points);
                if (passed) {
                	passedList.add(passed);
                }

                System.out.println(passed ? TestCaseResult.PASSED : TestCaseResult.FAILED);
                results.add(new TestResult(test.getName(), test.getPoints(), passed ? test.getPoints() : 0, passed, test.getFeedback()));
            }
            int totalScore = scores.stream().mapToInt(Integer::intValue).sum();
    	}
    }

    /*
     * constructor ===============
     */
	private SingleGradingTask(File submissionDirectory) {
		if (!submissionDirectory.isDirectory()) {
			throw new IllegalArgumentException(ExceptionMessage.INVALID_DIRECTORY.getContent(submissionDirectory.getAbsolutePath()));
		}

		this.directory = submissionDirectory;
		Thread.currentThread().setName(submissionDirectory.getName());
	}

	private SingleGradingTask(String submissionPath) {
		this(new File(submissionPath));
	}

	private SingleGradingTask(Builder builder) {
		this.directory = builder.directory;
		this.lab = builder.lab;
		this.testSuiteRouter = builder.testSuiteRouter;
	}

	/*
	 * builder
	 */
	public static class Builder {
		private File directory;
	    private TestSuiteRouter testSuiteRouter;
	    private String lab;

	    public Builder() {}

	    public Builder(TestSuiteRouter testSuiteRouter) {
	    	this.testSuiteRouter = testSuiteRouter;
	    }

		public Builder testSuiteRouter(TestSuiteRouter testSuiteRouter) {
			this.testSuiteRouter = testSuiteRouter;
			return this;
		}

		public Builder directory(File directory) {
			this.directory = directory;
			return this;
		}

		public Builder lab(String lab) {
			this.lab = lab;
			return this;
		}

		public SingleGradingTask build() {
			return new SingleGradingTask(this);
		}
	}

	/*
	 * logArea ===============
	 */
	public SingleGradingTask logArea(JTextArea logArea) {
		this.logArea = logArea;
		return this;
	}

//    private void log(String message) {
//        SwingUtilities.invokeLater(() -> {
//            logArea.append(message + System.lineSeparator());
//            logArea.setCaretPosition(logArea.getDocument().getLength());
//        });
//    }

    /**
     * Compile all .java files in the selected folder
     * @param folder
     * @return
     */
    private boolean compileStudentFiles(File folder) {
        try {
            List<String> javaFiles = new ArrayList<>();
            Files.walk(folder.toPath())
                 .filter(p -> p.toString().endsWith(FileExtension.JAVA))
                 .forEach(p -> javaFiles.add(p.toString()));

            if (javaFiles.isEmpty()) {
//                log(GradingMessage.NO_JAVA_FILE_FOUND_IN_FOLDER.getContent(folder.getName()));
            	GradingMessage.NO_JAVA_FILE_FOUND_IN_FOLDER.printErrorContent(folder.getName());
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
//                log(GradingMessage.COMPILATION_ERRORS_WITH_MESSAGE.getContent(Constants.EMPTY_STRING));
            	GradingMessage.COMPILATION_ERRORS_WITH_MESSAGE.printErrorContent(Constants.EMPTY_STRING);
                try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
//                        log("   " + line);
                    	System.err.println("   " + line);
                    }
                }
                return false;
            }
            return true;

        } catch (Exception e) {
//            log(GradingMessage.COMPILATION_ERRORS_WITH_MESSAGE.getContent(e.getMessage()));
            GradingMessage.COMPILATION_ERRORS_WITH_MESSAGE.printErrorContent(e.getMessage());
            return false;
        }
    }
}
