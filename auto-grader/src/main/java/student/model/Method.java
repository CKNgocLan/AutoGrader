package student.model;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

import student.util.ClassUtils;
import student.util.MethodUtils;

public class Method {
	private int modifier;
	private String name;
	private Class<?> returnedType;
	private Parameter[] parameters = {};

	public Method() {}

	public Method(String name, Class<?> returnType) {
		this.modifier = Modifier.PUBLIC;
		this.name = name;
		this.returnedType = returnType;
	}
	
	public Method(int modifier, Class<?> returnedType, String name, Parameter... parameters) {
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

	public Parameter[] getParameters() {
		return parameters;
	}

	public void setParameter(Parameter... parameters) {
		this.parameters = parameters;
	}
	
	public Class<?>[] getParameterTypes() {
		return Stream.of(this.parameters).map(param -> param.getType()).toArray(Class<?>[]::new);
	}
	
	public Object[] getParameterValues() {
		return Stream.of(parameters).map(param -> param.getValue()).toArray();
	}
	
	public boolean equals(java.lang.reflect.Method reflectMethod) {
		return reflectMethod.getModifiers() == this.modifier
				&& reflectMethod.getName().equals(this.name)
				&& reflectMethod.getReturnType().equals(this.returnedType)
				&& Arrays.equals(this.getParameterTypes(), MethodUtils.getParameterTypes(reflectMethod))
				;
	}
	
	public Class<?> boxingReturnedType() {
		return returnedType.isPrimitive() ? ClassUtils.boxing(returnedType): returnedType;
	}
}
