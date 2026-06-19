package common.util;

import common.constant.Constants;
import model.component.Student;

public class StudentUtils {
	public static Student createNotFoundStudent(String number) {
		return new Student(number, Constants.NOT_FOUND_STUDENT, null);
	}
}
