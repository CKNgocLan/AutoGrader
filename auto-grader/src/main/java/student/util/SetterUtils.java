package student.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Stream;

import student.constant.Constants;
import student.constant.MethodName;
import student.model.Setter;

public class SetterUtils {
	public static List<Setter> getMissingSetter(Class<?> clazz) {
		return getSettersFromAttributes(clazz).stream()
				.filter(attr -> !getDeclaredSetter(clazz, MethodName.SET).contains(attr)).toList();
	}

	/*
	 * ***************************************************************************
	 */

	public static List<Setter> getSettersFromAttributes(Class<?> clazz) {
		return Stream.of(clazz.getDeclaredFields()).filter(field -> !Modifier.isStatic(field.getModifiers()))
				.map(field -> new Setter(field)).toList();
	}

	/*
	 * ***************************************************************************
	 */

	public static List<Setter> getDeclaredSetter(Class<?> clazz, String getset) {
		return getDeclaredGetterSetter(clazz, getset).map(set -> new Setter(set.getName(), set.getReturnType()))
				.toList();
	}

	/*
	 * ***************************************************************************
	 */

	public static Stream<Method> getDeclaredGetterSetter(Class<?> clazz, String getset) {
		return Stream.of(clazz.getDeclaredMethods())
				.filter(method -> getset.equals(method.getName().subSequence(0, 3)));
	}

	/*
	 * ***************************************************************************
	 */

	public static String getSetterName(String attributeName) {
		if (attributeName == null || attributeName.isEmpty()) {
			return "";
		}

		// Special handling for common patterns
		if (attributeName.equalsIgnoreCase(Constants.ID)) {
			return MethodName.SET_ID;
		}

		return MethodName.SET + StringUtils.capitalizeFirstLetter(attributeName);
	}
}
