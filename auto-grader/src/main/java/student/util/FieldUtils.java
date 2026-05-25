package student.util;

import java.util.stream.Stream;

import student.model.TestingField;

public class FieldUtils {
	public static TestingField[] fromSolution(Class<?> clazz) {
		return Stream.of(clazz.getDeclaredFields())
				.map(field -> new TestingField(field.getModifiers(), field.getType(), field.getName()))
				.toArray(TestingField[]::new)
				;
	}
}
