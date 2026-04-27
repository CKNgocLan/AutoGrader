package student.util;

import java.lang.reflect.Modifier;

public class FieldUtils {
	public static boolean checkFieldDeclaration(java.lang.reflect.Field reflectField, String name, Class<?> type) {
		return checkFieldDeclaration(reflectField, Modifier.PRIVATE, name, type);
	}

	public static boolean checkFieldDeclaration(java.lang.reflect.Field reflectField, int modifier, String name,
			Class<?> type) {
		return reflectField.getModifiers() == modifier && reflectField.getName().equals(name)
				&& reflectField.getType().equals(type);
	}
}
