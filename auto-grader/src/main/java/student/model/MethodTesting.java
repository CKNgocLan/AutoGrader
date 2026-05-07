package student.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import student.constant.ExceptionMessage;
import student.constant.MethodName;
import student.constant.PropertyName;
import student.exception.InvalidConfigurationException;
import student.util.NumbericUtils;
import student.util.ParameterTestingUtils;

public class MethodTesting extends Method {
	private Object expectedValue;
	private Object returnedValue;
	private Class<?> clazz;
	private Object instance;
	private boolean isStatic;
	private boolean isFinal;
	private boolean isPublic;

	public MethodTesting(Class<?> returnedType, String name) {
		super(returnedType, name);
	}

	public MethodTesting(Class<?> returnedType, String name, ParameterTesting... parameters) {
		super(Modifier.PUBLIC, returnedType, name, parameters);
	}

	public MethodTesting(int modifier, Class<?> returnedType, String name, ParameterTesting... parameters) {
		super(modifier, returnedType, name, parameters);
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
	
	public String getCorrespondingClassName() {
		return this.clazz.getSimpleName();
	}
	
	public MethodTesting expectedValue(Object expectedValue) {
		this.expectedValue = expectedValue;
		return this;
	}
	
	public Object getExpectedValue() {
		return this.expectedValue;
	}
	
	public MethodTesting asStatic() {
		this.isStatic = true;
		return this;
	}
	
	public boolean isStatic() {
		return this.isStatic;
	}
	
	public MethodTesting asFinal() {
		this.isFinal = true;
		return this;
	}
	
	public boolean isFinal() {
		return this.isFinal;
	}
	
	public MethodTesting asPublic() {
		this.isPublic = true;
		return this;
	}
	
	public boolean isPublic() {
		return this.isPublic;
	}

	public Object invokeSetter() throws NoSuchMethodException, SecurityException, InvalidConfigurationException,
			IllegalAccessException, InvocationTargetException {
		isConfigured();

		return clazz.getDeclaredMethod(super.getName(), super.getParameterTypes()).invoke(instance,
				getParameterValues());
	}

	public String invokeToString()
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return String.valueOf(clazz.getMethod(MethodName.TO_STRING).invoke(instance));
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

	public MethodTesting config(Class<?> clazz, Object instance) {
		this.clazz = clazz;
		this.instance = instance;

		return this;
	}

	public Object returning() throws NoSuchMethodException, SecurityException, InvalidConfigurationException,
			IllegalAccessException, InvocationTargetException {
		isConfigured();

		return clazz.getDeclaredMethod(super.getName(), getParameterTypes()).invoke(instance);
	}
	
	public Double returnNumbericAbs() throws NumberFormatException, NoSuchMethodException,
			SecurityException, IllegalAccessException, InvocationTargetException, InvalidConfigurationException {
		return Math.abs(NumbericUtils.toDouble(boxingReturnedType().cast(returning())))
				- NumbericUtils.toDouble(expectedValue);
	}

	public boolean assertExpectedValue(Object expected) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, InvocationTargetException, InvalidConfigurationException {
		return super.boxingReturnedType().cast(returning()).equals(expected);
	}
}
