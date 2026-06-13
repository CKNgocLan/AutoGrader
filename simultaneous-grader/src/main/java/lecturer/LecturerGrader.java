package lecturer;
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

import common.constant.Constants;
import common.constant.FileExtension;
import common.constant.TopicName;
import common.constant.Midterm;
import common.constant.ProblemName;
import common.constant.TestingResult;
import common.message.GradingMessage;
import common.util.PathUtils;
import common.util.StringUtils;
import model.component.StudentList;
import model.component.TestCase;

public class LecturerGrader extends JFrame {
	private static final long serialVersionUID = 3700796113357733984L;
	
	private JTextField folderPathField;
    private JButton browseButton, openReportsButton, gradeButton, lab1Button, lab2Button, lab3Button;
    private JComboBox<String> labComboBox;
//    private JComboBox<String> questionComboBox;
    private JTextArea logArea;
    private JButton clearLogButton;
    
    private TestSuiteRouter testSuiteRouter;
    
    private Map<String, List<String>> labQuestionsMap = new LinkedHashMap<>();

    public LecturerGrader() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
        setTitle("Grader - 253");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setVisible(false);
        
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // TODO background color
        Color panelBackgroundColor = new Color(239, 246, 255); // light blue
        panelBackgroundColor = new Color(255,239,239); // light red

        Color primaryBackgroundColor = new Color(252, 253, 255);
		primaryBackgroundColor = new Color(255, 239, 239);
        getContentPane().setBackground(primaryBackgroundColor);
        
		Color clearLogButtonBackgroundColor = new Color(208, 18, 18);
		Color clearLogButtonForegroundColor = Color.WHITE;// new Color(255, 129, 0);
		
		Color openReportsButtonBackgroundColor = new Color(0, 103, 26);
		Color openReportsButtonForegroundColor = Color.WHITE;// new Color(0, 103, 26);

        // ==================== TOP PANEL ====================
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        topPanel.setBorder(BorderFactory.createTitledBorder("Student Submission Folder"));

        folderPathField = new JTextField(50);
        folderPathField.setText(System.getProperty(Constants.USER_DIR)); // default hint

        browseButton = new JButton("Browse Folder...");
        browseButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        browseButton.setBackground(new Color(21, 128, 61));
        browseButton.setForeground(Color.WHITE);
        
        gradeButton = new JButton("Start Grading");
        gradeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gradeButton.setBackground(new Color(208, 18, 18));
        gradeButton.setForeground(Color.WHITE);

        lab1Button = createGradingLabButton(TopicName.L1);
        lab2Button = createGradingLabButton(TopicName.L2);
        lab3Button = createGradingLabButton(TopicName.L3);
        
        // Lab and Question drop down
        labComboBox = new JComboBox<>();
        labComboBox.setBackground(Color.WHITE);
        labComboBox.setForeground(Color.DARK_GRAY);
        
//        questionComboBox = new JComboBox<>();
//        questionComboBox.setBackground(Color.WHITE);
//        questionComboBox.setForeground(Color.DARK_GRAY);

        topPanel.add(new JLabel("Submission Folder:"));
        topPanel.add(folderPathField);
        topPanel.add(browseButton);
        // LAB drop down
        JLabel labLabel = new JLabel("     Topic:");
        topPanel.add(labLabel);

        topPanel.add(labComboBox);

//        // QUESTION drop down
//        topPanel.add(new JLabel("     Question:"));
//        topPanel.add(questionComboBox);
        
        topPanel.add(gradeButton);
        topPanel.add(lab1Button);
        topPanel.add(lab2Button);
        topPanel.add(lab3Button);

        // ==================== CENTER: Log Area ====================
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        logArea.setMargin(new Insets(10, 10, 10, 10));
        logArea.setBackground(Color.WHITE);

        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setBorder(BorderFactory.createTitledBorder("Grading Progress & Results"));

        // ==================== BOTTOM PANEL ====================
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        openReportsButton = new JButton("📁 Open Reports Folder");
        openReportsButton.setBackground(openReportsButtonBackgroundColor);
        openReportsButton.setForeground(openReportsButtonForegroundColor);
        
