package student.util;

import java.lang.reflect.Parameter;
import java.util.stream.Stream;

import student.constant.MethodName;
import student.model.MethodTesting;

public class MethodUtils {
	public static Class<?>[] getParameterTypes(java.lang.reflect.Method method) {
		return Stream.of(method.getParameters()).map(Parameter::getType).toArray(Class<?>[]::new);
	}
	
	public static String getJoinedName(CharSequence delimeter, MethodTesting... method) {
		return String.join(delimeter, Stream.of(method).map(m -> m.getName()).toList());
	}
	
	/*
	 * common methods
	 */
	public static MethodTesting createMethodToString() {
		return new MethodTesting(String.class, MethodName.TO_STRING);
	}
	
	public static MethodTesting createMethodEquals(String paramName, Class<?> paramType) {
		return new MethodTesting(boolean.class, MethodName.EQUALS, new student.model.ParameterTesting(paramType, paramName));
	}
}
