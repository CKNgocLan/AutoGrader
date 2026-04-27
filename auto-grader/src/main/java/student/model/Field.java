package student.model;

import java.lang.reflect.Modifier;

public class Field {
	private int modifier;
	private Class<?> type;
	private String name;

	public Field(Class<?> type, String name) {
		this.modifier = Modifier.PRIVATE;
		this.type = type;
		this.name = name;
	}

	public Field(int modifier, Class<?> type, String name) {
		this.modifier = modifier;
		this.type = type;
		this.name = name;
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
		return getModifier() == reflectField.getModifiers()
				&& getType().equals(reflectField.getType())
				&& getName().equals(reflectField.getName())
				;
	}
}
