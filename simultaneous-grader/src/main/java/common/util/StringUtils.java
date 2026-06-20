package common.util;

import java.util.stream.Stream;

import common.constant.Constants;
import common.constant.ProblemName;
import common.constant.Symbol;

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
			if (!Character.isUpperCase(c) && c != Constants.UNDERSCORE_CHAR && !Character.isDigit(c)) {
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
	
	public static boolean isNullOrEmpty(Object value) {
		return value == null || String.valueOf(value).isEmpty();
	}
	
	public static String toString(Object value) {
		return isNullOrEmpty(value) ? Constants.EMPTY_STRING : String.valueOf(value);
	}

	public static String removeWhiteSpace(Object value) {
		return toString(value).replaceAll("\\s", Constants.EMPTY_STRING);
	}

	public static String toLowerCaseNoSpace(Object value) {
		return removeWhiteSpace(value).toLowerCase();
	}

	public static boolean compareAsLowerCaseNoSpace(Object value1, Object value2) {
		return toLowerCaseNoSpace(value1).equals(toLowerCaseNoSpace(value2));
	}

	public static String joinLowerCase(Object... value) {
		return String.join(Symbol.HYPHEN, Stream.of(value).map(val -> toLowerCaseNoSpace(val)).toList());
	}
	public static String joinOriginal(Object... value) {
		return String.join(Symbol.HYPHEN, Stream.of(value).map(val -> toString(val)).toList());
	}

	public static String toSafeName(String value) {
		return isNullOrEmpty(value) ? ProblemName.EMPTY : value.replaceAll(Constants.SAFE_STRING_REGEX, Constants.UNDERSCORE);
	}

	public static String encloseDoubleQuote(Object value) {
		return String.format("\"%s\"", toString(value));
	}
}
