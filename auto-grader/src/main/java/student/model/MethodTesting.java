package student.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

import student.constant.ExceptionMessage;
import student.constant.MethodName;
import student.constant.PropertyName;
import student.exception.InvalidConfigurationException;
import student.util.ClassUtils;
import student.util.MethodUtils;
import student.util.NumbericUtils;

public class MethodTesting {
	private int modifier;
	private String name;
	private Class<?> returnedType;
	private ParameterTesting[] parameters = {};
	private Object expectedValue;
	private Object returnedValue;
	private Class<?> clazz;
	private Object instance;
	private boolean isStatic;
	private boolean isFinal;
	private boolean isPublic;
	
	public MethodTesting() {
		
	}

	public MethodTesting(Class<?> returnedType, String name) {
		this.modifier = Modifier.PUBLIC;
		this.returnedType = returnedType;
		this.name = name;
	}

	public MethodTesting(Class<?> returnedType, String name, ParameterTesting... parameters) {
		this.modifier = Modifier.PUBLIC;
		this.returnedType = returnedType;
		this.name = name;
		this.parameters = parameters;
	}

	public MethodTesting(int modifier, Class<?> returnedType, String name, ParameterTesting... parameters) {
		this.modifier = modifier;
		this.returnedType = returnedType;
		this.name = name;
		this.parameters = parameters;
	}

	public int getModifier() {
		return modifier;
	}

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getReturnedType() {
		return returnedType;
	}

	public void setReturnedType(Class<?> returnedType) {
		this.returnedType = returnedType;
	}

	public ParameterTesting[] getParameters() {
		return parameters;
	}

	public void setParameter(ParameterTesting... parameters) {
		this.parameters = parameters;
	}
	
	public Class<?> boxingReturnedType() {
		return returnedType.isPrimitive() ? ClassUtils.boxing(returnedType): returnedType;
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

	public String invokeToString()
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return String.valueOf(clazz.getMethod(MethodName.TO_STRING).invoke(instance));
	}

	public Object invokeSetter() throws NoSuchMethodException, SecurityException, InvalidConfigurationException,
			IllegalAccessException, InvocationTargetException {
		isConfigured();

		return clazz.getDeclaredMethod(name, this.getParameterTypes()).invoke(instance,
				getParameterValues());
	}
	
	public Class<?>[] getParameterTypes() {
		return Stream.of(this.parameters).map(param -> param.getType()).toArray(Class<?>[]::new);
	}
	
	public Object[] getParameterValues() {
		return Stream.of(parameters).map(param -> param.getValue()).toArray();
	}
	
	public boolean equals(java.lang.reflect.Method reflectMethod) {
		return reflectMethod.getModifiers() == this.modifier && equals(reflectMethod);
	}
	
	public boolean equalsButModifiers(java.lang.reflect.Method reflectMethod) {
		return reflectMethod.getName().equals(this.name)
				&& reflectMethod.getReturnType().equals(this.returnedType)
				&& Arrays.equals(this.getParameterTypes(), MethodUtils.getParameterTypes(reflectMethod))
				;
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

		return clazz.getDeclaredMethod(name, getParameterTypes()).invoke(instance, getParameterValues());
	}
	
	public Double returnNumbericAbs() throws NumberFormatException, NoSuchMethodException,
			SecurityException, IllegalAccessException, InvocationTargetException, InvalidConfigurationException {
		return Math.abs(NumbericUtils.toDouble(boxingReturnedType().cast(returning())))
				- NumbericUtils.toDouble(expectedValue);
	}

	public boolean assertExpectedValue(Object expected) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, InvocationTargetException, InvalidConfigurationException {
		return this.boxingReturnedType().cast(returning()).equals(expected);
	}
}
