package student;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;

import student.constant.Problem;
import student.model.ITestCase;
import student.testSuite.labTestSuite.ExamFinalTestSuite253;

public class LecturerGrader {

    private static final String SUBMISSIONS_DIR = "submissions";
    private static final String REPORTS_DIR = "reports";
    private static final int MAX_THREADS = 6; // Adjust based on your CPU

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("     JAVA LAB BATCH GRADER (PARALLEL MODE)");
        System.out.println("     " + MAX_THREADS + " students graded simultaneously");
        System.out.println("===========================================\n");

        File submissionsFolder = new File(SUBMISSIONS_DIR);
        if (!submissionsFolder.exists() || !submissionsFolder.isDirectory()) {
            System.out.println("❌ '" + SUBMISSIONS_DIR + "' folder not found!");
            return;
        }

        new File(REPORTS_DIR).mkdirs();

        List<File> studentFiles = getStudentFiles(submissionsFolder);
        if (studentFiles.isEmpty()) {
            System.out.println("No student submissions found.");
            return;
        }

        System.out.println("Found " + studentFiles.size() + " submissions.");
        System.out.println("Starting parallel grading with " + MAX_THREADS + " threads...\n");

        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
        List<Future<StudentResult>> futures = new ArrayList<>();
        int fullMarks = 0;

        for (File file : studentFiles) {
            String studentName = getStudentName(file.getName());
            Future<StudentResult> future = executor.submit(() -> gradeSingleStudent(file, studentName));
            futures.add(future);
        }

        List<StudentResult> results = new ArrayList<>();

        for (Future<StudentResult> future : futures) {
            try {
                StudentResult result = future.get();
                results.add(result);
                if (result.totalScore == 100) fullMarks++;
                System.out.printf("Completed: %-30s → %3d / 100%n", result.studentName, result.totalScore);
            } catch (Exception e) {
                System.out.println("Error processing result: " + e.getMessage());
            }
        }

        executor.shutdown();

        generateSummaryTXT(results, fullMarks);

        System.out.println("\n✅ PARALLEL BATCH GRADING COMPLETED!");
        System.out.println("Total students : " + results.size());
        System.out.println("Full marks     : " + fullMarks);
        System.out.println("Reports saved in: " + REPORTS_DIR);
    }

    private static List<File> getStudentFiles(File folder) {
        List<File> files = new ArrayList<>();
        File[] all = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".java"));
        if (all != null) {
            files.addAll(Arrays.asList(all));
        }
        files.sort(Comparator.comparing(File::getName));
        return files;
    }

    private static String getStudentName(String filename) {
        return filename.replaceAll("(?i)_?Main\\.java$", "")
                       .replaceAll("(?i)\\.java$", "").trim();
    }

    private static StudentResult gradeSingleStudent(File studentFile, String studentName) {
        try {
            // Clean old .class files first
            cleanClassFiles(studentFile.getParentFile());

            // Copy to Main.java
            Files.copy(studentFile.toPath(), Paths.get("Main.java"), StandardCopyOption.REPLACE_EXISTING);

            // Compile
            Process compile = new ProcessBuilder("javac", "Main.java").start();
            if (compile.waitFor() != 0) {
                saveIndividualReport(studentName, 0, "Compilation Failed");
                return new StudentResult(studentName, 0, null, null);
            }

            // Run tests
            List<ITestCase> tests = new ExamFinalTestSuite253().getAllTests(Problem.SECTION_1);
            int totalScore = 0;
            List<Integer> scores = new ArrayList<>();
            List<Boolean> passedList = new ArrayList<>();

            for (ITestCase test : tests) {
                boolean passed = test.runTest();
                int points = passed ? test.getPoints() : 0;
                totalScore += points;
                scores.add(points);
                passedList.add(passed);
            }

            saveDetailedIndividualReport(studentName, totalScore, tests, scores, passedList);

            return new StudentResult(studentName, totalScore, scores, passedList);

        } catch (Exception e) {
            saveIndividualReport(studentName, 0, "Error: " + e.getMessage());
            return new StudentResult(studentName, 0, null, null);
        }
    }

    private static void cleanClassFiles(File folder) {
        try {
            Files.walk(folder.toPath())
                 .filter(p -> p.toString().endsWith(".class"))
                 .forEach(p -> {
                     try { Files.deleteIfExists(p); } catch (Exception ignored) {}
                 });
        } catch (Exception ignored) {}
    }

    // ==================== REPORTING METHODS (same as before) ====================
    private static void saveIndividualReport(String studentName, int score, String content) {
        try {
            String filename = REPORTS_DIR + "/" + studentName.replaceAll("[^a-zA-Z0-9._-]", "_") + "_report.txt";
            Files.write(Paths.get(filename), content.getBytes("UTF-8"));
        } catch (Exception ignored) {}
    }

    private static void saveDetailedIndividualReport(String studentName, int totalScore,
                                                     List<ITestCase> tests,
                                                     List<Integer> scores,
                                                     List<Boolean> passed) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("===========================================\n");
            sb.append("STUDENT REPORT - ").append(studentName).append("\n");
            sb.append("Date: ").append(LocalDateTime.now()).append("\n");
            sb.append("Final Score: ").append(totalScore).append("/100\n\n");

            for (int i = 0; i < tests.size(); i++) {
                ITestCase t = tests.get(i);
                boolean p = passed.get(i);
                sb.append(String.format("%-45s %s %3d pts%n", t.getName(), p ? "✓ PASSED" : "✗ FAILED", scores.get(i)));
                if (!p) sb.append("   Feedback: ").append(t.getFeedback()).append("\n");
            }

            String filename = REPORTS_DIR + "/" + studentName.replaceAll("[^a-zA-Z0-9._-]", "_") + "_report.txt";
            Files.write(Paths.get(filename), sb.toString().getBytes("UTF-8"));
        } catch (Exception ignored) {}
    }

    private static void generateSummaryTXT(List<StudentResult> results, int fullMarks) {
        try {
            String file = REPORTS_DIR + "/SUMMARY_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm")) + ".txt";
            StringBuilder sb = new StringBuilder();
            sb.append("BATCH GRADING SUMMARY (PARALLEL)\n");
            sb.append("Date: ").append(LocalDateTime.now()).append("\n");
            sb.append("Total Students: ").append(results.size()).append("\n");
            sb.append("Full Marks: ").append(fullMarks).append("\n\n");

            for (StudentResult r : results) {
                sb.append(String.format("%-35s %3d / 100    %s%n", r.studentName, r.totalScore, r.status));
            }

            Files.write(Paths.get(file), sb.toString().getBytes("UTF-8"));
        } catch (Exception ignored) {}
    }

    static class StudentResult {
        String studentName;
        int totalScore;
        List<Integer> testScores;
        List<Boolean> testPassed;
        String status;

        StudentResult(String name, int total, List<Integer> scores, List<Boolean> passed) {
            this.studentName = name;
            this.totalScore = total;
            this.testScores = scores;
            this.testPassed = passed;
            this.status = (total == 100) ? "Excellent" : (total >= 70 ? "Good" : "Needs Improvement");
        }
    }
}