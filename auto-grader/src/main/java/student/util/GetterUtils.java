package student.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Stream;

import student.constant.FieldName;
import student.constant.MethodName;
import student.model.Getter;

public class GetterUtils {
	public static List<Getter> getMissingGetter(Class<?> clazz) {
		return getGettersFromAttributes(clazz).stream()
				.filter(attr -> !getDeclaredGetter(clazz, MethodName.GET).contains(attr)).toList();
	}

	/*
	 * ***************************************************************************
	 */

	public static List<Getter> getDeclaredGetter(Class<?> clazz, String getset) {
		return getDeclaredGetterSetter(clazz, getset).map(get -> new Getter(get.getName(), get.getReturnType()))
				.toList();
	}

	/*
	 * ***************************************************************************
	 */

	public static List<Getter> getGettersFromAttributes(Class<?> clazz) {
		return Stream.of(clazz.getDeclaredFields()).filter(field -> !Modifier.isStatic(field.getModifiers()))
				.map(field -> new Getter(field)).toList();
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
	public static String getGetterName(String attributeName) {
        if (attributeName == null || attributeName.isEmpty()) {
            return "";
        }
        
        // Special handling for common patterns
        if (attributeName.equalsIgnoreCase(FieldName.ID)) {
            return MethodName.GET_ID;
        }
        
        return MethodName.GET + StringUtils.capitalizeFirstLetter(attributeName);
    }
}
