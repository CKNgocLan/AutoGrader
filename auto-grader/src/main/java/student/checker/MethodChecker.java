package student.checker;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import student.model.Getter;
import student.model.Setter;
import student.util.GetterUtils;
import student.util.SetterUtils;

public class MethodChecker {
	private static MethodChecker instance = null;
	private GetterChecker getterChecker = GetterChecker.getInstance();
	private SetterChecker setterChecker = SetterChecker.getInstance();

	public static MethodChecker getInstance() {
		if (instance == null) {
			instance = new MethodChecker();
		}

		return instance;
	}

	/*
	 * ***************************************************************************
	 */

	public boolean checkMissingGetter(Class<?> clazz, List<Getter> invalidList) {
		return getterChecker.checkMissing(clazz, invalidList);
	}

	public boolean checkMissingSetter(Class<?> clazz, List<Setter> invalidList) {
		return setterChecker.checkMissing(clazz, invalidList);
	}

	/*
	 * ***************************************************************************
	 */
	
	public boolean checkStringGetset(Class<?> clazz, String fieldName, String testValue) {
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
}
