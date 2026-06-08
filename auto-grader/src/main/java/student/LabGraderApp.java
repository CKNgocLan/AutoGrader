package student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;

import student.constant.Problem;
import student.model.ITestCase;
import student.testSuite.labTestSuite.ExamFinalTestSuite253;

public class LabGraderApp extends JFrame {

    private JTextField folderPathField;
    private JComboBox<Integer> labCombo, questionCombo;
    private JButton browseBtn, gradeBtn, openReportsBtn;
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JTextArea logArea;
    private JLabel statusLabel;
    private Color themeColor = new Color(220, 53, 69);

    public LabGraderApp() {
        setTitle("LabGrader - Java Lab Autograder");
        setSize(1100, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 250));

        // Header
        JPanel header = createHeaderPanel();
        mainPanel.add(header, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = createInputPanel();
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Results Table
        createResultsTable();
        JScrollPane tableScroll = new JScrollPane(resultsTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Grading Results"));

        // Log Area
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setBorder(BorderFactory.createTitledBorder("Progress Log"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableScroll, logScroll);
        splitPane.setResizeWeight(0.6);
        mainPanel.add(splitPane, BorderLayout.CENTER);

        // Status & Buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel(" Ready", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bottomPanel.add(statusLabel, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        openReportsBtn = new JButton("📁 Open Reports Folder");
        btnPanel.add(openReportsBtn);
        bottomPanel.add(btnPanel, BorderLayout.CENTER);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Listeners
        browseBtn.addActionListener(e -> browseFolder());
        gradeBtn.addActionListener(e -> startBatchGrading());
        openReportsBtn.addActionListener(e -> openReportsFolder());
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(themeColor);
        header.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel title = new JLabel(" LabGrader - Java Lab Autograder", SwingConstants.LEFT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);

        // Theme colors
        JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        colorPanel.setOpaque(false);
        for (Color c : new Color[]{new Color(220,53,69), new Color(40,167,69), 
                                   new Color(255,193,7), new Color(0,123,255)}) {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(30,30));
            b.setBackground(c);
            b.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            b.addActionListener(e -> changeTheme(c));
            colorPanel.add(b);
        }

        header.add(title, BorderLayout.WEST);
        header.add(colorPanel, BorderLayout.EAST);
        return header;
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Batch Grading Configuration"));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Submissions Folder:"), gbc);

        folderPathField = new JTextField(40);
        gbc.gridx = 1; gbc.gridwidth = 2;
        panel.add(folderPathField, gbc);

        browseBtn = new JButton("Browse...");
        gbc.gridx = 3; gbc.gridwidth = 1;
        panel.add(browseBtn, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(new JLabel("Lab:"), gbc);
        labCombo = new JComboBox<>();
        for (int i=1; i<=15; i++) labCombo.addItem(i);
        gbc.gridx = 1;
        panel.add(labCombo, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Question:"), gbc);
        questionCombo = new JComboBox<>();
        for (int i=1; i<=10; i++) questionCombo.addItem(i);
        gbc.gridx = 3;
        panel.add(questionCombo, gbc);

        gradeBtn = new JButton("🚀 Start Batch Grading");
        gradeBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gradeBtn.setBackground(themeColor);
        gradeBtn.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(gradeBtn, gbc);

        return panel;
    }

    private void createResultsTable() {
        String[] columns = {"Student", "Total Score", "Percentage", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        resultsTable = new JTable(tableModel);
        resultsTable.setRowHeight(28);
        resultsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void changeTheme(Color color) {
        themeColor = color;
        gradeBtn.setBackground(color);
        repaint();
    }

    private void browseFolder() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            folderPathField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void startBatchGrading() {
        String path = folderPathField.getText().trim();
        if (path.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select submissions folder!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        File folder = new File(path);
        if (!folder.exists() || !folder.isDirectory()) {
            JOptionPane.showMessageDialog(this, "Invalid folder!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.setRowCount(0);
        logArea.setText("");
        log("Starting batch grading for Lab " + labCombo.getSelectedItem() + " Question " + questionCombo.getSelectedItem());

        new Thread(() -> gradeAllStudents(folder)).start();
    }

    private void gradeAllStudents(File submissionsFolder) {
        List<File> studentFiles = getStudentFiles(submissionsFolder);
        if (studentFiles.isEmpty()) {
            log("No .java files found!");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(6);
        List<Future<StudentResult>> futures = new ArrayList<>();

        for (File file : studentFiles) {
            String name = getStudentName(file.getName());
            futures.add(executor.submit(() -> gradeSingleStudent(file, name)));
        }

        int fullMarks = 0;
        for (Future<StudentResult> future : futures) {
            try {
                StudentResult result = future.get();
                SwingUtilities.invokeLater(() -> addResultToTable(result));
                if (result.totalScore == 100) fullMarks++;
                log("Completed: " + result.studentName + " → " + result.totalScore + "/100");
            } catch (Exception e) {
                log("Error: " + e.getMessage());
            }
        }

        executor.shutdown();
        log("\n✅ Batch grading completed! " + fullMarks + " students got full marks.");
    }

    private List<File> getStudentFiles(File folder) {
        List<File> files = new ArrayList<>();
        File[] all = folder.listFiles((d, n) -> n.toLowerCase().endsWith(".java"));
        if (all != null) files.addAll(Arrays.asList(all));
        files.sort(Comparator.comparing(File::getName));
        return files;
    }

    private String getStudentName(String filename) {
        return filename.replaceAll("(?i)_?Main\\.java$", "").replaceAll("(?i)\\.java$", "").trim();
    }

    private StudentResult gradeSingleStudent(File file, String studentName) {
        try {
            // Clean old class files
            cleanOldClassFiles(file.getParentFile());

            Files.copy(file.toPath(), Paths.get("Main.java"), StandardCopyOption.REPLACE_EXISTING);

            if (new ProcessBuilder("javac", "Main.java").start().waitFor() != 0) {
                return new StudentResult(studentName, 0, "Compilation Failed");
            }

            List<ITestCase> tests = new ExamFinalTestSuite253().getAllTests(Problem.SECTION_1);
            int total = 0;
            for (ITestCase t : tests) {
                if (t.runTest()) total += t.getPoints();
            }

            saveStudentReport(studentName, total);
            return new StudentResult(studentName, total, total == 100 ? "Excellent" : "Completed");

        } catch (Exception e) {
            return new StudentResult(studentName, 0, "Error");
        }
    }

    private void cleanOldClassFiles(File dir) {
        try {
            Files.walk(dir.toPath())
                 .filter(p -> p.toString().endsWith(".class"))
                 .forEach(p -> { try { Files.deleteIfExists(p); } catch (Exception ignored) {} });
        } catch (Exception ignored) {}
    }

    private void addResultToTable(StudentResult r) {
        tableModel.addRow(new Object[]{r.studentName, r.totalScore, 
                         String.format("%.1f%%", r.totalScore / 1.0), r.status});
    }

    private void saveStudentReport(String name, int score) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filename = "reports/" + name.replaceAll("[^a-zA-Z0-9._-]", "_") + "_" + timestamp + "_report.txt";
            // ... write report (can be expanded)
        } catch (Exception ignored) {}
    }

    private void openReportsFolder() {
        try {
            File dir = new File("reports");
            dir.mkdirs();
            Desktop.getDesktop().open(dir);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot open folder.");
        }
    }

    private void log(String msg) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(msg + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }

    static class StudentResult {
        String studentName;
        int totalScore;
        String status;

        StudentResult(String name, int score, String status) {
            this.studentName = name;
            this.totalScore = score;
            this.status = status;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LabGraderApp());
    }
}
