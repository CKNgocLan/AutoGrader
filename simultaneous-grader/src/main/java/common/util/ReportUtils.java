package common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
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
import common.constant.DateTimeFormatters;
import common.constant.FileExtension;
import common.constant.ProblemName;
import common.constant.Symbol;
import common.constant.TestingResult;
import common.constant.TopicName;
import common.constant.YearQuarter;
import common.constant.csv.TopicHeader;
import common.message.ExceptionMessage;
import common.message.GradingMessage;
import model.resultReport.TestCaseResult;

public class ReportUtils {
	public static List<CSVRecord> readCSV(String filePath) {
		try {
			return CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).setIgnoreEmptyLines(true)
					.setTrim(true).get().parse(Files.newBufferedReader(Paths.get(filePath))).getRecords();

		} catch (IOException e) {
			e.printStackTrace();
			return List.of();
		}
	}

	public static void createReportDir() {
    	Path dirPath = Paths.get(System.getProperty(Constants.USER_DIR), Constants.REPORTS_DIR);
    	if (!Files.exists(dirPath)) {
    		try {
				Files.createDirectories(dirPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }

	public static void generateExcelReport(String selectedDirectoryName, String topic, String problem, List<TestCaseResult> results) {
//	public static void generateExcelReport(String selectedDirectoryName, String topic, String problem, List<TestCaseResult> results) {
		createReportDir();

		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet(topic);

			// Create styles
			CellStyle headerStyle = createHeaderStyle(workbook);
			CellStyle passedStyle = createPassedStyle(workbook); // Green
			CellStyle failedStyle = createFailedStyle(workbook); // Red

			// Header row
			Row headerRow = sheet.createRow(0);
			String[] headers = { "No.", "Test Case Name", "Result", "Feedback" };
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(headerStyle);
			}

			// Data rows - one test case per row (vertical)
			int rowNum = 1;

			int passedCounter = 0;
			for (TestCaseResult result : results) {
				Row row = sheet.createRow(rowNum++);
				int col = 0;

				// No.
				row.createCell(col++).setCellValue(results.indexOf(result) + 1);

				// Test Case Name
				row.createCell(col++).setCellValue(result.testName());

				// Max Points
				// row.createCell(col++).setCellValue(result.maxPoints);
				
				// Earned Points
				// row.createCell(col++).setCellValue(result.earnedPoints);
				
				// Result + Color
				Cell resultCell = row.createCell(col++);
				if (result.passed()) {
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
					row.createCell(col++).setCellValue(result.feedback());
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
				//colIndex++;
				org.apache.poi.ss.usermodel.Cell testcaseCell = aggregationRow.createCell(colIndex++);
				testcaseCell.setCellValue(MessageFormat.format("{0}/{1} testcases", passedCounter, results.size()));
				testcaseCell.setCellStyle(resultStyle);

				// Result
				org.apache.poi.ss.usermodel.Cell resultCell = aggregationRow.createCell(colIndex++);
				resultCell.setCellValue(
						MessageFormat.format("{0}%", String.valueOf(((double) passedCounter / results.size()) * 100)));
				resultCell.setCellStyle(resultStyle);

				// Feedback
				colIndex++;
			}

			// Auto-size columns
			for (int i = 0; i < 5; i++) {
				sheet.autoSizeColumn(i);
			}

			// Generate dynamic filename: directory_lab_question_timestamp_report.xlsx
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimeFormatters.yyyy_MM_dd_HH_mm_ss));
//			String safeLab = (topic == null || topic.isEmpty()) ? TopicName.EMPTY : topic.replaceAll(Constants.SAFE_STRING_REGEX, Constants.UNDERSCORE);
//			String safeQ = (problem == null || problem.isEmpty()) ? ProblemName.EMPTY : problem.replaceAll(Constants.SAFE_STRING_REGEX, Constants.UNDERSCORE);
//			String safeDir = StringUtils.toSafeName(selectedDirectoryName);
//			String safeLab = StringUtils.toSafeName(topic);
//			String safeQ = StringUtils.toSafeName(problem);

			String fileName = Constants.OOP + Constants.UNDERSCORE + YearQuarter.Y25Q3
					+ Symbol.HYPHEN + StringUtils.toSafeName(selectedDirectoryName)
					+ Symbol.HYPHEN + StringUtils.toSafeName(topic)
					+ Symbol.HYPHEN + StringUtils.toSafeName(problem)
					+ Constants.UNDERSCORE + timestamp
					+ FileExtension.XLSX;

			String excelFile = Constants.REPORTS_DIR + Symbol.FORE_SLASH + fileName;

			try (FileOutputStream fos = new FileOutputStream(excelFile)) {
				workbook.write(fos);
			}

			System.out.println("Excel report generated: " + fileName);

		} catch (Exception e) {
			System.out.println(ExceptionMessage.EXCEL_REPORT_GENERATION_ERROR.withMessage(e));
			e.printStackTrace();
		}
	}

	public static void generateTopicCSVResult(File topicDirFile, String topic, List<Object[]> rows) {
	    // first create file object for file placed at location specified by filepath
	    File file = new File(FileExtension.CSV.toAbsoluteFileResultPath(topicDirFile.toString(), StringUtils.toLowerCaseNoSpace(topic)));

	    try {
	        // create FileWriter object with file as parameter
	        FileWriter outputfile = new FileWriter(file);
	        BufferedWriter writer = new BufferedWriter(outputfile);

	        // 1. Write the header row
	        String[] headers = TopicHeader.withProblems(ProblemName.P1, ProblemName.P2, ProblemName.P3);
            writer.write(convertToCsvRow(headers));
            writer.newLine();
	        	
            // 2. Write the data rows
            for (Object[] row : rows) {
            	writer.write(convertToCsvRow(row));
                writer.newLine();
            }

            writer.close();
            System.out.println(GradingMessage.GENERATE_CSV_REPORT_SUCCESSFULLY.getContent(topicDirFile.getAbsolutePath(), file.getName()));
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static String convertToCsvRow(Object[] fields) {
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            String field = String.valueOf(fields[i]);
            
            // If the text contains a comma, quote, or newline, wrap it in double quotes
            if (field.contains(Symbol.COMMA) || field.contains(Symbol.DOUBLE_QUOTE) || field.contains(Symbol.NEWLINE)) {
                field = Symbol.DOUBLE_QUOTE + field.replace(Symbol.DOUBLE_QUOTE, Symbol.DOUBLE_DOUBLE_QUOTE) + Symbol.DOUBLE_QUOTE;
            }
            
            row.append(field);
            // Separate with a comma
            if (i < fields.length - 1) {
                row.append(Symbol.COMMA);
            }
        }
        return row.toString();
    }

    // Helper styles
    private static CellStyle createHeaderStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    // PASSED Cell Style
    private static CellStyle createPassedStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    // FAILED Cell Style
    private static CellStyle createFailedStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());   // Light red
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
}
