package lecturer;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import common.constant.TopicName;
import common.util.PathUtils;
import model.component.StudentList;
import model.component.testSuite.factory.Lab3Problem1TestSuiteFactory;
import model.component.testSuite.factory.Lab3Problem2TestSuiteFactory;
import model.exception.TesterGotNoClassNameException;
import model.resultReport.ProblemResult;
import model.service.ProblemGradingTask;
import model.service.StudentThreadPool;

public class LecturerSnippet {
	static String selectedLab = TopicName.L3;
	static String submissionDirectoryName = "sample-lab3-submission";
	static String path = Path.of(PathUtils.currentFolderPath(), submissionDirectoryName).toString();
	static String csvPath = Path.of(PathUtils.currentFolderPath(), "cse203-participants-253.csv").toString();
	static File submissionDirectory = new File(path);

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			NoSuchFieldException, TesterGotNoClassNameException {
		gradeLab3();
	}

	private static void gradeLab3() {
		StudentList.setFilePath(csvPath);
		File studentDir = submissionDirectory.listFiles()[0];
		StudentThreadPool threadPool1 = new StudentThreadPool(TopicName.L3, studentDir);
		threadPool1.addTask(new ProblemGradingTask(studentDir.listFiles()[0], new Lab3Problem1TestSuiteFactory()));
		threadPool1.addTask(new ProblemGradingTask(studentDir.listFiles()[1], new Lab3Problem2TestSuiteFactory()));
		List<ProblemResult> studentResult1 = threadPool1.submit();

//		try {
//			List<Future<ProblemResult>> futureList = new ArrayList<>();
//			for (File studentDirectory : studentDir.listFiles()) {
//				threadPool1.addTask(new ProblemGradingTask(studentDirectory, new Lab3Problem1TestSuiteFactory()));
//				threadPool1.addTask(new ProblemGradingTask(studentDirectory, new Lab3Problem2TestSuiteFactory()));
//			}
//
//			while (futureList.stream().filter(thread -> !thread.isDone()).toList().size() > 0) {
//				System.out.println("Calculating...");
//			}
//			List<ProblemResult> problemResultList = futureList.stream().map(future -> {
//				try {
//					return future.get();
//				} catch (Exception e) {
//					e.printStackTrace();
//					return null;
//				} finally {
//					threadPool1.shutdown();
//				}
//			}).toList();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		threadPool1.shutdown();
	}

//	private static void generateCSVResult() {
//		File topicPath = Path.of(PathUtils.currentFolderPath(), submissionDirectoryName).toFile();
//		Object[] row1 = { "2331220036", "Le Kieu Anh", 100, 100, 100, 100 };
//		Object[] row2 = { "2431200178", "Vu Quang Tung", 100, 100, 100, 100 };
//		ReportUtils.generateTopicCSVResult(topicPath, Arrays.asList(row1, row2));
//	}

//	public void gradeLab3Problem1() {
//		File student = submissionDirectory.listFiles()[0];
//		File[] problems = student.listFiles();
//		File problem1 = problems[0];
//		TestSuiteFactory testSuiteFactory = new Lab3Problem1TestSuiteFactory(); 
//		new ProblemGradingTask(problem1, testSuiteFactory).toThread().start();
//	}

//	public static void generateTopicCSVResult(File topicDirFile, String topic, List<Object[]> rows) {
//	    // first create file object for file placed at location specified by filepath
//	    File file = new File(FileExtension.CSV.toAbsoluteFileResultPath(topicDirFile.toString(), StringUtils.toLowerCaseNoSpace(topic)));
//
//	    try {
//	        // create FileWriter object with file as parameter
//	        FileWriter outputfile = new FileWriter(file);
//	        BufferedWriter writer = new BufferedWriter(outputfile);
//
//	        // 1. Write the header row
//	        String[] headers = TopicHeader.withProblems(ProblemName.P1, ProblemName.P2, ProblemName.P3);
//            writer.write(convertToCsvRow(headers));
//            writer.newLine();
//	        	
//            // 2. Write the data rows
//            for (Object[] row : rows) {
//            	writer.write(convertToCsvRow(row));
//                writer.newLine();
//            }
//
//            writer.close();
//            System.out.println("CSV report successfully created at: " + topicDirFile.getAbsolutePath());
//	    }
//	    catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}

    // Safely escapes fields that contain commas or double quotes
//    private static String convertToCsvRow(Object[] fields) {
//        StringBuilder row = new StringBuilder();
//        for (int i = 0; i < fields.length; i++) {
//            String field = String.valueOf(fields[i]);
//            
//            // If the text contains a comma, quote, or newline, wrap it in double quotes
//            if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
//                field = "\"" + field.replace("\"", "\"\"") + "\"";
//            }
//            
//            row.append(field);
//            if (i < fields.length - 1) {
//                row.append(","); // Separate with a comma
//            }
//        }
//        return row.toString();
//    }
	
//	private static void findStudentByDirectory() {
//		for (File studentDir : submissionDirectory.listFiles()) {
//			if (!studentDir.isDirectory()) {
//				continue;
//			}
//			System.out.println(StudentList.findByStudentDirectory(studentDir));
//		}
//	}

//	private static void findStudent() {
//		StudentList.setFilePath("D:\\eclipse-wksp\\AutoGrader\\auto-grader\\cse203-participants-253.csv");
//		List<Student> students = StudentList.getList();
//		Student s = students.stream().filter(stu -> "2331200033".equals(stu.idNumber())).findFirst().orElseThrow();
//		System.out.println(s);
//	}

//	private static void future() {
//		ExecutorService executor = Executors.newSingleThreadExecutor();
//		Future<Integer> future5 = executor.submit(() -> {
//			return 5 * 5;
//		});
//
//		try {
//			while (!future5.isDone()) {
//				System.out.println("Calculating...");
//			}
//			System.out.println(future5.get(500, TimeUnit.MILLISECONDS));
//		} catch (InterruptedException | ExecutionException | TimeoutException e) {
//			e.printStackTrace();
//		}
//		executor.shutdown();
//	}
}
