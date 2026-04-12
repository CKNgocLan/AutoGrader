import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ColorScaleFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StudentGraderUI extends JFrame {

    private JTextField folderPathField;
    private JButton browseButton, gradeButton, openReportsButton;
    private JComboBox<String> labComboBox;
    private JComboBox<String> questionComboBox;
    private JTextArea logArea;
    private JButton clearLogButton;
    
    private Map<String, List<String>> labQuestionsMap = new LinkedHashMap<>();
    private Map<String, String> testSuiteMap = new HashMap<>(); // question -> TestSuite Class Name

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
        
        // Lab and Question drop down
        labComboBox = new JComboBox<>();
        questionComboBox = new JComboBox<>();

        topPanel.add(new JLabel("Submission Folder:"));
        topPanel.add(folderPathField);
        topPanel.add(browseButton);
        // LAB drop down
        topPanel.add(new JLabel("     Lab:"));
        topPanel.add(labComboBox);
        // QUESTION drop down
        topPanel.add(new JLabel("     Question:"));
        topPanel.add(questionComboBox);
        
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
        
        // Update questions when lab changes
        labComboBox.addActionListener(e -> updateQuestionComboBox());
        initializeTestSuites();
        initializeComboBoxes();

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
    
	private void initializeTestSuites() {
		// === ADD YOUR LABS AND QUESTIONS HERE ===
		// Format: Lab Name -> List of Questions
		labQuestionsMap.put("Lab 1 - Basic OOP", Arrays.asList(
				"Q1 - Constructor"
				, "Q2 - Getter & Setter"
				, "Q3 - Simple Calculator"
			)
		);

		labQuestionsMap.put("Lab 2 - Inheritance", Arrays.asList("Q1 - Animal Hierarchy", "Q2 - Shape Abstract Class"));

		labQuestionsMap.put("Lab 3 - Arrays & Collections", Arrays.asList("Q1 - Student Array", "Q2 - Dynamic List"));

		// Map question name to actual TestSuite class name (without .java)
		testSuiteMap.put("Q1 - Constructor", "ConstructorTestSuite");
		testSuiteMap.put("Q2 - Getter & Setter", "GetterSetterTestSuite");
		testSuiteMap.put("Q3 - Simple Calculator", "CalculatorTestSuite");
		testSuiteMap.put("Q1 - Animal Hierarchy", "AnimalTestSuite");
		testSuiteMap.put("Q2 - Shape Abstract Class", "ShapeTestSuite");
		testSuiteMap.put("Q1 - Student Array", "ArrayTestSuite");
		testSuiteMap.put("Q2 - Dynamic List", "CollectionTestSuite");
	}
    
    private void initializeComboBoxes() {
        for (String lab : labQuestionsMap.keySet()) {
            labComboBox.addItem(lab);
        }
        // Trigger first update
        updateQuestionComboBox();
    }
    
	private void updateQuestionComboBox() {
		String selectedLab = (String) labComboBox.getSelectedItem();
		if (selectedLab == null)
			return;

		questionComboBox.removeAllItems();
		for (String question : labQuestionsMap.get(selectedLab)) {
			questionComboBox.addItem(question);
		}
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
        
		String selectedQuestion = (String) questionComboBox.getSelectedItem();
		if (selectedQuestion == null) {
			JOptionPane.showMessageDialog(this, "Please select a Question!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		System.out.println("Selected Question: " + selectedQuestion);

		String testSuiteClassName = testSuiteMap.get(selectedQuestion);
		if (testSuiteClassName == null) {
			log("❌ No test suite found for selected question.");
			return;
		}
		System.out.println("Selected Test Suite: " + testSuiteClassName);
		
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
                int totalScore = scores.stream().mapToInt(Integer::intValue).sum();
                
                // Generate Excel Report
                generateExcelReport("student Name", "student ID", "student Email", totalScore, tests, scores, passedList);

                // Step 3: Generate report
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
    
	// ====================== EXCEL REPORT ======================
	private static void generateExcelReport(String name, String id, String email, int totalScore, List<TestCase> tests,
			List<Integer> scores, List<Boolean> passed) {
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Lab Result");

			// Styles
			CellStyle headerStyle = createHeaderStyle(workbook);
			CellStyle fullStyle = createFullPointsStyle(workbook);
			CellStyle zeroStyle = createZeroPointsStyle(workbook);

			// Header Row
			Row headerRow = sheet.createRow(0);
			int col = 0;

			headerRow.createCell(col++).setCellValue("Student Name");
			headerRow.createCell(col++).setCellValue("Student ID");
			headerRow.createCell(col++).setCellValue("Email");
			headerRow.createCell(col++).setCellValue("Total Score");
			headerRow.createCell(col++).setCellValue("Percentage");
			headerRow.createCell(col++).setCellValue("Status");

			for (TestCase t : tests) {
				Cell cell = headerRow.createCell(col++);
				cell.setCellValue(t.getName() + " (" + t.getPoints() + " pts)");
				cell.setCellStyle(headerStyle);
			}

			// Data Row
			Row dataRow = sheet.createRow(1);
			col = 0;

			dataRow.createCell(col++).setCellValue(name);
			dataRow.createCell(col++).setCellValue(id);
			dataRow.createCell(col++).setCellValue(email);
			dataRow.createCell(col++).setCellValue(totalScore);
			dataRow.createCell(col++).setCellValue(String.format("%.1f%%", totalScore / 1.0));
			dataRow.createCell(col++).setCellValue(
					(totalScore == 100) ? "Excellent" : (totalScore >= 70 ? "Good" : "Needs Improvement"));

			// Per-testcase scores with formatting
			for (int i = 0; i < tests.size(); i++) {
				Cell cell = dataRow.createCell(col++);
				int pts = scores.get(i);
				cell.setCellValue(pts);

				if (pts == tests.get(i).getPoints()) {
					cell.setCellStyle(fullStyle);
				} else if (pts == 0) {
					cell.setCellStyle(zeroStyle);
				}
			}

			// Auto-size columns
			for (int i = 0; i < col; i++) {
				sheet.autoSizeColumn(i);
			}

			// Color scale for Total Score
			applyColorScale(sheet, 1);

			// Save file
			String fileName = Constants.REPORTS_DIR + "/" + sanitize(name) + "_report.xlsx";
			try (FileOutputStream fos = new FileOutputStream(fileName)) {
				workbook.write(fos);
			}

			System.out.println("✅ Excel report generated successfully!");

		} catch (Exception e) {
			System.out.println("Warning: Could not generate Excel report: " + e.getMessage());
		}
	}
	
	private static CellStyle createHeaderStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		org.apache.poi.ss.usermodel.Font font = wb.createFont();
		font.setBold(true);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return style;
	}

	private static CellStyle createFullPointsStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return style;
	}

	private static CellStyle createZeroPointsStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return style;
	}
	
	private static void applyColorScale(Sheet sheet, int row) {
		SheetConditionalFormatting scf = sheet.getSheetConditionalFormatting();
		CellRangeAddress range = new CellRangeAddress(row, row, 3, 3); // Total Score column (index 3)

		ConditionalFormattingRule rule = scf.createConditionalFormattingColorScaleRule();
		ColorScaleFormatting csf = rule.getColorScaleFormatting();
		csf.setNumControlPoints(3);

		ConditionalFormattingThreshold min = csf.createThreshold();
		min.setRangeType(ConditionalFormattingThreshold.RangeType.MIN);

		ConditionalFormattingThreshold mid = csf.createThreshold();
		mid.setRangeType(ConditionalFormattingThreshold.RangeType.NUMBER);
		mid.setValue(50.0);

		ConditionalFormattingThreshold max = csf.createThreshold();
		max.setRangeType(ConditionalFormattingThreshold.RangeType.MAX);

//		csf.setColors(new org.apache.poi.ss.usermodel.Color[] { IndexedColors.RED.getIndex(), IndexedColors.YELLOW.getColor(),
//				IndexedColors.GREEN.getColor() });

		scf.addConditionalFormatting(new CellRangeAddress[] { range }, rule);
	}
	
	private static String sanitize(String str) {
        return str.replaceAll("[^a-zA-Z0-9._-]", "_");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentGraderUI().setVisible(true);
        });
    }
}