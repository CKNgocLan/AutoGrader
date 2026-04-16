package student.util;

public class ClassUtils {
	public static Class<?> boxing(Class<?> primitiveType) {
		if (byte.class.equals(primitiveType)) {
			return Byte.class;
		} else if(short.class.equals(primitiveType)) {
			return Short.class;
		} else if(int.class.equals(primitiveType)) {
			return Integer.class;
		} else if(float.class.equals(primitiveType)) {
			return Float.class;
		} else if(long.class.equals(primitiveType)) {
			return Long.class;
		} else if(double.class.equals(primitiveType)) {
			return Double.class;
		}
		
		return Object.class;
	}

	/*
	 * ***************************************************************************
	 */

}
