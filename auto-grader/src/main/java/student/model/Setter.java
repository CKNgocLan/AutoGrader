package student.model;

import java.lang.reflect.Field;

import student.util.TestCaseUtil;

public class Setter extends InvalidMethod {
	private Class<?> paramType;

	public Setter(Field field) {
		super(TestCaseUtil.getSetterName(field.getName()), void.class);
		this.paramType = field.getType();
	}
	
	public Setter(String name, Class<?> paramType) {
		super(name, void.class);
		this.paramType = paramType;
	}

	public Class<?> getParamType() {
		return paramType;
	}

	public void setParamType(Class<?> paramType) {
		this.paramType = paramType;
	}
	
	@Override
	public String toString() {
		return "{%s %s() %s}".formatted(this.getModifier(), this.getName(), this.getParamType());
	}
	
	public boolean equals(Setter setter) {
		return setter.getModifier() == this.getModifier()
				&& setter.getName().equals(this.getName())
				&& setter.getReturnType().equals(this.getReturnType())
				;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		
		return this.equals((Setter)obj);
	}
}
