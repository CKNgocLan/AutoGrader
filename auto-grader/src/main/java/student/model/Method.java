package student.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Method {
	private int modifier;
	private String name;
	private Class<?> returnedType;
	private Class<?>[] parameterTypes = {};

	public Method() {}

	public Method(String name, Class<?> returnType) {
		this.modifier = Modifier.PUBLIC;
		this.name = name;
		this.returnedType = returnType;
	}
	
	public Method(int modifier, Class<?> returnedType, String name, Class<?>... parameterTypes) {
		this.modifier = modifier;
		this.returnedType = returnedType;
		this.name = name;
		this.parameterTypes = parameterTypes;
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

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}
	
	public boolean equals(java.lang.reflect.Method reflectMethod) {
		return reflectMethod.getModifiers() == this.modifier
				&& reflectMethod.getName().equals(this.name)
				&& reflectMethod.getReturnType().equals(this.returnedType)
				&& Arrays.equals(this.parameterTypes, reflectMethod.getParameters())
				;
	}
	
	public void invoke(Class<?> clazz, Object instance) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		clazz.getDeclaredMethod(this.name).invoke(instance);
	}
}
