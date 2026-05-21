package student.util;

public class ValueUtils {
	public static Integer toInteger(Object value) {
		return Integer.valueOf(StringUtils.toString(value));
	}

	public static Integer toInt(Object value) {
		return toInteger(value).intValue();
	}
	
	public static Boolean toBoolean(Object value) {
		return Boolean.valueOf(StringUtils.toString(value));
	}

	public static Boolean toBooleanPrimitive(Object value) {
		return toBoolean(value).booleanValue();
	}
}
