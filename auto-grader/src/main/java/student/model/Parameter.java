package student.model;

public class Parameter {
	private String name;
	private Class<?> type;

	public Parameter() {
	}

	public Parameter(String name, Class<?> type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<Object> type) {
		this.type = type;
	}
}
