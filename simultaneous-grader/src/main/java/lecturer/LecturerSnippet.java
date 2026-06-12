package lecturer;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import common.constant.LabName;
import model.component.Student;
import model.component.StudentList;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.factory.Lab3Problem1TestSuiteFactory;
import model.exception.TesterGotNoClassNameException;
import model.service.ProblemGradingTask;

public class LecturerSnippet {
	static String selectedLab = LabName.L3;
	static String path = "D:\\eclipse-wksp\\AutoGrader\\auto-grader\\sample-lab3-submission";
	static String csvPath = "D:\\eclipse-wksp\\AutoGrader\\auto-grader\\cse203-participants-253.csv";
	static File submissionDirectory = new File(path);

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			NoSuchFieldException, TesterGotNoClassNameException {
		File student = submissionDirectory.listFiles()[0];
		File[] problems = student.listFiles();
		File problem1 = problems[0];
		TestSuiteFactory testSuiteFactory = new Lab3Problem1TestSuiteFactory(); 
		Thread thread = new Thread(new ProblemGradingTask(problem1, testSuiteFactory));
		thread.start();
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
