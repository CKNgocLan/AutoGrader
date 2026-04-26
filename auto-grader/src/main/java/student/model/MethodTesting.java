package student.model;

public class MethodTesting extends Method {
	private Object testingValue;
	
	public MethodTesting(String name, Class<?> returnedType, Object testingValue) {
		super(name, returnedType);
		this.testingValue = testingValue;
	}

	public Object getTestingValue() {
		return testingValue;
	}

	public void setTestingValue(Object testingValue) {
		this.testingValue = testingValue;
	}
}
