package student.model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import student.util.TestCaseUtil;

public class InvalidMethod {
	private int modifier;
	private String name;
	private Class<?> datatype;
	
	public InvalidMethod() {
	}
	
	public InvalidMethod(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public InvalidMethod(int modifier, String name, Class<?> datatype) {
//		this.modifier = modifier;
//		this.name = name;
//		this.datatype = datatype;
//	}
//
//	public InvalidMethod(Field field) {
//		this.modifier = field.getModifiers();
//		this.name = field.getName();
//		this.datatype = field.getType();
//	}
//
//	public boolean checkModifier() {
//		return Modifier.isPrivate(this.modifier) && TestCaseUtil.isCamelCase(this.name);
//	}
}
