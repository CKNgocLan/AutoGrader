

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LecturerGrader {

    private static final String SUBMISSIONS_DIR = "submissions";
    private static final String REPORTS_DIR = "reports";

    public static void main(String[] args) throws IOException {
        System.out.println("===========================================");
        System.out.println("     JAVA LAB BATCH GRADER (Lecturer Mode)");
        System.out.println("     Detailed per-testcase reports");
        System.out.println("===========================================\n");

        File submissionsFolder = new File(PathUtils.createFolderPath(LecturerGrader.class, Consts.SUBMISSIONS));
        
        if (!submissionsFolder.exists() || !submissionsFolder.isDirectory()) {
            System.out.println("❌ Error: '" + SUBMISSIONS_DIR + "' folder not found!");
            System.out.println("Please create the folder and put all student .java files inside.");
            return;
        }

        new File(REPORTS_DIR).mkdirs();

        List<File> studentFiles = getStudentSubmissionFiles(submissionsFolder);
        if (studentFiles.isEmpty()) {
            System.out.println("No .java files found in '" + SUBMISSIONS_DIR + "' folder.");
            return;
        }

        System.out.println("Found " + studentFiles.size() + " student submissions.");
        System.out.println("Starting batch grading...\n");

        List<StudentResult> results = new ArrayList<>();
        int fullMarksCount = 0;

        for (int i = 0; i < studentFiles.size(); i++) {
            File studentFile = studentFiles.get(i);
            String studentName = getStudentName(studentFile.getName());

            System.out.printf("[%d/%d] Grading %s ... ", i + 1, studentFiles.size(), studentName);

            StudentResult result = gradeSingleStudent(studentFile, studentName);
            results.add(result);

            if (result.totalScore == 100) fullMarksCount++;

            System.out.println("Score: " + result.totalScore + "/100");
        }

        // Generate summary report
        generateSummaryTXT(results, fullMarksCount);

        System.out.println("\n✅ Batch grading completed successfully!");
        System.out.println("Reports saved in folder: " + REPORTS_DIR);
        System.out.println("   • Individual detailed reports for each student");
        System.out.println("   • SUMMARY_....txt with overall results");
        System.out.println("   " + fullMarksCount + " students received full marks (100/100)");
    }

    // ====================== STUDENT RESULT ======================
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

    private static List<File> getStudentSubmissionFiles(File folder) {
        List<File> files = new ArrayList<>();
        File[] allFiles = folder.listFiles();
        if (allFiles != null) {
            for (File f : allFiles) {
                if (f.isFile() && f.getName().toLowerCase().endsWith(".java")) {
                    files.add(f);
                }
            }
        }
        files.sort(Comparator.comparing(File::getName));
        return files;
    }

    private static String getStudentName(String filename) {
        return filename.replaceAll("(?i)_?Main\\.java$", "")
                       .replaceAll("(?i)\\.java$", "").trim();
    }

    private static StudentResult gradeSingleStudent(File studentFile, String studentName) throws IOException {
        try {
            // Copy student file to Main.java
            Path mainPath = Paths.get("Main.java");
            Files.copy(studentFile.toPath(), mainPath, StandardCopyOption.REPLACE_EXISTING);

            // Compile
            Process compile = new ProcessBuilder("javac", "Main.java").start();
            int compileCode = compile.waitFor();

            if (compileCode != 0) {
                String errorMsg = getErrorMessage(compile.getErrorStream());
                String reportContent = "Student: " + studentName + "\nCompilation Failed!\n\n" + errorMsg;
                saveIndividualReport(studentName, 0, reportContent);
                return new StudentResult(studentName, 0,
                        Collections.nCopies(4, 0), Collections.nCopies(4, false));
            }

            // Run all test cases
            List<TestCase> tests = NumericTests.getAllTests();
            int totalScore = 0;
            List<Integer> scores = new ArrayList<>();
            List<Boolean> passedList = new ArrayList<>();

            for (TestCase test : tests) {
                boolean passed = test.runTest();
                int points = passed ? test.getPoints() : 0;
                totalScore += points;
                scores.add(points);
                passedList.add(passed);
            }

            // Save detailed individual report
            saveDetailedIndividualReport(studentName, totalScore, tests, scores, passedList);

            return new StudentResult(studentName, totalScore, scores, passedList);

        } catch (Exception e) {
            String errorReport = "Error grading " + studentName + ": " + e.getMessage();
            saveIndividualReport(studentName, 0, errorReport);
            return new StudentResult(studentName, 0,
                    Collections.nCopies(4, 0), Collections.nCopies(4, false));
        }
    }

    private static String getErrorMessage(InputStream stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    private static void saveIndividualReport(String studentName, int score, String content) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm"));
        String filename = REPORTS_DIR + "/" + studentName.replaceAll("[^a-zA-Z0-9._-]", "_") 
                         + "_report_" + timestamp + ".txt";
        Files.write(Paths.get(filename), content.getBytes("UTF-8"));
    }

    // Detailed report showing which testcase passed/failed with scores
    private static void saveDetailedIndividualReport(String studentName, int totalScore,
                                                     List<TestCase> tests,
                                                     List<Integer> scores,
                                                     List<Boolean> passed) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("===========================================\n");
        sb.append("STUDENT REPORT\n");
        sb.append("===========================================\n");
        sb.append("Student     : ").append(studentName).append("\n");
        sb.append("Graded on   : ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        sb.append("Final Score : ").append(totalScore).append("/100\n\n");

        sb.append("TEST-BY-TEST RESULTS:\n");
        sb.append("--------------------------------------------------\n");

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
        sb.append("OVERALL RESULT: ").append(totalScore).append("/100 - ")
          .append((totalScore == 100) ? "Excellent!" : "Review failed tests.").append("\n");

        String filename = REPORTS_DIR + "/" + studentName.replaceAll("[^a-zA-Z0-9._-]", "_") + "_report.txt";
        Files.write(Paths.get(filename), sb.toString().getBytes("UTF-8"));
    }

    private static void generateSummaryTXT(List<StudentResult> results, int fullMarks) {
        try {
            String summaryFile = REPORTS_DIR + "/SUMMARY_" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm")) + ".txt";

            StringBuilder sb = new StringBuilder();
            sb.append("BATCH GRADING SUMMARY\n");
            sb.append("=====================\n");
            sb.append("Date          : ").append(LocalDateTime.now()).append("\n");
            sb.append("Total Students: ").append(results.size()).append("\n");
            sb.append("Full Marks    : ").append(fullMarks).append("\n\n");
            sb.append("DETAILED SCORES:\n");
            sb.append("--------------------------------------------------\n");

            for (StudentResult r : results) {
                sb.append(String.format("%-35s %3d / 100    %s%n", r.studentName, r.totalScore, r.status));
            }

            Files.write(Paths.get(summaryFile), sb.toString().getBytes("UTF-8"));
        } catch (Exception ignored) {}
    }
}