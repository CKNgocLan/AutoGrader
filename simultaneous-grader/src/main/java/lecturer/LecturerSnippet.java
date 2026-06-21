package lecturer;

import java.io.File;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import common.constant.TopicName;
import common.util.PathUtils;
import common.util.ReportUtils;
import model.component.Student;
import model.component.StudentList;
import model.exception.InvalidConfigurationException;
import model.exception.NotFoundProblemSubmissionException;
import model.exception.NotFoundStudentException;
import model.exception.TesterGotNoClassNameException;
import model.service.StudentThreadPool;

public class LecturerSnippet {
	static String selectedLab = TopicName.L3;
	static String csvPath = Path.of(PathUtils.currentFolderPath(), "cse203-participants-253.csv").toString();
//	static String submissionDirectoryName = "sample-lab3-submission";
	static String submissionDirectoryName = "final-253-submission";
	static File submissionDirectory = new File(Path.of(PathUtils.currentFolderPath(), submissionDirectoryName).toString());
	
	static List<File> innerSubmissionDirectory = Stream.of(submissionDirectory.listFiles()).filter(file -> file.isDirectory()).toList();

	public static void main(String[] args) throws Exception {
//		gradeLab3();
		gradeFinalExam253();
	}
	private static void gradeFinalExam253() {
		String topic = TopicName.FINAL_253;
		StudentList.setFilePath(csvPath);
		String submissionDirectoryName = "final-253-submission";
		File submissionDirectory = new File(Path.of(PathUtils.currentFolderPath(), submissionDirectoryName).toString());
		ReportUtils.createTopicResultToCSV(submissionDirectory, topic);

		// 1. Record start time
		Instant start = Instant.now();
		List<Student> notFoundStudentList = new ArrayList<Student>();


        // 2. Find student submission
		for (Student student : StudentList.getList()) {
			try {
				System.out.println("Grade Submission of %s".formatted(student.fullName()));
				File stuSubDir = findStudentSubmission(student);
				if (stuSubDir == null) {
					continue;
				}

				new StudentThreadPool(topic, stuSubDir).submit();
			} catch (NoSuchElementException | NotFoundProblemSubmissionException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			} catch (NotFoundStudentException e) {
				notFoundStudentList.add(student);
			}
		}
		
		// 3. Record end time
		Instant end = Instant.now();

        // 4. Calculate total duration
		Duration timeElapsed = Duration.between(start, end);
		
		System.out.println("Time taken: " + timeElapsed.toSeconds() + " seconds");
		System.out.println("Time taken: " + timeElapsed.toMillis() + " milliseconds");
		
		System.out.println("NOT FOUND Student Number: %d".formatted(notFoundStudentList.size()));
		System.out.println("Finish Grading %s".formatted(topic));
	}

	private static void gradeLab3() {
		String topic = TopicName.L3;
		StudentList.setFilePath(csvPath);
		String submissionDirectoryName = "sample-lab3-submission";
		File submissionDirectory = new File(Path.of(PathUtils.currentFolderPath(), submissionDirectoryName).toString());
		ReportUtils.createTopicResultToCSV(submissionDirectory, topic);

		// 1. Record start time
		Instant start = Instant.now();
		List<Student> notFoundStudentList = new ArrayList<Student>();

        // 2. Find student submission
		for (Student student : StudentList.getList()) {
			try {
				File stuSubDir = findStudentSubmission(student);
				if (stuSubDir == null) {
					continue;
				}

				new StudentThreadPool(topic, stuSubDir).submit();
			} catch (NoSuchElementException | NotFoundProblemSubmissionException e) {
				System.err.println(e.getMessage());
			} catch (NotFoundStudentException e) {
				notFoundStudentList.add(student);
//				try {
//					e.writeCSV(submissionDirectory, topic, student);
//				} catch (InvalidConfigurationException e1) {
//					e1.printStackTrace();
//				}
			}
		}
		
		// 3. Record end time
		Instant end = Instant.now();

        // 4. Calculate total duration
		Duration timeElapsed = Duration.between(start, end);
		
		System.out.println("Time taken: " + timeElapsed.toSeconds() + " seconds");
		System.out.println("Time taken: " + timeElapsed.toMillis() + " milliseconds");
		
		System.out.println("NOT FOUND Student Number: %d".formatted(notFoundStudentList.size()));
		System.out.println("Finish Grading %s".formatted(topic));
	}

	private static File findStudentSubmission(Student student) throws NotFoundStudentException {
		return innerSubmissionDirectory.stream()
				.filter(innerDir -> StudentList.findByStudentDirectory(innerDir).equals(student)).findFirst()
				.orElse(null);
//				.orElseThrow(NotFoundStudentException.toSupplier(student));
	}

	private static void gradeLab3ViaSubmissionDirectory() {
		StudentList.setFilePath(csvPath);
		String topic = TopicName.L3;
//		File studentDir = submissionDirectory.listFiles()[0];
//		StudentThreadPool threadPool1 = new StudentThreadPool(TopicName.L3, studentDir);
//		threadPool1.addTask(new ProblemGradingTask(studentDir.listFiles()[0], new Lab3Problem1TestSuiteFactory()));
//		threadPool1.addTask(new ProblemGradingTask(studentDir.listFiles()[1], new Lab3Problem2TestSuiteFactory()));
//		List<ProblemResult> studentResult1 = threadPool1.submit();

//		HashMap<String, TestSuiteFactory> factoryMapper = new HashMap<String, TestSuiteFactory>();
//		factoryMapper.put(ProblemName.P1, new Lab3Problem1TestSuiteFactory());
//		factoryMapper.put(ProblemName.P2, new Lab3Problem2TestSuiteFactory());
//		factoryMapper.put(ProblemName.P3, new Lab3Problem3TestSuiteFactory());
//		factoryMapper.put(ProblemName.P4, new Lab3Problem4TestSuiteFactory());
//		factoryMapper.put(ProblemName.P5, new Lab3Problem5TestSuiteFactory());

		ReportUtils.createTopicResultToCSV(submissionDirectory, topic);
		for (File studentDir : submissionDirectory.listFiles()) {
			if(!studentDir.isDirectory()) {
				continue;
			}
//			StudentThreadPool threadPool = new StudentThreadPool(topic, studentDir);
//			threadPool.addTask(new ProblemGradingTask(Stream.of(studentDir.listFiles()).filter(probDir -> probDir.getName().equals(l3p1Factory.getTopic())).findFirst().orElseThrow(), l3p1Factory));
//			threadPool.addTask(new ProblemGradingTask(Stream.of(studentDir.listFiles()).filter(probDir -> probDir.getName().equals(l3p1Factory.getTopic())).findFirst().orElseThrow(), l3p2Factory));
//			threadPool.submit();
			try {
				new StudentThreadPool(topic, studentDir).submit();
			} catch (NoSuchElementException | NotFoundProblemSubmissionException e) {
				e.printStackTrace();
			}
		}

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
//		Student s = students.stream().filter(stu -> "2331200033".equals(stu.number())).findFirst().orElseThrow();
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
