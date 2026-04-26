package student.util;

import java.lang.reflect.InvocationTargetException;

import student.model.Method;
import student.model.MethodTesting;

public class MethodUtils {
	public static boolean isMethodDeclared(Class<?> clazz, Method method) throws NoSuchMethodException, SecurityException {
		return method.equals(clazz.getDeclaredMethod(method.getName()));
	}
}
