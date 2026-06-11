package lecturer;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVRecord;

import common.constant.LabName;
import common.util.ReportUtils;
import model.component.Student;
import model.component.StudentList;
import model.exception.TesterGotNoClassNameException;


public class Snippet {
	String selectedLab = LabName.L3;
	String path = "D:\\eclipse-wksp\\AutoGrader\\auto-grader\\sample-lab3-submission";
	static String csvPath = "D:\\eclipse-wksp\\AutoGrader\\auto-grader\\cse203-participants-253.csv";
	File submissionDirectory = new File(path);
    
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, NoSuchFieldException, TesterGotNoClassNameException {
//    	List<CSVRecord> records = ReportUtils.readCSV(csvPath);
//    	List<Student> students = records.stream().map(Student::new).toList();

    	StudentList.setFilePath("D:\\eclipse-wksp\\AutoGrader\\auto-grader\\cse203-participants-253.csv");
    	List<Student> students = StudentList.getList();
    	Student s = students.stream().filter(stu -> "2331200033".equals(stu.idNumber())).findFirst().orElseThrow();
    	System.out.println(s);
//    	for (Student s : students) {
//    		System.out.println(s.idNumber());
//    	}
    }

    private static void future() {
    	ExecutorService executor = Executors.newSingleThreadExecutor();
    	Future<Integer> future5 = executor.submit(() -> { return 5 * 5; });
    	
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
