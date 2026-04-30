package student.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import student.constant.ExceptionMessage;
import student.constant.PropertyName;
import student.exception.InvalidConfigurationException;

public class MethodTesting extends Method {
	private Object testingValue;
	private Object returnedValue;
	private Class<?> clazz;
	private Object instance;

	public MethodTesting(Class<?> returnedType, String name) {
		super(returnedType, name);
	}

	public MethodTesting(Class<?> returnedType, String name, Object testingValue, Parameter... parameters) {
		super(Modifier.PUBLIC, returnedType, name, parameters);
		this.testingValue = testingValue;

	}

	public Object getTestingValue() {
		return testingValue;
	}

	public void setTestingValue(Object testingValue) {
		this.testingValue = testingValue;
	}

	public Object getReturnedValue() {
		return returnedValue;
	}

	public void setReturnedValue(Object returnedValue) {
		this.returnedValue = returnedValue;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

	public Object invokeSetting() throws NoSuchMethodException, SecurityException, InvalidConfigurationException,
			IllegalAccessException, InvocationTargetException {
		isConfigured();

		return clazz.getDeclaredMethod(super.getName(), super.getParameterTypes()).invoke(instance,
				getParameterValues());
	}

	public Object returning() throws NoSuchMethodException, SecurityException, InvalidConfigurationException,
			IllegalAccessException, InvocationTargetException {
		isConfigured();

		return clazz.getDeclaredMethod(super.getName()).invoke(instance);
	}

	private boolean isConfigured() throws InvalidConfigurationException {
		if (clazz == null) {
			throw new InvalidConfigurationException(
					ExceptionMessage.PROPERTY_NOT_CONFIGURED.getContent(PropertyName.CLASS));
		}
		if (instance == null) {
			throw new InvalidConfigurationException(
					ExceptionMessage.PROPERTY_NOT_CONFIGURED.getContent(PropertyName.INSTANCE));
		}

		return true;
	}
}
