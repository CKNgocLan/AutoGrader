package student.model;

public class ParameterTesting extends Parameter {
	private Object testingValue;
	
	public ParameterTesting(Class<?> type) {
		super(null, type);
	}

	public ParameterTesting(String name, Class<?> type, Object testValue) {
		super(name, type);
		this.testingValue = testValue;
	}

	public ParameterTesting(String name, Class<?> type, Object testValue, boolean skipConstruction) {
		super(name, type, skipConstruction);
		this.testingValue = testValue;
	}

	public Object getTestingValue() {
		return testingValue;
	}

	public void setTestingValue(Object testingValue) {
		this.testingValue = testingValue;
	}
}
