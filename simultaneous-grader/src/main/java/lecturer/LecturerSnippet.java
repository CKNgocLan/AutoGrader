package lecturer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.opencsv.CSVWriter;

import common.constant.FileExtension;
import common.constant.TopicName;
import common.util.PathUtils;
import common.util.StringUtils;
import model.component.Student;
import model.component.StudentList;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.factory.Lab3Problem1TestSuiteFactory;
import model.exception.TesterGotNoClassNameException;
import model.service.ProblemGradingTask;

public class LecturerSnippet {
	static String selectedLab = TopicName.L3;
	static String path = "E:\\eclipse-workspace\\AutoGrader\\auto-grader\\sample-lab3-submission";
	static String csvPath = "E:\\eclipse-workspace\\AutoGrader\\cse203-participants-253.csv";
	static File submissionDirectory = new File(path);
	static String submissionDirectoryName = "sample-lab3-submission";

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			NoSuchFieldException, TesterGotNoClassNameException {
		Path topicPath = Path.of(PathUtils.currentFolderPath(), submissionDirectoryName);
		File file = new File(Path.of(topicPath.toString(), topicPath.toFile().getName() + FileExtension.CSV).toString());
		
		System.out.println(FileExtension.CSV.toResultName(StringUtils.toLowerCaseNoSpace(TopicName.L3)));
		System.out.println(FileExtension.CSV.toAbsoluteFileResultPath(path, StringUtils.toLowerCaseNoSpace(TopicName.L3)));
	}

	public void gradeLab3Problem1() {
		File student = submissionDirectory.listFiles()[0];
		File[] problems = student.listFiles();
		File problem1 = problems[0];
		TestSuiteFactory testSuiteFactory = new Lab3Problem1TestSuiteFactory(); 
		new ProblemGradingTask(problem1, testSuiteFactory).toThread().start();
	}

	public static void writeDataLineByLine(File submissionDir, String topic)
	{
	    // first create file object for file placed at location specified by filepath
	    File file = new File(Path.of(submissionDirectoryName, topic + FileExtension.CSV).toString());

	    try {
	        // create FileWriter object with file as parameter
	        FileWriter outputfile = new FileWriter(file);

	        // create CSVWriter object filewriter object as parameter
	        CSVWriter writer = new CSVWriter(outputfile);

	        // adding header to csv
	        String[] header = { "Name", "Class", "Marks" };
	        writer.writeNext(header);

	        // add data to csv
	        String[] data1 = { "Aman", "10", "620" };
	        writer.writeNext(data1);
	        String[] data2 = { "Suraj", "10", "630" };
	        writer.writeNext(data2);

	        // closing writer connection
	        writer.close();
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private static void findStudentByDirectory() {
		for (File studentDir : submissionDirectory.listFiles()) {
			if (!studentDir.isDirectory()) {
				continue;
			}
			System.out.println(StudentList.findByStudentDirectory(studentDir));
		}
	}

	private static void findStudent() {
		StudentList.setFilePath("D:\\eclipse-wksp\\AutoGrader\\auto-grader\\cse203-participants-253.csv");
		List<Student> students = StudentList.getList();
		Student s = students.stream().filter(stu -> "2331200033".equals(stu.idNumber())).findFirst().orElseThrow();
		System.out.println(s);
	}

	private static void future() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Integer> future5 = executor.submit(() -> {
			return 5 * 5;
		});

		try {
			while (!future5.isDone()) {
				System.out.println("Calculating...");
			}
			System.out.println(future5.get(500, TimeUnit.MILLISECONDS));
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}
}
