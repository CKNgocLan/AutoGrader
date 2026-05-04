package student;

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
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import student.constant.Constants;
import student.constant.Lab;
import student.constant.Question;
import student.model.ITestCase;
import student.util.PathUtils;

public class StudentGraderUI extends JFrame {
	private static final long serialVersionUID = 3700796113357733984L;
	
	private JTextField folderPathField;
    private JButton browseButton, gradeButton, openReportsButton;
    private JComboBox<String> labComboBox;
    private JComboBox<String> questionComboBox;
    private JTextArea logArea;
    private JButton clearLogButton;
    
    private TestSuiteRouter testSuiteRouter;
    
    private Map<String, List<String>> labQuestionsMap = new LinkedHashMap<>();

    public StudentGraderUI() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
        setTitle("Student Self-Grader - Java Lab");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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
        
        testSuiteRouter = new TestSuiteRouter();

        // Event Listeners
        browseButton.addActionListener(this::browseFolder);
        gradeButton.addActionListener(this::startGrading);
        openReportsButton.addActionListener(e -> openReportsFolder());
        clearLogButton.addActionListener(e -> logArea.setText(""));

        log("Student Submission Grader Instructions:\n");
        log("1. DELETE PACKAGE STATEMENT (first line)");
        log("2. PUT ALL your .java files in one folder but NOT APPLICATION FILE containing main()");
        log("3. Select that folder using Browse");
        log("4. Click \"Start Grading\"");
        log("5. Check the detailed report in reports/ folder\n");
    }
    
	private void initializeTestSuites() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		// === ADD YOUR LABS AND QUESTIONS HERE ===
		// Format: Lab Name -> List of Questions
//		labQuestionsMap.put(Lab.L1, Arrays.asList(Question.Q0));
		labQuestionsMap.put(Lab.L2, Arrays.asList(Question.Q5));
//		labQuestionsMap.put(Lab.L2, Arrays.asList(Question.Q1, Question.Q2, Question.Q3, Question.Q4, Question.Q5));
//		for (String lab : Lab.getNameList()) {
//			labQuestionsMap.put(lab, Question.getNameList(6));
//		}
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
        
		String selectedLab = (String) labComboBox.getSelectedItem();
		if (selectedLab == null) {
			JOptionPane.showMessageDialog(this, "Please select a Lab!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
        
		String selectedQuestion = (String) questionComboBox.getSelectedItem();
		if (selectedQuestion == null) {
			JOptionPane.showMessageDialog(this, "Please select a Question!", "Warning", JOptionPane.WARNING_MESSAGE);
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
                    generateCompilationErrorReport(submissionFolder.getName(), selectedLab, selectedQuestion);
                    SwingUtilities.invokeLater(() -> gradeButton.setEnabled(true));
                    return;
                }

                log("Compilation successful!\n");

                // Step 2: Run tests
                // TODO retrieve test suite based on selected lab & question 
                List<ITestCase> tests = testSuiteRouter.invokeAllTests(selectedLab, selectedQuestion);
                if (tests == null || tests.size() == 0) {
                	SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(this, 
                        		MessageFormat.format("TEST SUITE IS UNDER CONSTRUCTION!\n({0} - {1})", selectedLab, selectedQuestion), 
                            "ATTENTION", JOptionPane.WARNING_MESSAGE);
                        gradeButton.setEnabled(true);
                    });
                	return;
                }

                log("Running your test cases...\n");
                List<Integer> scores = new ArrayList<>();
                List<Boolean> passedList = new ArrayList<>();
                List<TestResult> results = new ArrayList<>();

                for (ITestCase test : tests) {
//                    log("→ " + test.getName() + " (" + test.getPoints() + " pts) ... ");
                    log("→ " + test.getName() + " ... ");
                    boolean passed = test.runTest();
                    int points = passed ? test.getPoints() : 0;
                    scores.add(points);
                    passedList.add(passed);

                    log(passed ? "PASSED" : "FAILED");
                    results.add(new TestResult(test.getName(), test.getPoints(), passed ? test.getPoints() : 0, passed, test.getFeedback()));
                }
                int totalScore = scores.stream().mapToInt(Integer::intValue).sum();
                
                // create REPORTS directory
                createReportDir();
                
                // Generate Excel Report
                generateExcelReport(submissionFolder.getName(), selectedLab, selectedQuestion, results);

                // Step 3: Generate report
                generateStudentReport(submissionFolder.getName(), selectedLab, selectedQuestion, totalScore, tests, scores, passedList);

                // TODO
