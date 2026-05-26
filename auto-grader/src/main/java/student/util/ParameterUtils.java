package student.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.stream.Stream;

import student.model.TestingParameter;

public class ParameterUtils {
	public static TestingParameter[] mapFromTypes(Class<?>... types) {
		return Stream.of(types).map(TestingParameter::new).toArray(TestingParameter[]::new);
	}

	public static boolean compareTestingValueViaGetter(Class<?> clazz, Object instance, TestingParameter paramTest)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Object castValue = (paramTest.getType().isPrimitive() ? ClassUtils.boxing(paramTest.getType())
				: paramTest.getType())
				.cast(clazz.getDeclaredMethod(GetterUtils.getGetterName(paramTest.getName())).invoke(instance));
		return castValue != null ? castValue.equals(paramTest.getValue()) : paramTest.getValue() == null;
	}
	
	public static Class<?>[] mapToType(TestingParameter... parameterTestings) {
		return Stream.of(parameterTestings).map(param -> param.getType()).toArray(Class<?>[]::new);
	}
	
	public static Class<?>[] mapToConstructorType(TestingParameter... parameterTestings) {
		return Stream.of(parameterTestings)
				.filter(param -> !param.isSkipConstruction())
				.map(param -> param.getType())
				.toArray(Class<?>[]::new);
	}
	
	public static Object[] mapToTestingValue(TestingParameter...parameterTestings) {
		return Stream.of(parameterTestings).map(param -> param.getValue()).toArray(Object[]::new);
	}
	
	public static Object[] mapToConstructorValue(TestingParameter...parameterTestings) {
		return Stream.of(parameterTestings)
				.filter(param -> !param.isSkipConstruction())
				.map(param -> param.getValue())
				.toArray(Object[]::new);
	}
	
	public static TestingParameter[] mapFromParameters(Parameter... parameters) {
		return Stream.of(parameters).map(param -> new TestingParameter(param)).toArray(TestingParameter[]::new);
	}
}
