package student.model;

public class ParameterTest extends Parameter {
	private Object testValue;
	
	public ParameterTest(Class<?> type) {
		super(null, type);
	}

	public ParameterTest(String name, Class<?> type, Object testValue) {
		super(name, type);
		this.testValue = testValue;
	}

	public Object getTestValue() {
		return testValue;
	}

	public void setTestValue(Object testValue) {
		this.testValue = testValue;
	}
}
