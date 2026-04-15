package student.checker;

import java.lang.reflect.InvocationTargetException;

import student.util.TestCaseUtil;

public class GetterChecker {
	public static boolean checkStringGetter(Class<?> clazz, String fieldName, String testValue) {
		try {
			Object instance = clazz.getDeclaredConstructor().newInstance();

			clazz.getDeclaredMethod(TestCaseUtil.getSetterName(fieldName), String.class)
				.invoke(instance, testValue);

			return testValue.equals(
					clazz.getDeclaredMethod(TestCaseUtil.getGetterName(fieldName))
				.invoke(instance)
			);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean check() {
		return true;
	}
	
	public static boolean checkDoubleGetter(Class<?> clazz, String fieldName, Double testValue) {
		return checkDoubleGetter(clazz, fieldName, testValue);
	}
	
	public static boolean checkDoubleGetter(Class<?> clazz, String fieldName, double testValue) throws NoSuchFieldException, SecurityException {
		try {
			Object instance = clazz.getDeclaredConstructor().newInstance();

			clazz.getDeclaredMethod(TestCaseUtil.getSetterName(fieldName), double.class)
				.invoke(instance, testValue);
			
			return testValue == Double
					.valueOf(clazz.getDeclaredMethod(TestCaseUtil.getGetterName(fieldName)).invoke(instance).toString())
					.doubleValue();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean checkIntegerGetter(Class<?> clazz, String fieldName, Integer testValue) {
		return checkIntGetter(clazz, fieldName, testValue.intValue());
	}
	
	public static boolean checkIntGetter(Class<?> clazz, String fieldName, int testValue) {
		try {
			Object instance = clazz.getDeclaredConstructor().newInstance();

			clazz.getDeclaredMethod(TestCaseUtil.getSetterName(fieldName), int.class)
				.invoke(instance, testValue);
			
			return testValue == Integer
					.valueOf(clazz.getDeclaredMethod(TestCaseUtil.getGetterName(fieldName)).invoke(instance).toString())
					.intValue();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return false;
		}
	}
}
