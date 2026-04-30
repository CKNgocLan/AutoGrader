package student.model;

import java.lang.reflect.InvocationTargetException;

public class TesterFactory {
	public static ATester getInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return ATester.class.cast(clazz.getDeclaredConstructor().newInstance());
	}
}
