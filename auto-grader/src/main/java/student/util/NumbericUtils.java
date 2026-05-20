package student.util;

public class NumbericUtils {
	public static Double toDouble(Object value) {
		return Double.valueOf(StringUtils.toString(value));
	}
	
	public static Integer toInteger(Object value) {
		return Integer.valueOf(StringUtils.toString(value));
	}
}
