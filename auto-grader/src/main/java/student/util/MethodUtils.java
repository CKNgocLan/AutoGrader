package student.util;

import java.lang.reflect.Parameter;
import java.util.stream.Stream;

import student.constant.MethodName;
import student.model.TestingMethod;

public class MethodUtils {
	public static Class<?>[] getParameterTypes(java.lang.reflect.Method method) {
		return Stream.of(method.getParameters()).map(Parameter::getType).toArray(Class<?>[]::new);
	}
	
	public static String getJoinedName(CharSequence delimeter, TestingMethod... method) {
		return String.join(delimeter, Stream.of(method).map(m -> m.getName()).toList());
	}
	
	/*
	 * common methods
	 */
	public static TestingMethod createMethodToString() {
		return new TestingMethod(String.class, MethodName.TO_STRING);
	}
	
	public static TestingMethod createMethodEquals(String paramName, Class<?> paramType) {
		return new TestingMethod(boolean.class, MethodName.EQUALS, new student.model.TestingParameter(paramType, paramName));
	}
}
