package common.util;

import java.lang.reflect.Constructor;

public class ConstructorUtils {
	public static Class<?>[] getParameterTypes(Constructor<?> constructor) {
		return constructor.getParameterTypes();
	}
}
