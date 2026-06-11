package helper.elementChecker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import common.util.GetterUtils;
import common.util.SetterUtils;
import model.element.Getter;
import model.element.Setter;
import model.element.TestingMethod;

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
	 * ***** MISSING GETTER/SETTER *****
	 */

	public boolean checkMissingGetter(Class<?> clazz, List<Getter> invalidList) {
		return getterChecker.checkMissing(clazz, invalidList);
	}

	public boolean checkMissingSetter(Class<?> clazz, List<Setter> invalidList) {
		return setterChecker.checkMissing(clazz, invalidList);
	}

	/*
	 * STRING OPERATION ***************
	 */

	public boolean checkStringGetset(Class<?> clazz, String fieldName, String testValue) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Object instance = clazz.getDeclaredConstructor().newInstance();

		clazz.getDeclaredMethod(SetterUtils.getSetterName(fieldName), String.class).invoke(instance, testValue);

		return testValue.equals(clazz.getDeclaredMethod(GetterUtils.getGetterName(fieldName)).invoke(instance));
	}

	/*
	 * BYTE OPERATION ***************
	 */

	public boolean checkBytetGetset(Class<?> clazz, String fieldName, Byte testValue) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		return checkBytetGetset(clazz, fieldName, testValue.byteValue());
	}

	public boolean checkBytetGetset(Class<?> clazz, String fieldName, byte testValue) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Object instance = clazz.getDeclaredConstructor().newInstance();

		clazz.getDeclaredMethod(SetterUtils.getSetterName(fieldName), byte.class).invoke(instance, testValue);

		return testValue == Byte
				.valueOf(clazz.getDeclaredMethod(GetterUtils.getGetterName(fieldName)).invoke(instance).toString())
				.byteValue();
	}

	/*
	 * SHORT OPERATION ***************
	 */

	public boolean checkShortGetset(Class<?> clazz, String fieldName, Short testValue) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalArgumentException {
		return checkShortGetset(clazz, fieldName, testValue.shortValue());
	}

	public boolean checkShortGetset(Class<?> clazz, String fieldName, short testValue) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalArgumentException {
		Object instance = clazz.getDeclaredConstructor().newInstance();

		clazz.getDeclaredMethod(SetterUtils.getSetterName(fieldName), short.class).invoke(instance, testValue);

		return testValue == Short
				.valueOf(clazz.getDeclaredMethod(GetterUtils.getGetterName(fieldName)).invoke(instance).toString())
				.shortValue();
	}

	/*
	 * INTEGER OPERATION ***************
	 */

	public boolean checkIntegerGetset(Class<?> clazz, String fieldName, Integer testValue)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException {
		return checkIntGetset(clazz, fieldName, testValue.intValue());
	}

	public boolean checkIntGetset(Class<?> clazz, String fieldName, int testValue) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Object instance = clazz.getDeclaredConstructor().newInstance();

		clazz.getDeclaredMethod(SetterUtils.getSetterName(fieldName), int.class).invoke(instance, testValue);

		return testValue == Integer
				.valueOf(clazz.getDeclaredMethod(GetterUtils.getGetterName(fieldName)).invoke(instance).toString())
				.intValue();
	}

	/*
	 * LONG OPERATION ***************
	 */

	public boolean checkLongGetset(Class<?> clazz, String fieldName, Long testValue) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		return checkLongGetset(clazz, fieldName, testValue.longValue());
	}

	public boolean checkLongGetset(Class<?> clazz, String fieldName, long testValue) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Object instance = clazz.getDeclaredConstructor().newInstance();

		clazz.getDeclaredMethod(SetterUtils.getSetterName(fieldName), long.class).invoke(instance, testValue);

		return testValue == Long
				.valueOf(clazz.getDeclaredMethod(GetterUtils.getGetterName(fieldName)).invoke(instance).toString())
				.longValue();
	}

	/*
	 * FLOAT OPERATION ***************
	 */

	public boolean checkFloatGetset(Class<?> clazz, String fieldName, Float testValue)
			throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		return checkFloatGetset(clazz, fieldName, testValue.floatValue());
	}

	public boolean checkFloatGetset(Class<?> clazz, String fieldName, float testValue)
			throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Object instance = clazz.getDeclaredConstructor().newInstance();

		clazz.getDeclaredMethod(SetterUtils.getSetterName(fieldName), float.class).invoke(instance, testValue);

		return testValue == Float
				.valueOf(clazz.getDeclaredMethod(GetterUtils.getGetterName(fieldName)).invoke(instance).toString())
				.floatValue();
	}

	/*
	 * DOUBLE OPERATION ***************
	 */

	public boolean checkDoubleGetset(Class<?> clazz, String fieldName, Double testValue)
			throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		return checkDoubleGetset(clazz, fieldName, testValue.doubleValue());
	}

	public boolean checkDoubleGetset(Class<?> clazz, String fieldName, double testValue)
			throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Object instance = clazz.getDeclaredConstructor().newInstance();

		clazz.getDeclaredMethod(SetterUtils.getSetterName(fieldName), double.class).invoke(instance, testValue);

		return testValue == Double
				.valueOf(clazz.getDeclaredMethod(GetterUtils.getGetterName(fieldName)).invoke(instance).toString())
				.doubleValue();
	}

	/*
	 * DECLARATION ***************
	 */

	public boolean isMethodDeclared(Class<?> clazz, TestingMethod method)
			throws NoSuchMethodException, SecurityException {
		return method.equals(clazz.getDeclaredMethod(method.getName(), method.getParameterTypes()));
	}

	public boolean isDeclaredAsSpecialModifers(Class<?> clazz, TestingMethod method)
			throws NoSuchMethodException, SecurityException {
		Method reflectMethod = clazz.getDeclaredMethod(method.getName(), method.getParameterTypes());

		if (!method.equalsButModifiers(reflectMethod)) {
			return false;
		}

		if ((method.isStatic() && !Modifier.isStatic(reflectMethod.getModifiers()))
				|| (!method.isStatic() && Modifier.isStatic(reflectMethod.getModifiers()))) {
			return false;
		}

		if ((method.isAbstract() && !Modifier.isAbstract(reflectMethod.getModifiers()))
				|| (!method.isAbstract() && Modifier.isAbstract(reflectMethod.getModifiers()))) {
			return false;
		}

		return true;
	}
	
	public boolean isPublicAbstract(Class<?> clazz, TestingMethod method) throws NoSuchMethodException {
		Method reflectMethod = clazz.getDeclaredMethod(method.getName(), method.getParameterTypes());

		if (!method.equalsButModifiers(reflectMethod)) {
			return false;
		}
		
		return Modifier.isPublic(reflectMethod.getModifiers()) && Modifier.isAbstract(reflectMethod.getModifiers());
	}

	/*
	 * EXCLUDE
	 */

	public boolean isExluded(Class<?> clazz, TestingMethod... excludedMethods) {
		Method[] actualMethods = clazz.getDeclaredMethods();

		for (Method am : actualMethods) {
			Optional<TestingMethod> foundMethod = Stream.of(excludedMethods)
					.filter(em -> em.getName().equals(am.getName()) && em.getReturnedType().equals(am.getReturnType()))
					.findFirst();
			if (foundMethod.isPresent()) {
				return false;
			}
		}

		return true;
	}

	/*
	 * build
	 */
	
	public void buildInstance(Class<?> clazz, Object instance, TestingMethod method) throws Exception {
		clazz.getDeclaredMethod(method.getName(), method.getParameterTypes()).invoke(instance, method.getParameterValues());
	}
}
