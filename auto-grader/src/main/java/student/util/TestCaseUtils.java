package student.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import student.constant.Constants;

public class TestCaseUtils {

	public static boolean checkField(Field field) {
		if (Modifier.isStatic(field.getModifiers())) {
			return isAllUppercase(field.getName());
		}
		return Modifier.isPrivate(field.getModifiers()) && isCamelCase(field.getName());
	}

	public static boolean checkPrivateStaticField(Field field) {
		return Modifier.isPrivate(field.getModifiers()) && Modifier.isStatic(field.getModifiers())
				&& isAllUppercase(field.getName());
	}

	/*
	 * ***************************************************************************
	 */

	public static boolean isCamelCase(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}

		// Must start with lowercase letter
		if (!Character.isLowerCase(name.charAt(0))) {
			return false;
		}

		// Should not contain underscore
		if (name.contains(Constants.UNDERSCORE)) {
			return false;
		}

		return true;
	}

	public static boolean isAllUppercase(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		for (char c : name.toCharArray()) {
			if (!Character.isUpperCase(c) && c != Constants.UNDERSCORE_CHAR) {
				return false;
			}
		}
		return true;
	}

}
