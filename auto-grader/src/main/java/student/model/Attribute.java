package student.model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import student.util.TestCaseUtil;

public class Attribute {
	private int modifier;
	private String name;
	private Class<?> datatype;

	public Attribute(int modifier, String name, Class<?> datatype) {
		this.modifier = modifier;
		this.name = name;
		this.datatype = datatype;
	}

	public Attribute(Field field) {
		this.modifier = field.getModifiers();
		this.name = field.getName();
		this.datatype = field.getType();
	}

	public boolean checkModifier() {
		return Modifier.isPrivate(this.modifier) && TestCaseUtil.isCamelCase(this.name);
	}
}
