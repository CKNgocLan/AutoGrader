package student.util;

import student.constant.Constants;

public class StringUtils {
	public static String capitalizeFirstLetter(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
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
	
	public static boolean isNullOrEmpty(String value) {
		return value == null || value.isEmpty();
	}
}
