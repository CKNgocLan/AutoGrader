package student;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import org.opentest4j.AssertionFailedError;

import student.constant.Lab;
import student.constant.Problem;
import student.exception.TesterGotNoClassNameException;
import student.model.SingleGradingTask;
import student.testSuite.lab4.problem1_3.EmployeeTester;

public class StudentSnippet {
	String selectedLab = Lab.L3;
	String path = "D:\\eclipse-wksp\\AutoGrader\\auto-grader\\sample-lab3-submission";
	File submissionDirectory = new File(path);
    
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, NoSuchFieldException, TesterGotNoClassNameException {
    }

    private static void future() {
    	ExecutorService executor = Executors.newSingleThreadExecutor();
    	Future<Integer> future5 = executor.submit(() -> { return 5 * 5; });
    	
    	try {
	    	while (!future5.isDone()) {
	    		System.out.println("Calculating...");
	    	}
			System.out.println(future5.get(500, TimeUnit.MILLISECONDS));
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
    	executor.shutdown();
    }

	private static void assertThrownException() throws ClassNotFoundException, TesterGotNoClassNameException {
		try {
        	EmployeeTester employeeTester = new EmployeeTester();
        	assertThrows(NoSuchMethodException.class, () -> {
        		employeeTester.isValidNumberMethod();
        	});
    	} catch (AssertionFailedError e) {
    		e.printStackTrace();
    		System.out.println(e.getMessage());
    	}
	}

	private static void cleanCombinedFiles() {
		// Define the relative or absolute path to the target/classes directory
        Path targetClassesPath = Paths.get("target", "classes");

        // Verify that the directory exists before proceeding
        if (!Files.exists(targetClassesPath)) {
            System.out.println("The target/classes directory does not exist.");
            return;
        }
        
        // Files.list() only reads the immediate contents of the directory
        try (Stream<Path> files = Files.list(targetClassesPath)) {
            files.filter(Files::isRegularFile)
                 .filter(path -> path.toString().endsWith(".class"))
                 .forEach(path -> {
                     try {
                         Files.delete(path);
                         System.out.println("Deleted top-level file: " + path.getFileName());
                     } catch (IOException e) {
                         System.err.println("Failed to delete " + path + ": " + e.getMessage());
                     }
                 });
            
            System.out.println("Top-level .class file cleanup complete.");
        } catch (IOException e) {
            System.err.println("Error reading the directory: " + e.getMessage());
        }
	}
}
