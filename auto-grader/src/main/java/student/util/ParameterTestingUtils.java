package student.util;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import student.model.ParameterTesting;

public class ParameterTestingUtils {
	public static ParameterTesting[] mapFromTypes(Class<?>... types) {
		return Stream.of(types).map(ParameterTesting::new).toArray(ParameterTesting[]::new);
	}

	public static boolean compareTestingValue(Class<?> clazz, Object instance, ParameterTesting paramTest)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Object castValue = (paramTest.getType().isPrimitive() ? ClassUtils.boxing(paramTest.getType())
				: paramTest.getType())
				.cast(clazz.getDeclaredMethod(GetterUtils.getGetterName(paramTest.getName())).invoke(instance));
		return castValue != null ? castValue.equals(paramTest.getValue()) : paramTest.getValue() == null;
	}
	
	public static Class<?>[] mapToType(ParameterTesting... parameterTestings) {
		return Stream.of(parameterTestings).map(param -> param.getType()).toArray(Class<?>[]::new);
	}
	
	public static Object[] mapToTestingValue(ParameterTesting...parameterTestings) {
		return Stream.of(parameterTestings).map(param -> param.getValue()).toArray(Object[]::new);
	}
}
