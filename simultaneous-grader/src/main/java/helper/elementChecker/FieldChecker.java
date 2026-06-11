package helper.elementChecker;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import common.util.ClassUtils;
import common.util.StringUtils;
import model.element.TestingField;

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

	public boolean checkDeclaration(Field reflectField, String name, Class<?> type) {
		return checkDeclaration(reflectField, Modifier.PRIVATE, name, type);
	}
	
	public boolean checkPublicStaticFinalField(Class<?> clazz, Field field) {
		return ClassUtils.containFieldButModifiers(clazz, new TestingField(field.getType(), field.getName()))
				&& Modifier.isPublic(field.getModifiers())
				&& Modifier.isStatic(field.getModifiers())
				&& Modifier.isFinal(field.getModifiers())
				&& StringUtils.isAllUppercase(field.getName())
				;
	}

	public boolean checkDeclaration(Field reflectField, int modifier, String name,
			Class<?> type) {
		return reflectField.getModifiers() == modifier && reflectField.getName().equals(name)
				&& reflectField.getType().equals(type);
	}

	public boolean compareValue(TestingField fieldTesting, Object actualValue) {
		return fieldTesting.getValue().equals(actualValue);
	}
}
