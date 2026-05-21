package student.util;

public class ValueUtils {
	/*
	 * double
	 */

	public static Double toDouble(Object value) {
		return Double.valueOf(StringUtils.toString(value));
	}

	public static double toDoublePrimitive(Object value) {
		return toDouble(value).doubleValue();
	}

	/*
	 * integer
	 */

	public static Integer toInteger(Object value) {
		return Integer.valueOf(StringUtils.toString(value));
	}

	public static int toInt(Object value) {
		return toInteger(value).intValue();
	}

	/*
	 * boolean
	 */

	public static Boolean toBoolean(Object value) {
		return Boolean.valueOf(StringUtils.toString(value));
	}

	public static boolean toBooleanPrimitive(Object value) {
		return toBoolean(value).booleanValue();
	}
}
