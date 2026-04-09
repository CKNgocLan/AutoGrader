package experimentGrader;

import java.io.*;
import java.util.*;

public class NumericTests {

    // Helper to run student's Main with given input and capture output
    private static String runStudentMain(String input) throws Exception {
        // Save original streams
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        try {
            // Set input
            System.setIn(new ByteArrayInputStream(input.getBytes("UTF-8")));
            System.setOut(printStream);

            // Run student's main (assumes class is named "Main")
            StudentSubmission.main(new String[0]);   // <-- student's compiled Main class

            printStream.flush();
            return outputStream.toString("UTF-8").trim();
        } finally {
            // Restore original streams
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    // Example Test Cases with numeric assertions (total = 100 points)

    public static List<TestCase> getAllTests() {
        List<TestCase> tests = new ArrayList<>();

        // Test 1: Simple addition-like output (20 points)
        tests.add(new TestCase() {
            public String getName() { return "Test 1: Sum of 5 and 3"; }
            public int getPoints() { return 20; }
            public boolean runTest() {
                try {
                    String output = runStudentMain("5\n3\n");
                    int result = Integer.parseInt(output.trim());
                    System.out.println("Result: " + result);
                    return result == 8;   // numeric assertion: 5 + 3 = 8
                } catch (Exception e) {
                    return false;
                }
            }
            public String getFeedback() {
                return "Expected output: 8 (sum of 5 and 3)";
            }
        });

        // Test 2: Average calculation (30 points)
        tests.add(new TestCase() {
            public String getName() { return "Test 2: Average of 10 20"; }
            public int getPoints() { return 30; }
            public boolean runTest() {
                try {
                    String output = runStudentMain("10\n20\n");
                    double result = Double.parseDouble(output.trim());
                    System.out.println("Result: " + result);
                    return Math.abs(result - 30.0) < 0.01;   // floating point tolerance
                } catch (Exception e) {
                    return false;
                }
            }
            public String getFeedback() {
                return "Expected output: 20.0 (average of 10, 20)";
            }
        });

        // Test 3: Max of three numbers (25 points)
        tests.add(new TestCase() {
            public String getName() { return "Test 3: Maximum of 7 12 5"; }
            public int getPoints() { return 25; }
            public boolean runTest() {
                try {
                    String output = runStudentMain("7\n12\n5\n");
                    int result = Integer.parseInt(output.trim());
                    return result == 12;
                } catch (Exception e) {
                    return false;
                }
            }
            public String getFeedback() {
                return "Expected output: 12 (max of 7, 12, 5)";
            }
        });

        // Test 4: Simple multiplication (25 points) — add more easily
        tests.add(new TestCase() {
            public String getName() { return "Test 4: Product of 6 and 7"; }
            public int getPoints() { return 25; }
            public boolean runTest() {
                try {
                    String output = runStudentMain("6\n7\n");
                    int result = Integer.parseInt(output.trim());
                    return result == 42;
                } catch (Exception e) {
                    return false;
                }
            }
            public String getFeedback() {
                return "Expected output: 42 (6 * 7)";
            }
        });

        return tests;
    }
}