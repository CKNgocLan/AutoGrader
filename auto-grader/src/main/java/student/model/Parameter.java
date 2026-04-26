package student.model;

public class Parameter {
	private String name;
	private Class<?> type;
	private boolean skipConstruction;

	public Parameter() {
	}

	public Parameter(String name, Class<?> type) {
		this.name = name;
		this.type = type;
	}
	
	public Parameter(String name, Class<?> type, boolean skipConstruction) {
		this.name = name;
		this.type = type;
		this.skipConstruction = skipConstruction;
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

	public boolean isSkipConstruction() {
		return skipConstruction;
	}

	public void setSkipConstruction(boolean skipConstruction) {
		this.skipConstruction = skipConstruction;
	}
}
