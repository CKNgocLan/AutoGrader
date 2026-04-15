package student.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import student.constant.Constants;
import student.constant.MethodName;
import student.model.InvalidMethod;

public class TestCaseUtil {
//	private static TestCaseUtil instance = null;
//	
//	public static TestCaseUtil getInstance() {
//		if (instance == null) {
//			instance = new TestCaseUtil();
//		}
//		
//		return instance;
//	}

	public static boolean checkField(Field field) {
		return Modifier.isPrivate(field.getModifiers())
				&& isCamelCase(field.getName());
	}
	
	public static boolean checkPrivateStaticField(Field field) {
		return Modifier.isPrivate(field.getModifiers())
				&& Modifier.isStatic(field.getModifiers())
				&& isAllUppercase(field.getName());
	}
	
	public static boolean checkGetter(Class<?> clazz, List<InvalidMethod> invalid) {
		List<String> missingGetter = getMissingGetter(clazz);
		
		if (missingGetter.size() > 0) {
			invalid.addAll(missingGetter.stream().map(InvalidMethod::new).collect(Collectors.toList()));
			return false;
		}
		
		return true;
	}
	
	public static boolean checkSetter(Class<?> clazz) {
		return getMissingSetter(clazz).size() < 1;
	}
	
	/* *************************************************************************** */
	
	public static List<String> getMissingGetter(Class<?> clazz) {
		return getGettersFromAttributes(clazz).stream()
				.filter(attrGetter -> !getDeclaredGetterSetter(clazz, MethodName.GET).contains(attrGetter))
				.collect(Collectors.toList())
				;
	}
	
	public static List<String> getMissingSetter(Class<?> clazz) {
		return getSettersFromAttributes(clazz).stream()
				.filter(attrGetter -> !getDeclaredGetterSetter(clazz, MethodName.SET).contains(attrGetter))
				.collect(Collectors.toList())
				;
	}
	
	/* *************************************************************************** */
	
	public static List<String> getGettersFromAttributes(Class<?> clazz) {
		return Stream.of(clazz.getDeclaredFields())
				.filter(field -> !Modifier.isStatic(field.getModifiers()))
				.map(field -> getGetterName(field.getName()))
				.collect(Collectors.toList());
	}
	
	public static List<String> getSettersFromAttributes(Class<?> clazz) {
		return Stream.of(clazz.getDeclaredFields())
				.filter(field -> !Modifier.isStatic(field.getModifiers()))
				.map(field -> getSetterName(field.getName()))
				.collect(Collectors.toList());
	}
	
	public static List<String> getDeclaredGetterSetter(Class<?> clazz, String getset) {
		return Stream.of(clazz.getDeclaredMethods())
				.map(method -> method.getName()).filter(name -> getset.equals(name.subSequence(0, 3)))
				.collect(Collectors.toList());
	}
	
	/* *************************************************************************** */

	public static boolean isCamelCase(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}

		// Must start with lowercase letter
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
