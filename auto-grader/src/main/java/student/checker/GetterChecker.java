package student.checker;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import student.model.Getter;
import student.model.Setter;
import student.util.GetterUtils;
import student.util.SetterUtils;
import student.util.TestCaseUtils;

public class GetterChecker {
	private static GetterChecker instance = null;

	public static GetterChecker getInstance() {
		if (instance == null) {
			instance = new GetterChecker();
		}

		return instance;
	}

	/*
	 * ***************************************************************************
	 */

	public boolean checkMissing(Class<?> clazz, List<Getter> invalidList) {
		List<Getter> missingList = GetterUtils.getMissingGetter(clazz);

		if (missingList.size() > 0) {
			if (invalidList != null) {
				invalidList.addAll(missingList);
			}
			return false;
		}

		return true;
	}

	public boolean checkSetter(Class<?> clazz, List<Setter> invalid) {
		List<Setter> missingList = SetterUtils.getMissingSetter(clazz);
		if (missingList.size() > 0) {
			invalid.addAll(missingList);
			return false;
		}

		return true;
	}

	/*
	 * ***************************************************************************
	 */

	public boolean checkStringGetter(Class<?> clazz, String fieldName, String testValue) {
		try {
			Object instance = clazz.getDeclaredConstructor().newInstance();

			clazz.getDeclaredMethod(SetterUtils.getSetterName(fieldName), String.class).invoke(instance, testValue);

			return testValue.equals(clazz.getDeclaredMethod(GetterUtils.getGetterName(fieldName)).invoke(instance));
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

	public static boolean checkDoubleGetter(Class<?> clazz, String fieldName, double testValue)
			throws NoSuchFieldException, SecurityException {
		try {
			Object instance = clazz.getDeclaredConstructor().newInstance();

			clazz.getDeclaredMethod(SetterUtils.getSetterName(fieldName), double.class).invoke(instance, testValue);

			return testValue == Double
					.valueOf(clazz.getDeclaredMethod(GetterUtils.getGetterName(fieldName)).invoke(instance).toString())
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

			clazz.getDeclaredMethod(SetterUtils.getSetterName(fieldName), int.class).invoke(instance, testValue);

			return testValue == Integer
					.valueOf(clazz.getDeclaredMethod(GetterUtils.getGetterName(fieldName)).invoke(instance).toString())
					.intValue();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return false;
		}
	}
}