//                log("\n" + "=".repeat(60));
//                log(String.format("FINAL SCORE: %d / 100", totalScore));
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
//                        "Grading Completed!\n\nYour Score: " + totalScore + "/100\n\nCheck the reports folder for details.", 
                        "Grading Completed!\n\nCheck the reports folder for details.", 
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
            
            // TODO check and remove package statement

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

    private void generateCompilationErrorReport(String folderName, String selectedLab, String selectedQuestion) {
        try {
            String content = "Student Submission: " + folderName + "\n" +
                            "Date: " + LocalDateTime.now() + "\n\n" +
                            "Compilation Failed!\n" +
                            "Please check your code for syntax or compilation errors.\n";

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss"));
            
            String safeDir = folderName.replaceAll("[^a-zA-Z0-9._-]", "_");
            String safeLab = (selectedLab == null || selectedLab.isEmpty()) ? "Lab" : selectedLab.replaceAll("[^a-zA-Z0-9._-]", "_");
            String safeQ = (selectedQuestion == null || selectedQuestion.isEmpty()) ? "Q" : selectedQuestion.replaceAll("[^a-zA-Z0-9._-]", "_");
            
            String fileName = Constants.REPORTS_DIR + "/" + "OOP_253-" + safeDir + "-L" + safeLab + "-Q" + safeQ + "_" + timestamp + "_report.txt";
            
//            String fileName = Constants.REPORTS_DIR + "/" + "OOP_253-" + safeDir + "_" + timestamp + "_report.txt";
            Files.write(Paths.get(fileName), content.getBytes("UTF-8"));
        } catch (Exception ignored) {}
    }

    private void generateStudentReport(String folderName, 
							           String selectedLab, 
							           String selectedQuestion,
							           int totalScore, 
                                       List<ITestCase> tests, 
                                       List<Integer> scores, 
                                       List<Boolean> passed) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("===========================================\n");
            sb.append("          STUDENT SELF-GRADER REPORT\n");
            sb.append("===========================================\n");
            sb.append("Folder Name   : ").append(folderName).append("\n");
            sb.append("Date          : ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
//            sb.append("Final Score   : ").append(totalScore).append("/100\n\n");

            sb.append("DETAILED TEST RESULTS:\n");
            sb.append("--------------------------------------------------\n\n");

            for (int i = 0; i < tests.size(); i++) {
                ITestCase t = tests.get(i);
                boolean p = passed.get(i);
                int score = scores.get(i);

                //TODO sb.append(String.format("%-45s %s %3d / %d pts%n", t.getName(), p ? "PASSED" : "FAILED", score, t.getPoints()));
                sb.append(String.format("%-45s %s%n", t.getName(), p ? "PASSED" : "FAILED"));

                if (!p) {
                    sb.append("   Feedback : ").append(t.getFeedback()).append("\n\n");
                } else {
                    sb.append("\n");
                }
            }

            sb.append("===========================================\n");
            //TODO sb.append("OVERALL RESULT: ").append(totalScore).append("/100\n");

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss"));
            
            String safeDir = folderName.replaceAll("[^a-zA-Z0-9._-]", "_");
            String safeLab = (selectedLab == null || selectedLab.isEmpty()) ? "Lab" : selectedLab.replaceAll("[^a-zA-Z0-9._-]", "_");
            String safeQ = (selectedQuestion == null || selectedQuestion.isEmpty()) ? "Q" : selectedQuestion.replaceAll("[^a-zA-Z0-9._-]", "_");
            
            String fileName = Constants.REPORTS_DIR + "/" + "OOP_253-" + safeDir + "-L" + safeLab + "-Q" + safeQ + "_" + timestamp + ".txt";

//            String fileName = Constants.REPORTS_DIR + "/" + folderName.replaceAll("[^a-zA-Z0-9._-]", "_") + "_report.txt";
            Files.write(Paths.get(fileName), sb.toString().getBytes("UTF-8"));

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
 // Updated generateExcelReport() - Vertical layout with colored cells
    private void generateExcelReport(String selectedDirectoryName, 
                                     String selectedLab, 
                                     String selectedQuestion, 
                                     List<TestResult> results) {
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Test Report");

            // Create styles
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle passedStyle = createPassedStyle(workbook);   // Green
            CellStyle failedStyle = createFailedStyle(workbook);   // Red

            // Header row
            Row headerRow = sheet.createRow(0);
//            String[] headers = {"No.", "Test Case Name", "Max Points", "Earned Points", "Result", "Feedback"};
            String[] headers = {"No.", "Test Case Name", "Result", "Feedback"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Data rows - one test case per row (vertical)
            int rowNum = 1;

			for (TestResult result : results) {
				Row row = sheet.createRow(rowNum++);
				int col = 0;

				// No.
				row.createCell(col++).setCellValue(results.indexOf(result) + 1);

				// Test Case Name
				row.createCell(col++).setCellValue(result.testName);

				// Max Points
				//TODO row.createCell(col++).setCellValue(result.maxPoints);

				// Earned Points
				//TODO row.createCell(col++).setCellValue(result.earnedPoints);

				// Result + Color
				Cell resultCell = row.createCell(col++);
				resultCell.setCellValue(result.passed ? "PASSED" : "FAILED");

				// Apply color
				if (result.passed) {
					resultCell.setCellStyle(passedStyle);
				} else {
					resultCell.setCellStyle(failedStyle);

					// Feedback
					row.createCell(col++).setCellValue(result.feedback);
				}
			}
			
			// create aggregation row
//			{
//				Row aggregationRow = sheet.createRow(rowNum);
//				int colIndex = 0;
//				
//				// No
//				colIndex++;
//				
//				// Test Case Name
//				colIndex++;
//				
//				// Max Points
//				aggregationRow.createCell(colIndex++).setCellValue("Total Point:");
//				
//				// Earned Points
//				aggregationRow.createCell(colIndex++).setCellValue(results.stream().mapToInt(res -> res.earnedPoints).sum());
//			}

            // Auto-size columns
            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }

            // Generate dynamic filename: directory_lab_question_timestamp_report.xlsx
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss"));
            String safeDir = selectedDirectoryName.replaceAll("[^a-zA-Z0-9._-]", "_");
            String safeLab = (selectedLab == null || selectedLab.isEmpty()) ? "Lab" : selectedLab.replaceAll("[^a-zA-Z0-9._-]", "_");
            String safeQ = (selectedQuestion == null || selectedQuestion.isEmpty()) ? "Q" : selectedQuestion.replaceAll("[^a-zA-Z0-9._-]", "_");

            String fileName = "OOP_253-" + safeDir + "-L" + safeLab + "-Q" + safeQ + "_" + timestamp + ".xlsx";

            String excelFile = Constants.REPORTS_DIR + "/" + fileName;

            try (FileOutputStream fos = new FileOutputStream(excelFile)) {
                workbook.write(fos);
            }

            System.out.println("Excel report generated: " + fileName);

        } catch (Exception e) {
            System.out.println("Error generating Excel report: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void createReportDir() throws IOException {
    	Path dirPath = Paths.get(System.getProperty(Constants.USER_DIR), Constants.REPORTS_DIR);
    	if (!Files.exists(dirPath)) {
    		Files.createDirectories(dirPath);
    	}
    }

    // Helper styles
    private CellStyle createHeaderStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    // PASSED Cell Style
    private CellStyle createPassedStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    // FAILED Cell Style
    private CellStyle createFailedStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());   // Light red
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
    // ====================== End of EXCEL REPORT ======================
	
	// ==================== Helper Class ====================
	private static class TestResult {
		private String testName;
		private int maxPoints;
		private int earnedPoints;
		private boolean passed;
		private String feedback;

		TestResult(String testName, int maxPoints, int earnedPoints, boolean passed, String feedback) {
			this.testName = testName;
			this.maxPoints = maxPoints;
			this.earnedPoints = earnedPoints;
			this.passed = passed;
			this.feedback = feedback;
		}
	}

	// ==================== End of Helper Class ====================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	try {
				new StudentGraderUI().setVisible(true);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
        });
    }
}