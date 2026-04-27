package student.checker;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import student.util.StringUtils;

public class FieldChecker {
	private static FieldChecker instance = null;

	public static FieldChecker getInstance() {
		if (instance == null) {
			instance = new FieldChecker();
		}

		return instance;
	}

	/*
	 * ***************************************************************************
	 */

	public boolean checkField(Field field) {
		if (Modifier.isStatic(field.getModifiers())) {
			return StringUtils.isAllUppercase(field.getName());
		}
		return Modifier.isPrivate(field.getModifiers()) && StringUtils.isCamelCase(field.getName());
	}

	public boolean checkPrivateStaticField(Field field) {
		return Modifier.isPrivate(field.getModifiers()) && Modifier.isStatic(field.getModifiers())
				&& StringUtils.isAllUppercase(field.getName());
	}

	public boolean checkDeclaration(java.lang.reflect.Field reflectField, String name, Class<?> type) {
		return checkDeclaration(reflectField, Modifier.PRIVATE, name, type);
	}

	public boolean checkDeclaration(java.lang.reflect.Field reflectField, int modifier, String name,
			Class<?> type) {
		return reflectField.getModifiers() == modifier && reflectField.getName().equals(name)
				&& reflectField.getType().equals(type);
	}

}
