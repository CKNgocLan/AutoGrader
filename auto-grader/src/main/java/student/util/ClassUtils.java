package student.util;

import java.util.stream.Stream;

import student.model.FieldTesting;

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

	public static boolean containField(Class<?> clazz, FieldTesting testingField) {
		return Stream.of(clazz.getDeclaredFields()).filter(declaredField -> testingField.equals(declaredField)).toList().size() > 0;
	}

	public static boolean containFieldButModifiers(Class<?> clazz, FieldTesting testingField) {
		return Stream.of(clazz.getDeclaredFields()).filter(declaredField -> testingField.equalsButModifiers(declaredField)).toList().size() > 0;
	}
}
