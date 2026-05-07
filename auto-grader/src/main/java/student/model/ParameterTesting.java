package student.model;

public class ParameterTesting {
	private String name;
	private Class<?> type;
	private Object value;
	private Object testingValue;
	private boolean skipConstruction;

	public ParameterTesting(Class<?> type) {
		this.type = type;
	}
	
	public ParameterTesting(Class<?> type, String name) {
		this.type = type;
		this.name = name;
	}

	public ParameterTesting(Class<?> type, String name, Object testingValue) {
		this.type = type;
		this.name = name;
		this.testingValue = testingValue;
	}

	public ParameterTesting(Class<?> type, String name, Object testingValue, boolean skipConstruction) {
		this.type = type;
		this.name = name;
		this.testingValue = testingValue;
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

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isSkipConstruction() {
		return skipConstruction;
	}

	public void setSkipConstruction(boolean skipConstruction) {
		this.skipConstruction = skipConstruction;
	}

	public Object getTestingValue() {
		return testingValue;
	}

	public void setTestingValue(Object testingValue) {
		this.testingValue = testingValue;
	}
}
