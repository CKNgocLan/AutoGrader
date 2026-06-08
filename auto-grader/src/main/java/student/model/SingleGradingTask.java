package student.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import student.TestSuiteRouter;
import student.constant.Constants;
import student.constant.ExceptionMessage;
import student.constant.FileExtension;
import student.constant.GradingMessage;
import student.util.PathUtils;

public class SingleGradingTask implements IRunnableSingleGradingTask {
	private File submissionDirectory;
    private JTextArea logArea;
    private TestSuiteRouter testSuiteRouter;
    private String selectedLab;
    private String selectedQuestion;

    @Override
    public void run() {
//    	System.out.println(submissionDirectory.getName());
    	for (File subSubmissionDirectory : submissionDirectory.listFiles()) {
//    		System.out.println(MessageFormat.format("---{0}", subSubmissionDirectory.getAbsolutePath()));
    		
    		// Step 1: Compile all student's .java files
    		boolean compileSuccess = compileStudentFiles(subSubmissionDirectory);
            if (!compileSuccess) {
            	GradingMessage.COMPILATION_FAILED.printErrorContent();
				Thread.currentThread().interrupt();
				return;
            }
			GradingMessage.FOLDER_COMPILATION_SUCCESSFUL.printContent(subSubmissionDirectory.getAbsolutePath());

			// Step 2: Run tests
			List<ITestCase> tests = testSuiteRouter.invokeAllTests(selectedLab, selectedQuestion);
    	}
    }

    /*
     * constructor ===============
     */
	public SingleGradingTask(File submissionDirectory) {
		if (!submissionDirectory.isDirectory()) {
			throw new IllegalArgumentException(ExceptionMessage.INVALID_DIRECTORY.getContent(submissionDirectory.getAbsolutePath()));
		}

		this.submissionDirectory = submissionDirectory;
		Thread.currentThread().setName(submissionDirectory.getName());
	}

	public SingleGradingTask(String submissionPath) {
		this(new File(submissionPath));
	}

	/*
	 * build
	 */
	public SingleGradingTask testSuiteRouter(TestSuiteRouter testSuiteRouter) {
		this.testSuiteRouter = testSuiteRouter;
		return this;
	}

	/*
	 * logArea ===============
	 */
	public SingleGradingTask logArea(JTextArea logArea) {
		this.logArea = logArea;
		return this;
	}

    private void log(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(message + System.lineSeparator());
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }

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
                log(GradingMessage.NO_JAVA_FILE_FOUND_IN_FOLDER.getContent(folder.getName()));
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
                log(GradingMessage.COMPILATION_ERRORS_WITH_MESSAGE.getContent(Constants.EMPTY_STRING));
                try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        log("   " + line);
                    }
                }
                return false;
            }
            return true;

        } catch (Exception e) {
            log(GradingMessage.COMPILATION_ERRORS_WITH_MESSAGE.getContent(e.getMessage()));
            return false;
        }
    }
}
