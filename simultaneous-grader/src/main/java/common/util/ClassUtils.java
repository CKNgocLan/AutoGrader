package common.util;

import java.util.stream.Stream;

import common.constant.Constants;
import model.element.TestingField;

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

	public static String toInnerClassName(String superClassName, String className) {
		return String.join(Constants.DOLLAR_SIGN, superClassName, className);
	}

	public static String[] splitInnerClassName(String fullInnerClassName) {
		return fullInnerClassName.split(Constants.ESCAPE_DOLLAR_SIGN);
	}

	/*
	 * ***************************************************************************
	 */

	public static boolean containField(Class<?> clazz, TestingField testingField) {
		return Stream.of(clazz.getDeclaredFields()).filter(declaredField -> testingField.equals(declaredField)).toList().size() > 0;
	}

	public static boolean containFieldButModifiers(Class<?> clazz, TestingField testingField) {
		return Stream.of(clazz.getDeclaredFields()).filter(declaredField -> testingField.equalsButModifiers(declaredField)).toList().size() > 0;
	}
}
