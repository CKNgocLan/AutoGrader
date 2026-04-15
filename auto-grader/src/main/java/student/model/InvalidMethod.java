package student.model;

public class InvalidMethod {
	private int modifier;
	private String name;
	private Class<?> returnType;

	public InvalidMethod() {}

	public InvalidMethod(String name, Class<?> returnType) {
		this.name = name;
		this.returnType = returnType;
	}

	public int getModifier() {
		return modifier;
	}

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getReturnType() {
		return returnType;
	}

	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}
}
