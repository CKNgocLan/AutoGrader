package common.util;

import common.constant.Constants;
import model.component.Student;

public class StudentUtils {
	public static Student createNotFoundStudent(String idNumber) {
		return new Student(idNumber, Constants.NOT_FOUND_STUDENT, null);
	}
}
