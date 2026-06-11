package model.component;

import java.io.File;
import java.util.List;

import common.constant.Constants;
import common.util.ReportUtils;

public class StudentList {
	private static List<Student> list;
	private static String filePath;

	public static List<Student> getList() {
		return list;
	}

	public static void setFilePath(String filePath) {
		StudentList.filePath = filePath;
		initializeList();
	}

	public static Student findByID(String idNumber) {
		return list.stream().filter(stu -> stu.idNumber().equals(idNumber)).findFirst().orElseThrow();
	}

	public static Student findByStudentDirectory(File submissionDir) {
		return StudentList.findByID(submissionDir.getName().split(Constants.UNDERSCORE)[0]);
	}

	private static List<Student> initializeList() {
		if (list == null || list.isEmpty()) {
			list = ReportUtils.readCSV(filePath).stream().map(Student::new).toList();
		}
		return list;
	}
}
