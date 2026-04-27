package student.model;

public class FieldTesting extends Field {
//	private Object testingValue;
//	private Class<?> clazz;
//	private Object instance;

	public FieldTesting(Class<?> type, String name) {
		super(type, name);
	}

	public boolean equals(java.lang.reflect.Field reflectField) {
		return getModifier() == reflectField.getModifiers()
				&& getType().equals(reflectField.getType())
				&& getName().equals(reflectField.getName())
				;
	}
}
