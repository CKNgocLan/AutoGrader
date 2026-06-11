package model.component;

import java.util.List;

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

	private static List<Student> initializeList() {
		if (list == null || list.isEmpty()) {
			list = ReportUtils.readCSV(filePath).stream().map(Student::new).toList();
		}
		return list;
	}
}
