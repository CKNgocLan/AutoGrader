package student.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Stream;

import student.constant.Constants;
import student.constant.MethodName;
import student.model.Getter;
import student.model.Setter;

public class TestCaseUtil {

	public static boolean checkField(Field field) {
		return Modifier.isPrivate(field.getModifiers())
				&& isCamelCase(field.getName());
	}
	
	public static boolean checkPrivateStaticField(Field field) {
		return Modifier.isPrivate(field.getModifiers())
				&& Modifier.isStatic(field.getModifiers())
				&& isAllUppercase(field.getName());
	}
	
	public static boolean checkGetter(Class<?> clazz, List<Getter> invalid) {
		List<Getter> missingList = getMissingGetter(clazz);
		
		if (missingList.size() > 0) {
			invalid.addAll(missingList);
			return false;
		}
		
		return true;
	}
	
	public static boolean checkSetter(Class<?> clazz, List<Setter> invalid) {
		List<Setter> missingList = getMissingSetter(clazz);
		if (missingList.size() > 0) {
			invalid.addAll(missingList);
			return false;
		}
		
		return true;
	}
	
	/* *************************************************************************** */
	
	public static List<Getter> getMissingGetter(Class<?> clazz) {
		return getGettersFromAttributes(clazz).stream()
				.filter(attr -> !getDeclaredGetter(clazz, MethodName.GET).contains(attr)).toList();
	}
	
	public static List<Setter> getMissingSetter(Class<?> clazz) {
		return getSettersFromAttributes(clazz).stream()
				.filter(attr -> !getDeclaredSetter(clazz, MethodName.SET).contains(attr)).toList();
	}
	
	/* *************************************************************************** */
	
	public static List<Getter> getGettersFromAttributes(Class<?> clazz) {
		return Stream.of(clazz.getDeclaredFields())
				.filter(field -> !Modifier.isStatic(field.getModifiers()))
				.map(field -> new Getter(field))
				.toList();
	}
	
	public static List<Setter> getSettersFromAttributes(Class<?> clazz) {
		return Stream.of(clazz.getDeclaredFields())
				.filter(field -> !Modifier.isStatic(field.getModifiers()))
				.map(field -> new Setter(field))
				.toList();
	}
	
	public static Stream<Method> getDeclaredGetterSetter(Class<?> clazz, String getset) {
		return Stream.of(clazz.getDeclaredMethods())
				.filter(method -> getset.equals(method.getName().subSequence(0, 3)))
				;
	}
	
	public static List<Getter> getDeclaredGetter(Class<?> clazz, String getset) {
		return getDeclaredGetterSetter(clazz, getset)
				.map(get -> new Getter(get.getName(), get.getReturnType()))
				.toList();
	}
	
	public static List<Setter> getDeclaredSetter(Class<?> clazz, String getset) {
		return getDeclaredGetterSetter(clazz, getset)
				.map(set -> new Setter(set.getName(), set.getReturnType()))
				.toList();
	}
	
	/* *************************************************************************** */

	public static boolean isCamelCase(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}

		// Mus)t start with lowercase letter
		if (!Character.isLowerCase(name.charAt(0))) {
			return false;
		}

		// Should not contain underscore
		if (name.contains(Constants.UNDERSCORE)) {
			return false;
		}

		return true;
	}
	
	public static boolean isAllUppercase(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		for (char c : name.toCharArray()) {
			if (!Character.isUpperCase(c) && c != Constants.UNDERSCORE_CHAR) {
				return false;
			}
		}
		return true;
	}
	
	/* *************************************************************************** */
	
	public static String getGetterName(String attributeName) {
        if (attributeName == null || attributeName.isEmpty()) {
            return "";
        }
        
        // Special handling for common patterns
        if (attributeName.equalsIgnoreCase(Constants.ID)) {
            return MethodName.GET_ID;
        }
        
        return MethodName.GET + capitalizeFirstLetter(attributeName);
    }
	
	public static String getSetterName(String attributeName) {
        if (attributeName == null || attributeName.isEmpty()) {
            return "";
        }

        // Special handling for common patterns
        if (attributeName.equalsIgnoreCase(Constants.ID)) {
            return MethodName.SET_ID;
        }
        
        return MethodName.SET + capitalizeFirstLetter(attributeName);
    }

	public static String capitalizeFirstLetter(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}
