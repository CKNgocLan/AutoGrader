package grader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LecturerGrader {

    private static final String SUBMISSIONS_DIR = "submissions";
    private static final String REPORTS_DIR = "reports";

    public static void main(String[] args) throws IOException {
        System.out.println("===========================================");
        System.out.println("     JAVA LAB BATCH GRADER (Lecturer Mode)");
        System.out.println("     Total: 100 points per student");
        System.out.println("===========================================\n");

        String submissionDirPath = Paths.get(System.getProperty("user.dir").toString(), "src", LecturerGrader.class.getPackageName(), SUBMISSIONS_DIR).toString();
        File submissionsFolder = new File(submissionDirPath);
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

        System.out.println("Found " + studentFiles.size() + " submissions. Starting batch grading...\n");

        List<StudentResult> results = new ArrayList<>();
        int fullMarksCount = 0;

        for (int i = 0; i < studentFiles.size(); i++) {
            File studentFile = studentFiles.get(i);
            String studentName = getStudentName(studentFile.getName());

            System.out.printf("[%d/%d] Grading %s ... ", i + 1, studentFiles.size(), studentName);

            StudentResult result = gradeSingleStudent(studentFile, studentName);
            results.add(result);

            if (result.score == 100) {
				fullMarksCount++;
			}

            System.out.println("Score: " + result.score + "/100");
        }

        // Generate reports
        generateSummaryCSV(results);
        generateSummaryTXT(results, fullMarksCount);

        System.out.println("\n✅ Batch grading completed successfully!");
        System.out.println("Reports generated in folder: " + REPORTS_DIR);
        System.out.println("   • summary_grades.csv     ← Open this in Excel/Google Sheets");
        System.out.println("   • SUMMARY_....txt        ← Human-readable summary");
        System.out.println("   • Individual student reports (detailed)");
    }

    // ==================== Student Result Class ====================
    static class StudentResult {
        String studentName;
        int score;
        String status;
        String detailedFeedback;

        StudentResult(String name, int score, String status, String feedback) {
            this.studentName = name;
            this.score = score;
            this.status = status;
            this.detailedFeedback = feedback;
        }
    }

    private static List<File> getStudentSubmissionFiles(File folder) {
        List<File> files = new ArrayList<>();
        File[] all = folder.listFiles();
        if (all != null) {
            for (File f : all) {
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
                       .replaceAll("(?i)\\.java$", "")
                       .trim();
    }

    private static StudentResult gradeSingleStudent(File studentFile, String studentName) throws IOException {
        try {
            // Copy to Main.java
            Path mainPath = Paths.get("Main.java");
            Files.copy(studentFile.toPath(), mainPath, StandardCopyOption.REPLACE_EXISTING);

            // Compile
            Process compile = new ProcessBuilder("javac", "Main.java").start();
            int compileCode = compile.waitFor();

            if (compileCode != 0) {
                String errorMsg = getErrorMessage(compile.getErrorStream());
                String report = "Student: " + studentName + "\nCompilation Failed!\n\n" + errorMsg;
                saveIndividualReport(studentName, 0, report);
                return new StudentResult(studentName, 0, "Compilation Failed", report);
            }

            // Run tests
            List<TestCase> tests = NumericTests.getAllTests();
            int totalScore = 0;
            StringBuilder feedback = new StringBuilder();

            feedback.append("Student: ").append(studentName).append("\n");
            feedback.append("Graded on: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).append("\n\n");
            feedback.append("Test Results:\n\n");

            for (TestCase test : tests) {
                boolean passed = test.runTest();
                int points = passed ? test.getPoints() : 0;
                totalScore += points;

                feedback.append(String.format("%-45s %-3s %3d pts%n",
                        test.getName(), passed ? "✓" : "✗", points));

                if (!passed) {
                    feedback.append("   → ").append(test.getFeedback()).append("\n");
                }
            }

            feedback.append("\nFINAL SCORE: ").append(totalScore).append(" / 100\n");

            String status = (totalScore == 100) ? "Excellent" : (totalScore >= 70 ? "Good" : "Needs Improvement");

            saveIndividualReport(studentName, totalScore, feedback.toString());

            return new StudentResult(studentName, totalScore, status, feedback.toString());

        } catch (Exception e) {
            String report = "Error grading " + studentName + ": " + e.getMessage();
            saveIndividualReport(studentName, 0, report);
            return new StudentResult(studentName, 0, "Error", report);
        }
    }

    private static void saveIndividualReport(String studentName, int score, String content) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm"));
        String filename = REPORTS_DIR + "/" + studentName.replaceAll("[^a-zA-Z0-9._-]", "_")
                         + "_report_" + timestamp + ".txt";
        Files.write(Paths.get(filename), content.getBytes("UTF-8"));
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

    // ==================== CSV Report (Best for Excel) ====================
    private static void generateSummaryCSV(List<StudentResult> results) {
        try {
            String csvFile = REPORTS_DIR + "/summary_grades.csv";
            try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile))) {

                // Header
                pw.println("Student Name,Score,Percentage,Status,Graded On");

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                for (StudentResult r : results) {
                    String line = String.format("\"%s\",%d,%.1f%%,\"%s\",\"%s\"",
                            escapeCsv(r.studentName),
                            r.score,
                            (r.score / 100.0) * 100,
                            r.status,
                            LocalDateTime.now().format(dtf));
                    pw.println(line);
                }
            }
        } catch (Exception e) {
            System.out.println("Warning: Could not create CSV summary: " + e.getMessage());
        }
    }

    private static String escapeCsv(String field) {
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }

    // ==================== Human-readable Summary ====================
    private static void generateSummaryTXT(List<StudentResult> results, int fullMarks) {
        try {
            String summaryFile = REPORTS_DIR + "/SUMMARY_" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm")) + ".txt";

            StringBuilder sb = new StringBuilder();
            sb.append("JAVA LAB BATCH GRADING SUMMARY\n");
            sb.append("=============================\n");
            sb.append("Date: ").append(LocalDateTime.now()).append("\n");
            sb.append("Total Students: ").append(results.size()).append("\n");
            sb.append("Full Marks (100 pts): ").append(fullMarks).append("\n\n");
            sb.append("DETAILED SCORES:\n");
            sb.append("--------------------------------------------------\n");

            for (StudentResult r : results) {
                sb.append(String.format("%-35s %3d / 100    %s%n", r.studentName, r.score, r.status));
            }

            Files.write(Paths.get(summaryFile), sb.toString().getBytes("UTF-8"));
        } catch (Exception ignored) {}
    }
}