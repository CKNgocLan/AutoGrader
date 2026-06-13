package common.util;

import java.util.stream.Stream;

import common.constant.Constants;
import common.constant.ProblemName;
import model.element.TestingField;


public class FieldUtils {
	public static TestingField[] fromSolution(Class<?> clazz) {
		return Stream.of(clazz.getDeclaredFields())
				.filter(field -> field.getModifiers() != 4122)
				.map(field -> new TestingField(field))
				.toArray(TestingField[]::new)
				;
	}
}
