package student.util;

import java.lang.reflect.Parameter;
import java.util.stream.Stream;

import student.model.Method;

public class MethodUtils {
	public static boolean isMethodDeclared(Class<?> clazz, Method method) throws NoSuchMethodException, SecurityException {
		return method.equals(clazz.getDeclaredMethod(method.getName(), method.getParameterTypes()));
	}
	
	public static Class<?>[] getParameterTypes(java.lang.reflect.Method method) {
		return Stream.of(method.getParameters()).map(Parameter::getType).toArray(Class<?>[]::new);
	}
}
