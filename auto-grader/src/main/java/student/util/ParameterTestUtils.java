package student.util;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import student.model.ParameterTest;

public class ParameterTestUtils {
	public static ParameterTest[] toArray(Class<?>... types) {
		return Stream.of(types).map(ParameterTest::new).toArray(ParameterTest[]::new);
	}

	public static boolean compareTestValue(Class<?> clazz, Object instance, ParameterTest paramTest)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Object castValue = (paramTest.getType().isPrimitive() ? ClassUtils.boxing(paramTest.getType())
				: paramTest.getType())
				.cast(clazz.getDeclaredMethod(GetterUtils.getGetterName(paramTest.getName())).invoke(instance));
		return castValue != null ? castValue.equals(paramTest.getTestValue()) : paramTest.getTestValue() == null;
	}
}