        clearLogButton = new JButton("Clear Log");
        clearLogButton.setBackground(clearLogButtonBackgroundColor);
        clearLogButton.setForeground(clearLogButtonForegroundColor);
        
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

        // TODO Event Listeners
        browseButton.addActionListener(this::browseFolder);
        gradeButton.addActionListener(this::startGrading);
        openReportsButton.addActionListener(e -> openReportsFolder());
        clearLogButton.addActionListener(e -> logArea.setText(""));
        lab1Button.addActionListener(this::gradeLab);
        lab2Button.addActionListener(this::gradeLab);
        lab3Button.addActionListener(this::gradeLab);

        log("Student Submission Grader Instructions:\n");
        log("1. DELETE PACKAGE STATEMENT (first line)");
        log("2. PUT ALL your .java files in one folder but NOT APPLICATION FILE containing main()");
        log("3. Select that folder using Browse");
        log("4. Click \"Start Grading\"");
        log("5. Check the detailed report in reports/ folder\n");
        
        topPanel.setBackground(panelBackgroundColor);
        logScroll.setBackground(panelBackgroundColor);
        bottomPanel.setBackground(panelBackgroundColor);
    }

    private void gradeLab(ActionEvent e) {
    	String lab = e.getActionCommand();
    	System.out.println("Start grading " + lab);
    }
    
    private JButton createGradingLabButton(String lab) {
        JButton btn = new JButton(lab);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(208, 18, 18));
        btn.setForeground(Color.WHITE);

        return btn;
    }
    
	private void initializeTestSuites() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		// === ADD YOUR LABS AND QUESTIONS HERE ===
		// Format: Lab Name -> List of Questions
//		labQuestionsMap.put(FinalExam.FINAL_253, Arrays.asList(Question.SECTION_2, Question.SECTION_1));
//		labQuestionsMap.put(Lab.L4, Arrays.asList(Question.Q1, Question.Q4));
		labQuestionsMap.put(Midterm.MIDTERM_253, Arrays.asList(ProblemName.P1));
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

