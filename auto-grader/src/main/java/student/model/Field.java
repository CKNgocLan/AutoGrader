package student.model;

public class Field {
	private int modifier;
	private Class<?> type;
	private String name;

	public Field(Class<?> type, String name) {
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

}
