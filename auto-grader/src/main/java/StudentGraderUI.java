import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentGraderUI extends JFrame {

    private JTextField folderPathField;
    private JButton browseButton, gradeButton, openReportsButton;
    private JTextArea logArea;
    private JButton clearLogButton;

    public StudentGraderUI() {
        setTitle("Student Self-Grader - Java Lab");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setSize(1080, 620);
        setUndecorated(false);
        setVisible(false);
        
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ==================== TOP PANEL ====================
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        topPanel.setBorder(BorderFactory.createTitledBorder("Your Submission Folder"));

        folderPathField = new JTextField(50);
        folderPathField.setText(System.getProperty(Constants.USER_DIR)); // default hint

        browseButton = new JButton("Browse Folder...");
        gradeButton = new JButton("Start Grading");
        gradeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gradeButton.setBackground(new Color(0, 150, 0));
        gradeButton.setForeground(Color.WHITE);

        topPanel.add(new JLabel("Submission Folder:"));
        topPanel.add(folderPathField);
        topPanel.add(browseButton);
        topPanel.add(gradeButton);

        // ==================== CENTER: Log Area ====================
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        logArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setBorder(BorderFactory.createTitledBorder("Grading Progress & Results"));

        // ==================== BOTTOM PANEL ====================
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        openReportsButton = new JButton("📁 Open Reports Folder");
        clearLogButton = new JButton("Clear Log");
        bottomPanel.add(openReportsButton);
        bottomPanel.add(clearLogButton);

        add(topPanel, BorderLayout.NORTH);
        add(logScroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event Listeners
        browseButton.addActionListener(this::browseFolder);
        gradeButton.addActionListener(this::startGrading);
        openReportsButton.addActionListener(e -> openReportsFolder());
        clearLogButton.addActionListener(e -> logArea.setText(""));

        log("Student Submission Grader Ready\n");
        log("Instructions:");
        log("1. DELETE PACKAGE STATEMENT (first line)");
        log("2. PUT ALL your .java files in one folder but NOT APPLICATION FILE containing main()");
        log("3. Select that folder using Browse");
        log("4. Click \"Grade My Submission\"");
        log("5. Check the detailed report in reports/ folder\n");
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
        String path = folderPathField.getText().trim();
        File submissionFolder = new File(path);

        if (!submissionFolder.exists() || !submissionFolder.isDirectory()) {
            JOptionPane.showMessageDialog(this, 
                "Folder not found!\nPlease select a valid folder containing your .java files.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        logArea.setText(""); // Clear previous log
        log("Starting self-grading for folder: " + submissionFolder.getName());
        log("Compiling your Java files...\n");

        gradeButton.setEnabled(false);

        new Thread(() -> {
            try {
                // Step 1: Compile all student's .java files
                boolean compileSuccess = compileStudentFiles(submissionFolder);

                if (!compileSuccess) {
                    log("Compilation failed. Please check your code for errors.");
                    generateCompilationErrorReport(submissionFolder.getName());
                    SwingUtilities.invokeLater(() -> gradeButton.setEnabled(true));
                    return;
                }

                log("Compilation successful!\n");
                log("Running your test cases...\n");

                // Step 2: Run tests
                List<TestCase> tests = MethodTestSuite.getAllTests();
                List<Integer> scores = new ArrayList<>();
                List<Boolean> passedList = new ArrayList<>();

                for (TestCase test : tests) {
                    log("→ " + test.getName() + " (" + test.getPoints() + " pts) ... ");
                    boolean passed = test.runTest();
                    int points = passed ? test.getPoints() : 0;
                    // TODO replace totalScore
//                    totalScore += points;
                    scores.add(points);
                    passedList.add(passed);

                    log(passed ? "PASSED" : "FAILED");
                }

                // Step 3: Generate report
                int totalScore = scores.stream().mapToInt(Integer::intValue).sum();
                generateStudentReport(submissionFolder.getName(), totalScore, tests, scores, passedList);

                log("\n" + "=".repeat(60));
                log(String.format("FINAL SCORE: %d / 100", totalScore));
                log("=".repeat(60));

                if (totalScore == 100) {
                    log("Excellent! All tests passed.");
                } else if (totalScore >= 70) {
                    log("Good work! Review the failed tests below.");
                } else {
                    log("Please review the failed tests and try again.");
                }

                log("\nDetailed report saved in reports/ folder.");

                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, 
                        "Grading Completed!\n\nYour Score: " + totalScore + "/100\n\nCheck the reports folder for details.", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    gradeButton.setEnabled(true);
                });

            } catch (Exception ex) {
                log("\nUnexpected error: " + ex.getMessage());
                gradeButton.setEnabled(true);
            }
        }).start();
    }

    // Compile all .java files in the selected folder
    private boolean compileStudentFiles(File folder) {
        try {
            List<String> javaFiles = new ArrayList<>();
            Files.walk(folder.toPath())
                 .filter(p -> p.toString().endsWith(".java"))
                 .forEach(p -> javaFiles.add(p.toString()));

            if (javaFiles.isEmpty()) {
                log("No .java files found in the folder!");
                return false;
            }

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
                log("Compilation Errors:");
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
            log("Compilation error: " + e.getMessage());
            return false;
        }
    }

    private void generateCompilationErrorReport(String folderName) {
        try {
            String content = "Student Submission: " + folderName + "\n" +
                            "Date: " + LocalDateTime.now() + "\n\n" +
                            "Compilation Failed!\n" +
                            "Please check your code for syntax or compilation errors.\n";

            String filename = Constants.REPORTS_DIR + "/" + folderName.replaceAll("[^a-zA-Z0-9._-]", "_") + "_report.txt";
            Files.write(Paths.get(filename), content.getBytes("UTF-8"));
        } catch (Exception ignored) {}
    }

    private void generateStudentReport(String folderName, int totalScore, 
                                       List<TestCase> tests, 
                                       List<Integer> scores, 
                                       List<Boolean> passed) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("===========================================\n");
            sb.append("          STUDENT SELF-GRADER REPORT\n");
            sb.append("===========================================\n");
            sb.append("Folder Name   : ").append(folderName).append("\n");
            sb.append("Date          : ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
            sb.append("Final Score   : ").append(totalScore).append("/100\n\n");

            sb.append("DETAILED TEST RESULTS:\n");
            sb.append("--------------------------------------------------\n\n");

            for (int i = 0; i < tests.size(); i++) {
                TestCase t = tests.get(i);
                boolean p = passed.get(i);
                int score = scores.get(i);

                sb.append(String.format("%-45s %s %3d / %d pts%n",
                        t.getName(), p ? "PASSED" : "FAILED", score, t.getPoints()));

                if (!p) {
                    sb.append("   Feedback : ").append(t.getFeedback()).append("\n\n");
                } else {
                    sb.append("\n");
                }
            }

            sb.append("===========================================\n");
            sb.append("OVERALL RESULT: ").append(totalScore).append("/100\n");

            String filename = Constants.REPORTS_DIR + "/" + folderName.replaceAll("[^a-zA-Z0-9._-]", "_") + "_report.txt";
            Files.write(Paths.get(filename), sb.toString().getBytes("UTF-8"));

        } catch (Exception e) {
            log("Failed to save report: " + e.getMessage());
        }
    }

    private void openReportsFolder() {
        File reportsDir = new File("reports");
        if (!reportsDir.exists()) reportsDir.mkdirs();
        try {
            Desktop.getDesktop().open(reportsDir);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Cannot open folder automatically.\nPath: " + reportsDir.getAbsolutePath(), 
                "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void log(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentGraderUI().setVisible(true);
        });
    }
}