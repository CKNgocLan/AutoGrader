package student.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import student.constant.ExceptionMessage;
import student.constant.PropertyName;
import student.exception.InvalidConfigurationException;

public class MethodTesting extends Method {
	private Object testingValue;
	private Class<?> clazz;
	private Object instance;
	
	public MethodTesting(String name, Class<?> returnedType, Object testingValue) {
		super(name, returnedType);
		this.testingValue = testingValue;
	}
	
	public MethodTesting(String name, Class<?> returnedType, Object testingValue, Parameter... parameters) {
		super(Modifier.PUBLIC, returnedType, name, parameters);
		this.testingValue = testingValue;
		
	}

	public Object getTestingValue() {
		return testingValue;
	}

	public void setTestingValue(Object testingValue) {
		this.testingValue = testingValue;
	}
	
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

	public Object returning() throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException, InvalidConfigurationException {
		if (clazz == null) {
			throw new InvalidConfigurationException(
					ExceptionMessage.PROPERTY_NOT_CONFIGURED.getContent(PropertyName.CLASS));
		}
		if (instance == null) {
			throw new InvalidConfigurationException(
					ExceptionMessage.PROPERTY_NOT_CONFIGURED.getContent(PropertyName.INSTANCE));
		}

		return clazz.getDeclaredMethod(super.getName()).invoke(instance);
	}
}
