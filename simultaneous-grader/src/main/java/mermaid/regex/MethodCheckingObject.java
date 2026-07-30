package mermaid.regex;

import java.util.List;

public class MethodCheckingObject extends CheckingObject {
	private Scope scope;
	private String returnType;
	private boolean isAbstract;
	private List<String> parameters;

	public MethodCheckingObject(Scope scope, String returnType, String name, boolean isAbstract,
			List<String> parameters) {
		this.scope = scope;
		this.returnType = returnType;
		this.name = name;
		this.isAbstract = isAbstract;
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "Method: [" + scope + "] " + returnType + " " + name + "(" + String.join(", ", parameters) + ")"
				+ (isAbstract ? " (abstract)" : "");
	}
}
