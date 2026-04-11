import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StudentGrader {

    private static final String REPORTS_DIR = "reports";

    public static void main(String[] args) throws IOException {
        System.out.println("===========================================");
        System.out.println("     Student Self-Grader (Multiple Files)");
        System.out.println("===========================================\n");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path to your submission folder: ");
        String submissionPath = scanner.nextLine().trim();

        File submissionFolder = new File(submissionPath);

        if (!submissionFolder.exists() || !submissionFolder.isDirectory()) {
            System.out.println("Error: Folder not found or is not a directory!");
            System.out.println("Please provide a valid folder containing your .java files.");
            return;
        }

        new File(REPORTS_DIR).mkdirs();

        System.out.println("\nCompiling your submission files...");
        boolean compileSuccess = compileStudentFiles(submissionFolder);

        if (!compileSuccess) {
            System.out.println("❌ Compilation failed. Please fix the errors and try again.");
            generateCompilationErrorReport(submissionFolder.getName());
            return;
        }

        System.out.println("Compilation successful.\n");
        System.out.println("Running test cases...\n");

        // Run the tests using the compiled classes
        List<TestCase> tests = NumericTests.getAllTests();
        int totalScore = 0;
        List<Integer> scores = new ArrayList<>();
        List<Boolean> passedList = new ArrayList<>();

        for (TestCase test : tests) {
            System.out.print("→ " + test.getName() + " (" + test.getPoints() + " pts) ... ");
            boolean passed = test.runTest();
            int points = passed ? test.getPoints() : 0;
            totalScore += points;
            scores.add(points);
            passedList.add(passed);

            System.out.println(passed ? "✓ PASSED" : "✗ FAILED");
        }

        // Generate detailed report
        generateStudentReport(submissionFolder.getName(), totalScore, tests, scores, passedList);

        System.out.println("\n" + "=".repeat(60));
        System.out.printf("YOUR FINAL SCORE: %d / 100%n", totalScore);
        System.out.println("=".repeat(60));

        if (totalScore == 100) {
            System.out.println("Excellent work! All tests passed.");
        } else if (totalScore >= 70) {
            System.out.println("Good effort! Review the failed tests.");
        } else {
            System.out.println("Keep working on it. Check the report for details.");
        }

        System.out.println("\nReport saved in: " + REPORTS_DIR + "/");
        System.out.println("You can run this again anytime.");
    }

    // Compile all .java files in the student's submission folder
    private static boolean compileStudentFiles(File folder) {
        try {
            List<String> javaFiles = new ArrayList<>();
            Files.walk(folder.toPath())
                 .filter(path -> path.toString().endsWith(".java"))
                 .forEach(path -> javaFiles.add(path.toString()));

            if (javaFiles.isEmpty()) {
                System.out.println("No .java files found in the folder.");
                return false;
            }

            // Compile all java files together
            ProcessBuilder pb = new ProcessBuilder();
            List<String> command = new ArrayList<>();
            command.add("javac");
            command.addAll(javaFiles);

            pb.command(command);
            Process process = pb.start();

            int exitCode = process.waitFor();

            if (exitCode != 0) {
                printError(process.getErrorStream());
                return false;
            }
            return true;

        } catch (Exception e) {
            System.out.println("Error during compilation: " + e.getMessage());
            return false;
        }
    }

    private static void printError(InputStream stream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("   " + line);
            }
        }
    }

    private static void generateCompilationErrorReport(String studentName) throws IOException {
        String content = "Student Submission: " + studentName + "\n" +
                        "Date: " + LocalDateTime.now() + "\n\n" +
                        "Compilation Failed!\n" +
                        "Please check your code for syntax errors.\n";

        String filename = REPORTS_DIR + "/" + studentName.replaceAll("[^a-zA-Z0-9._-]", "_") + "_report.txt";
        Files.write(Paths.get(filename), content.getBytes("UTF-8"));
    }

    private static void generateStudentReport(String studentName, int totalScore,
                                              List<TestCase> tests,
                                              List<Integer> scores,
                                              List<Boolean> passed) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("===========================================\n");
        sb.append("          STUDENT SELF-GRADER REPORT\n");
        sb.append("===========================================\n");
        sb.append("Student       : ").append(studentName).append("\n");
        sb.append("Submission Date: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        sb.append("Final Score   : ").append(totalScore).append("/100\n\n");

        sb.append("TEST-BY-TEST RESULTS:\n");
        sb.append("--------------------------------------------------\n\n");

        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            boolean p = passed.get(i);
            int score = scores.get(i);

            sb.append(String.format("%-45s %s %3d / %d pts%n",
                    t.getName(), p ? "✓ PASSED" : "✗ FAILED", score, t.getPoints()));

            if (!p) {
                sb.append("   Feedback : ").append(t.getFeedback()).append("\n");
            }
            sb.append("\n");
        }

        sb.append("===========================================\n");
        sb.append("OVERALL RESULT: ").append(totalScore).append("/100\n");

        String filename = REPORTS_DIR + "/" + studentName.replaceAll("[^a-zA-Z0-9._-]", "_") + "_report.txt";
        Files.write(Paths.get(filename), sb.toString().getBytes("UTF-8"));
    }
}