//		questionComboBox.removeAllItems();
//		for (String question : labQuestionsMap.get(selectedLab)) {
//			questionComboBox.addItem(question);
//		}
	}

    private void browseFolder(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setCurrentDirectory(new File(folderPathField.getText()));

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            folderPathField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private File validateLabSubmissionPath() {
    	File submissionFolder = new File(folderPathField.getText().trim());

        if (!submissionFolder.exists() || !submissionFolder.isDirectory()) {
            JOptionPane.showMessageDialog(this, 
                GradingMessage.FOLDER_CONTAINING_JAVA_FILE_NOT_FOUND.getContent()
                , GradingMessage.ERROR.getContent()
                , JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return submissionFolder;
    }

    private void gradeLab3(ActionEvent e) {
    	File submissionDir = validateLabSubmissionPath();
    	if(submissionDir == null) {
    		return;
    	}

    	String path = folderPathField.getText().trim();
		String selectedLab = TopicName.L3;

		// Clear previous log
        logArea.setText(Constants.EMPTY_STRING);
        log(GradingMessage.STARTING_GRADING_FOR_FOLDER.getContent(submissionDir.getName()));
        log(GradingMessage.COMPILING_JAVA_FILES_NEWLINE.getContent());
        log("Grading Lab 3...");
//        gradeButton.setEnabled(false);

     // Step 1: Compile all student's .java files
        boolean compileSuccess = compileStudentFiles(submissionDir);
    }

    private void startGrading(ActionEvent e) {
        String path = folderPathField.getText().trim();
        File submissionFolder = new File(path);

        if (!submissionFolder.exists() || !submissionFolder.isDirectory()) {
            JOptionPane.showMessageDialog(this, 
                GradingMessage.FOLDER_CONTAINING_JAVA_FILE_NOT_FOUND.getContent()
                , GradingMessage.ERROR.getContent()
                , JOptionPane.ERROR_MESSAGE);
            return;
        }

		String selectedLab = (String) labComboBox.getSelectedItem();
		if (StringUtils.isNullOrEmpty(selectedLab)) {
			JOptionPane.showMessageDialog(this
					, GradingMessage.PLEASE_SELECT_LAB.getContent()
					, GradingMessage.WARNING.getContent()
					, JOptionPane.WARNING_MESSAGE);
			return;
		}
        
		// TODO remove selecting question
		String selectedQuestion = "";
//		if (StringUtils.isNullOrEmpty(selectedQuestion)) {
//			JOptionPane.showMessageDialog(this
//					, GradingMessage.PLEASE_SELECT_QUESTION.getContent()
//					, GradingMessage.WARNING.getContent()
//					, JOptionPane.WARNING_MESSAGE);
//			return;
//		}

        // TODO loop through submission directory
        // Clear previous log
        logArea.setText(Constants.EMPTY_STRING);
        log(GradingMessage.STARTING_GRADING_FOR_FOLDER.getContent(submissionFolder.getName()));
        log(GradingMessage.COMPILING_JAVA_FILES_NEWLINE.getContent());

        gradeButton.setEnabled(false);

        new Thread(() -> {
            try {
                // Step 1: Compile all student's .java files
                boolean compileSuccess = compileStudentFiles(submissionFolder);

                if (!compileSuccess) {
                    log(GradingMessage.COMPILATION_FAILED.getContent());
                    generateCompilationErrorReport(submissionFolder.getName(), selectedLab, selectedQuestion);
                    SwingUtilities.invokeLater(() -> gradeButton.setEnabled(true));
                    return;
                }

                log(GradingMessage.COMPILATION_SUCCESSFUL_NEWLINE.getContent());

                // Step 2: Run tests
                List<TestCase> tests = testSuiteRouter.invokeAllTests(selectedLab, selectedQuestion);
                if (tests == null) {
                	SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(this, 
                        		GradingMessage.ERROR_CHECK_TERMINAL.getContent()
                        		, GradingMessage.UPPERCASE_ATTENTION.getContent()
                        		, JOptionPane.WARNING_MESSAGE);
                        gradeButton.setEnabled(true);
                    });
                	return;
                } else if (tests.size() == 0) {
                	SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(this, 
                        		GradingMessage.TEST_SUITE_UNDER_CONSTRUCTION.getContent(selectedLab, selectedQuestion)
                        		, GradingMessage.UPPERCASE_ATTENTION.getContent()
                        		, JOptionPane.WARNING_MESSAGE);
                        gradeButton.setEnabled(true);
                    });
                	return;
                }

                log(GradingMessage.RUNNING_TEST_CASES_NEWLINE.getContent());
                List<Integer> scores = new ArrayList<>();
                List<Boolean> passedList = new ArrayList<>();
                List<TestResult> results = new ArrayList<>();

                for (TestCase test : tests) {
//                    log("→ " + test.getName() + " (" + test.getPoints() + " pts) ... ");
                    log("→ " + test.getName() + " ... ");
                    boolean passed = test.runTest();
                    int points = passed ? test.getPoints() : 0;
                    scores.add(points);
                    if (passed) {
                    	passedList.add(passed);
                    }

                    log(passed ? TestingResult.PASSED : TestingResult.FAILED);
                    results.add(new TestResult(test.getName(), test.getPoints(), passed ? test.getPoints() : 0, passed, test.getFeedback()));
                }
                int totalScore = scores.stream().mapToInt(Integer::intValue).sum();
                
                // create REPORTS directory
                createReportDir();
                
                // Step 3: Generate Excel Report
                generateExcelReport(submissionFolder.getName(), selectedLab, selectedQuestion, results);

                log(Constants.ASSIGN.repeat(60));

                if (totalScore/5 == tests.size()) {
                    log(GradingMessage.EXCELLENT_ALL_TESTS_PASSED.getContent());
                } else {
                    log(GradingMessage.GOOD_WORK_REVIEW_FAILED_TESTS.getContent());
                }

                log("\n" + GradingMessage.DETAILED_REPORT_SAVED_IN_FOLDER.getContent(Constants.REPORTS_DIR));
                log("\n" + GradingMessage.PASSED_TESTCASE_RATE.getContent(passedList.size(), tests.size()));
                log("\n" + GradingMessage.PERCENTAGE.getContent(Double.valueOf(passedList.size()) / tests.size()));

                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this,
                        GradingMessage.GRADING_COMPLETED_WITH_PASSED_TESTCASE.getContent(passedList.size(), tests.size())
                        , GradingMessage.SUCCESS.getContent()
                        , JOptionPane.INFORMATION_MESSAGE);
                    gradeButton.setEnabled(true);
                });
            } catch (Exception ex) {
                log("\n" + GradingMessage.UNEXPECTED_ERROR_WITH_MESSAGE.getContent(ex.getMessage()));
                gradeButton.setEnabled(true);
            }
        }).start();
    }

    // Compile all .java files in the selected folder
    private boolean compileStudentFiles(File folder) {
        try {
            List<String> javaFiles = new ArrayList<>();
            Files.walk(folder.toPath())
                 .filter(p -> p.toString().endsWith(FileExtension.JAVA.extension()))
                 .forEach(p -> javaFiles.add(p.toString()));

            if (javaFiles.isEmpty()) {
                log(GradingMessage.NO_JAVA_FILE_FOUND_IN_FOLDER.getContent(folder.getName()));
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
                log(GradingMessage.COMPILATION_ERRORS_WITH_MESSAGE.getContent(Constants.EMPTY_STRING));
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
            log(GradingMessage.COMPILATION_ERRORS_WITH_MESSAGE.getContent(e.getMessage()));
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
            
            String fileName = Constants.REPORTS_DIR + "/" + "OOP_253-" + safeDir + "-" + safeLab + "-" + safeQ + "_" + timestamp + "_report.txt";
            
//            String fileName = Constants.REPORTS_DIR + "/" + "OOP_253-" + safeDir + "_" + timestamp + "_report.txt";
            Files.write(Paths.get(fileName), content.getBytes("UTF-8"));
        } catch (Exception ignored) {}
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

            int passedCounter = 0;
			for (TestResult result : results) {
				Row row = sheet.createRow(rowNum++);
				int col = 0;

				// No.
				row.createCell(col++).setCellValue(results.indexOf(result) + 1);

				// Test Case Name
				row.createCell(col++).setCellValue(result.testName);

				// Max Points
				// row.createCell(col++).setCellValue(result.maxPoints);

				// Earned Points
				// row.createCell(col++).setCellValue(result.earnedPoints);

				// Result + Color
				Cell resultCell = row.createCell(col++);
				if (result.passed) {
					passedCounter++;
					
					// Result
					resultCell.setCellValue(TestingResult.PASSED);

					// Apply color
					resultCell.setCellStyle(passedStyle);
				} else {
					// Result
					resultCell.setCellValue(TestingResult.FAILED);
					
					resultCell.setCellStyle(failedStyle);

					// Feedback
					row.createCell(col++).setCellValue(result.feedback);
				}
			}
			
			// create aggregation row
			{
				Row aggregationRow = sheet.createRow(rowNum);
				int colIndex = 0;
				
				// aggregation row style
				org.apache.poi.ss.usermodel.Font resultFont = workbook.createFont();
				resultFont.setBold(true);
				resultFont.setItalic(true);
				resultFont.setColor(IndexedColors.WHITE.getIndex());
				
				CellStyle resultStyle = workbook.createCellStyle();
				resultStyle.setAlignment(HorizontalAlignment.CENTER);
				resultStyle.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
				resultStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				resultStyle.setFont(resultFont);
				
				// No
				colIndex++;
				
				// Test Case Name
//				colIndex++;
				org.apache.poi.ss.usermodel.Cell testcaseCell = aggregationRow.createCell(colIndex++);
				testcaseCell.setCellValue(MessageFormat.format("{0}/{1} testcases", passedCounter, results.size()));
				testcaseCell.setCellStyle(resultStyle);
				
				// Result
				org.apache.poi.ss.usermodel.Cell resultCell = aggregationRow.createCell(colIndex++);
				resultCell.setCellValue(MessageFormat.format("{0}%", String.valueOf(((double)passedCounter/results.size())*100)));
				resultCell.setCellStyle(resultStyle);
				
				// Feedback
				colIndex++;
			}

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
		StudentList.setFilePath("D:\\eclipse-wksp\\AutoGrader\\auto-grader\\cse203-participants-253.csv");
        SwingUtilities.invokeLater(() -> {
        	try {
				new LecturerGrader().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
        });
    }
}