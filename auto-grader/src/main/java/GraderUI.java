

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class GraderUI extends JFrame {

    private static final String SUBMISSIONS_DIR_DEFAULT = "submissions";
    private static final String REPORTS_DIR = "reports";

    private JTextField folderPathField;
    private JButton browseButton, gradeButton, openReportsButton;
    private JTextArea logArea;
    private JTable resultsTable;
    private DefaultTableModel tableModel;

    private List<StudentResult> currentResults = new ArrayList<>();
    private List<TestCase> testCases = new ArrayList<>();   // For dynamic columns

    public GraderUI() {
        setTitle("Java Lab Autograder - Batch UI (Offline - Text Reports)");
        setSize(1100, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Load test cases for column headers
        testCases = NumericTests.getAllTests();

        // ==================== TOP PANEL ====================
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBorder(BorderFactory.createTitledBorder("Submission Folder"));

        String defaultSubmissionDir = Paths.get(new File("").getAbsolutePath()).toString();// PathUtils.createFolderPath(getClass(), Consts.SUBMISSIONS);
        folderPathField = new JTextField(defaultSubmissionDir, 50);
        browseButton = new JButton("Browse...");
        gradeButton = new JButton("Grade All Submissions");
        gradeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gradeButton.setBackground(new Color(0, 122, 204));
        gradeButton.setForeground(Color.WHITE);

        topPanel.add(new JLabel("Folder:"));
        topPanel.add(folderPathField);
        topPanel.add(browseButton);
        topPanel.add(gradeButton);

        // ==================== CENTER ====================
        JSplitPane centerSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setBorder(BorderFactory.createTitledBorder("Grading Log"));

        // Dynamic table columns: Student + Total + % + Status + one per testcase
        String[] columnNames = buildColumnNames();
        tableModel = new DefaultTableModel(columnNames, 0);
        resultsTable = new JTable(tableModel);
        resultsTable.setRowHeight(28);

        // Custom renderer for per-testcase columns
        for (int i = 4; i < columnNames.length; i++) {
            resultsTable.getColumnModel().getColumn(i).setCellRenderer(new TestCaseCellRenderer());
        }

        // Double-click to show detailed test breakdown
        resultsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = resultsTable.getSelectedRow();
                    if (row >= 0 && row < currentResults.size()) {
                        showDetailedTestDialog(currentResults.get(row));
                    }
                }
            }
        });

        JScrollPane tableScroll = new JScrollPane(resultsTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Grading Results (Double-click row for details)"));

        centerSplit.setTopComponent(logScroll);
        centerSplit.setBottomComponent(tableScroll);
        centerSplit.setDividerLocation(280);

        // ==================== BOTTOM PANEL ====================
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        openReportsButton = new JButton("📁 Open Reports Folder");
        bottomPanel.add(openReportsButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerSplit, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event Listeners
        browseButton.addActionListener(this::browseFolder);
        gradeButton.addActionListener(this::startGrading);
        openReportsButton.addActionListener(e -> openFolder(REPORTS_DIR));

        log("✅ Java Lab Autograder UI (Simplified - Text Reports Only)\n" +
            "   • Select folder with student .java files\n" +
            "   • Click Grade → Get detailed per-testcase results\n" +
            "   • Double-click any student to see full pass/fail + feedback");
    }

    private String[] buildColumnNames() {
        List<String> cols = new ArrayList<>();
        cols.add("Student Name");
        cols.add("Total Score");
        cols.add("Percentage");
        cols.add("Status");
        for (TestCase t : testCases) {
            cols.add(t.getName() + " (" + t.getPoints() + " pts)");
        }
        return cols.toArray(new String[0]);
    }

    private class TestCaseCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            int testIndex = column - 4;
            if (testIndex < 0 || testIndex >= testCases.size()) return c;

            int maxPoints = testCases.get(testIndex).getPoints();
            int score = (value instanceof Integer) ? (Integer) value : 0;

            if (score == maxPoints) {
                c.setBackground(new Color(204, 255, 204)); // light green
                setText(score + " ✓");
            } else if (score == 0) {
                c.setBackground(new Color(255, 204, 204)); // light red
                setText(score + " ✗");
            } else {
                c.setBackground(new Color(255, 255, 204)); // light yellow
                setText(String.valueOf(score));
            }
            setHorizontalAlignment(SwingConstants.CENTER);
            return c;
        }
    }

    private void showDetailedTestDialog(StudentResult result) {
        JDialog dialog = new JDialog(this, "Test Details - " + result.studentName, true);
        dialog.setSize(720, 520);
        dialog.setLocationRelativeTo(this);

        JTextPane detailsPane = new JTextPane();
        detailsPane.setContentType("text/html");
        detailsPane.setEditable(false);

        StringBuilder html = new StringBuilder();
        html.append("<h2>Student: ").append(result.studentName).append("</h2>");
        html.append("<h3>Final Score: <span style='color:#0066cc'>").append(result.totalScore)
            .append("/100</span></h3><hr>");

        for (int i = 0; i < testCases.size(); i++) {
            TestCase t = testCases.get(i);
            int score = result.testScores.get(i);
            boolean passed = result.testPassed.get(i);

            html.append("<p><b>").append(t.getName()).append("</b> — ")
                .append(score).append("/").append(t.getPoints()).append(" pts ")
                .append(passed ? "<span style='color:green'>✓ PASSED</span>" : "<span style='color:red'>✗ FAILED</span>")
                .append("</p>");

            if (!passed) {
                html.append("<p style='margin-left:25px; color:#cc0000'>Feedback: ")
                    .append(t.getFeedback()).append("</p>");
            }
        }

        detailsPane.setText(html.toString());
        dialog.add(new JScrollPane(detailsPane));
        dialog.setVisible(true);
    }

    private void browseFolder(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setCurrentDirectory(new File(folderPathField.getText()));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            folderPathField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void startGrading(ActionEvent e) {
        String folderPath = folderPathField.getText().trim();
        File submissionsFolder = new File(folderPath);

        if (!submissionsFolder.exists() || !submissionsFolder.isDirectory()) {
            JOptionPane.showMessageDialog(this, "Folder not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        logArea.setText("");
        tableModel.setRowCount(0);
        currentResults.clear();
        new File(REPORTS_DIR).mkdirs();

        log("Scanning folder: " + folderPath);
        List<File> studentFiles = getStudentFiles(submissionsFolder);

        if (studentFiles.isEmpty()) {
            log("ERROR: No .java files found.");
            return;
        }

        log("Found " + studentFiles.size() + " submissions. Grading...\n");

        gradeButton.setEnabled(false);

        new Thread(() -> {
            int fullMarks = 0;

            for (int i = 0; i < studentFiles.size(); i++) {
                File file = studentFiles.get(i);
                String studentName = getStudentName(file.getName());

                log(String.format("[%d/%d] Grading %s ...", i + 1, studentFiles.size(), studentName));

                try {
                	StudentResult result = gradeSingleStudent(file, studentName);
                    currentResults.add(result);

                    if (result.totalScore == 100) fullMarks++;

                    SwingUtilities.invokeLater(() -> addRowToTable(result));
                    log("   → Total Score: " + result.totalScore + "/100");
                } catch (IOException exc) {
                	
                }
            }

            generateSummaryTXT(currentResults, fullMarks);

            SwingUtilities.invokeLater(() -> {
                gradeButton.setEnabled(true);
                log("\nGRADING COMPLETED!\n" +
                    "   • Detailed text reports saved in reports/ folder\n" +
                    "   • Double-click any row to see per-testcase details");
                JOptionPane.showMessageDialog(this, "Grading finished successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            });
        }).start();
    }

    private void addRowToTable(StudentResult r) {
        Object[] rowData = new Object[4 + testCases.size()];
        rowData[0] = r.studentName;
        rowData[1] = r.totalScore;
        rowData[2] = String.format("%.1f%%", r.totalScore / 1.0);
        rowData[3] = r.status;

        for (int i = 0; i < testCases.size(); i++) {
            rowData[4 + i] = r.testScores.get(i);
        }
        tableModel.addRow(rowData);
    }

    private List<File> getStudentFiles(File folder) {
        List<File> files = new ArrayList<>();
        File[] all = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".java"));
        if (all != null) files.addAll(Arrays.asList(all));
        files.sort(Comparator.comparing(File::getName));
        return files;
    }

    private String getStudentName(String filename) {
        return filename.replaceAll("(?i)_?Main\\.java$", "")
                       .replaceAll("(?i)\\.java$", "").trim();
    }

    private StudentResult gradeSingleStudent(File studentFile, String studentName) throws IOException {
        try {
            Files.copy(studentFile.toPath(), Paths.get("Main.java"), StandardCopyOption.REPLACE_EXISTING);

            Process compile = new ProcessBuilder("javac", "Main.java").start();
            if (compile.waitFor() != 0) {
                String errorMsg = getErrorMessage(compile.getErrorStream());
                saveIndividualReport(studentName, 0, "Compilation Failed\n\n" + errorMsg);
                return new StudentResult(studentName, 0,
                        Collections.nCopies(testCases.size(), 0),
                        Collections.nCopies(testCases.size(), false));
            }

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

            saveDetailedIndividualReport(studentName, totalScore, tests, scores, passedList);

            return new StudentResult(studentName, totalScore, scores, passedList);

        } catch (Exception e) {
            saveIndividualReport(studentName, 0, "Error: " + e.getMessage());
            return new StudentResult(studentName, 0,
                    Collections.nCopies(testCases.size(), 0),
                    Collections.nCopies(testCases.size(), false));
        }
    }

    private String getErrorMessage(InputStream stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = br.readLine()) != null) sb.append(line).append("\n");
        }
        return sb.toString();
    }

    private void saveIndividualReport(String studentName, int score, String content) throws IOException {
        String filename = REPORTS_DIR + "/" + studentName.replaceAll("[^a-zA-Z0-9._-]", "_") + "_report.txt";
        Files.write(Paths.get(filename), content.getBytes("UTF-8"));
    }

    private void saveDetailedIndividualReport(String studentName, int totalScore,
                                              List<TestCase> tests, List<Integer> scores,
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
        sb.append("OVERALL: ").append(totalScore).append("/100\n");

        String filename = REPORTS_DIR + "/" + studentName.replaceAll("[^a-zA-Z0-9._-]", "_") + "_report.txt";
        Files.write(Paths.get(filename), sb.toString().getBytes("UTF-8"));
    }

    private void generateSummaryTXT(List<StudentResult> results, int fullMarks) {
        try {
            String file = REPORTS_DIR + "/SUMMARY_" +
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

            Files.write(Paths.get(file), sb.toString().getBytes("UTF-8"));
        } catch (Exception ignored) {}
    }

    private void openFolder(String path) {
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Cannot open folder automatically.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void log(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraderUI().setVisible(true));
    }
}