package student.model;

import java.lang.reflect.Field;
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
import student.util.StringUtils;
import student.util.ValueUtils;

public class TestingMethod {
	private int modifier;
	private String name;
	private Class<?> returnedType;
	private TestingParameter[] parameters = {};
	private Object expectedValue;
	private Object returnedValue;
	private Class<?> clazz;
	private Object instance;
	private boolean isStatic;
	private boolean isFinal;
	private boolean isPublic;
	private boolean isAbstract;

	public TestingMethod(Class<?> returnedType, String name) {
		this.modifier = Modifier.PUBLIC;
		this.returnedType = returnedType;
		this.name = name;
	}

	public TestingMethod(Class<?> returnedType, String name, TestingParameter... parameters) {
		this.modifier = Modifier.PUBLIC;
		this.returnedType = returnedType;
		this.name = name;
		this.parameters = parameters;
	}

	public TestingMethod(int modifier, Class<?> returnedType, String name, TestingParameter... parameters) {
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

	public TestingParameter[] getParameters() {
		return parameters;
	}

	public void setParameter(TestingParameter... parameters) {
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
	
	public TestingMethod expectedValue(Object expectedValue) {
		this.expectedValue = expectedValue;
		return this;
	}
	
	public Object getExpectedValue() {
		return this.expectedValue;
	}
	
	public TestingMethod asStatic() {
		this.isStatic = true;
		return this;
	}
	
	public boolean isStatic() {
		return this.isStatic;
	}

	public TestingMethod asFinal() {
		this.isFinal = true;
		return this;
	}
	
	public boolean isFinal() {
		return this.isFinal;
	}
	
	public TestingMethod asPublic() {
		this.isPublic = true;
		return this;
	}
	
	public boolean isPublic() {
		return this.isPublic;
	}
	
	public TestingMethod asAbstract() {
		this.isAbstract = true;
		return this;
	}
	
	public boolean isAbstract() {
		return this.isAbstract;
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
		return reflectMethod.getModifiers() == this.modifier && equalsButModifiers(reflectMethod);
	}
	
	public boolean equalsButModifiers(java.lang.reflect.Method reflectMethod) {
		return reflectMethod.getName().equals(this.name)
				&& reflectMethod.getReturnType().equals(this.returnedType)
				&& Arrays.equals(this.getParameterTypes(), MethodUtils.getParameterTypes(reflectMethod))
				;
	}
	
	/*
	 * *************************
	 */

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

	public TestingMethod config(Class<?> clazz, Object instance) {
		this.clazz = clazz;
		this.instance = instance;

		return this;
	}
	
	public Class<?> getConfiguredClass() {
		return this.clazz;
	}
	
	public Object getConfiguredInstance() {
		return this.instance;
	}
	
	/*
	 * *************************
	 */

	public Object returning() throws NoSuchMethodException, SecurityException, InvalidConfigurationException,
			IllegalAccessException, InvocationTargetException {
		isConfigured();

		return clazz.getDeclaredMethod(name, getParameterTypes()).invoke(instance, getParameterValues());
	}
	
	public Object returningInPrivate() throws NoSuchMethodException, SecurityException, InvalidConfigurationException,
			IllegalAccessException, InvocationTargetException {
		isConfigured();

		java.lang.reflect.Method method = clazz.getDeclaredMethod(name, getParameterTypes());
		method.setAccessible(true);
		return method.invoke(instance, getParameterValues());
	}
	
	public Double returnNumbericAbs() throws NumberFormatException, NoSuchMethodException,
			SecurityException, IllegalAccessException, InvocationTargetException, InvalidConfigurationException {
		return Math.abs(NumbericUtils.toDouble(boxingReturnedType().cast(returning()))
				- NumbericUtils.toDouble(expectedValue));
	}
	
	public Boolean returnBooleanInPrivate() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, InvalidConfigurationException {
		return Boolean.valueOf(StringUtils.toString(returningInPrivate()));
	}
	
	public boolean returnBooleanPrimitive() throws InvalidConfigurationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		isConfigured();

		return ValueUtils.toBooleanPrimitive(clazz.getDeclaredMethod(name, getParameterTypes()).invoke(instance, getParameterValues()));
	}
	
	public boolean assertExpectedBoolean() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, InvalidConfigurationException {
		return returnBooleanInPrivate().equals(expectedValue);
	}
	
	public void returnVoid() throws InvalidConfigurationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		isConfigured();
		
		clazz.getDeclaredMethod(name, getParameterTypes()).invoke(instance, getParameterValues());
	}
	
	/*
	 * *************************
	 */
	
	public Object getUpdatedValue(String fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Field updatedField = clazz.getDeclaredField(fieldName);
		updatedField.setAccessible(true);

		return updatedField.get(instance);
	}

	@Deprecated
	public void updateIntegerFieldValue(String fieldName, int value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field updatedField = clazz.getDeclaredField(fieldName);
		boolean canAccessed = updatedField.canAccess(instance);
		updatedField.setAccessible(true);
		
		updatedField.setInt(instance, value);
		updatedField.setAccessible(canAccessed);
	}

	@Deprecated
	public boolean assertExpectedValue(Object expected) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, InvocationTargetException, InvalidConfigurationException {
		return this.boxingReturnedType().cast(returning()).equals(expected);
	}
}
