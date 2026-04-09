package experimentGrader;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Autograder {

    public static void main(String[] args) {
        System.out.println("=== Java Lab Autograder (Total: 100 points) ===\n");
        
        String submissionPath = Paths.get(System.getProperty("user.dir").toString(), "src", Autograder.class.getPackageName(), "StudentSubmission.java").toString();
        
        // For single student: place their file as "Main.java" in current dir
        // For batch: loop over submissions/ folder

        gradeSubmission(submissionPath);   // Change to batch mode if needed
    }

    private static void gradeSubmission(String studentFileName) {
        try {
            // Step 1: Compile student's code
            System.out.println("Compiling " + studentFileName + "...");
            Process compileProcess = new ProcessBuilder("javac", studentFileName).start();
            int compileExit = compileProcess.waitFor();

            if (compileExit != 0) {
                System.out.println("❌ Compilation failed!");
                printStream(compileProcess.getErrorStream());
                System.out.println("Final Score: 0 / 100");
                return;
            }
            System.out.println("✅ Compilation successful.\n");

            // Step 2: Run all test cases
            List<TestCase> tests = NumericTests.getAllTests();
            int totalScore = 0;
            int totalPossible = 0;

            System.out.println("Running Test Cases:");
            for (TestCase test : tests) {
                totalPossible += test.getPoints();
                System.out.println("\n▶ " + test.getName() + " (" + test.getPoints() + " pts)");

                boolean passed = test.runTest();
                if (passed) {
                    System.out.println("   ✅ Passed");
                    totalScore += test.getPoints();
                } else {
                    System.out.println("   ❌ Failed");
                    System.out.println("   Feedback: " + test.getFeedback());
                }
            }

            // Step 3: Final report
            System.out.println("\n" + "=".repeat(50));
            System.out.println("FINAL GRADE: " + totalScore + " / " + totalPossible);
            System.out.println("=".repeat(50));

        } catch (Exception e) {
            System.out.println("Error during grading: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printStream(InputStream stream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}