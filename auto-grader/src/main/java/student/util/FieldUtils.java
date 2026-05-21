package student.util;

public class FieldUtils {
	public static Integer toInteger(Object value) {
		return Integer.valueOf(StringUtils.toString(value));
	}

	public static Integer toInt(Object value) {
		return toInteger(value).intValue();
	}
}
