package student.model;

import java.lang.reflect.Modifier;
import java.util.List;

public class FieldTesting {
	private int modifier;
	private Class<?> type;
	private String name;
	private Class<?> typeParameter;
	private boolean isStatic;
	private boolean isFinal;
	private boolean isPublic;

	public FieldTesting(Class<?> type, String name) {
		this.modifier = Modifier.PRIVATE;
		this.type = type;
		this.name = name;
	}
	
	public FieldTesting(Class<? extends List> type, Class<?> typeParameter, String name) {
		this.modifier = Modifier.PRIVATE;
		this.type = type;
		this.typeParameter = typeParameter;
		this.name = name;
	}
	
	public FieldTesting(int modifier, Class<?> type, String name) {
		this.modifier = modifier;
		this.type = type;
		this.name = name;
	}
	
	public FieldTesting asStatic() {
		this.isStatic = true;
		return this;
	}
	
	public int getModifier() {
		return modifier;
	}

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getTypeParameter() {
		return typeParameter;
	}

	public void setTypeParameter(Class<?> typeParameter) {
		this.typeParameter = typeParameter;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		if (obj instanceof java.lang.reflect.Field) {
			return this.equals((java.lang.reflect.Field)obj);
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public boolean equals(java.lang.reflect.Field reflectField) {
		return modifier == reflectField.getModifiers()
				&& type.equals(reflectField.getType())
				&& name.equals(reflectField.getName())
				;
	}
	
	public boolean equalsButModifiers(java.lang.reflect.Field reflectField) {
		return type.equals(reflectField.getType())
				&& name.equals(reflectField.getName());
	}
	
	public boolean isStatic() {
		return this.isStatic;
	}
	
	public FieldTesting asFinal() {
		this.isFinal = true;
		return this;
	}
	
	public boolean isFinal() {
		return this.isFinal;
	}
	
	public FieldTesting asPublic() {
		this.isPublic = true;
		return this;
	}
	
	public boolean isPublic() {
		return this.isPublic;
	}
}
