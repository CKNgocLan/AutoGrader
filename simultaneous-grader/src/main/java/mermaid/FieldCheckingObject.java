package mermaid;

public class FieldCheckingObject extends CheckingObject {
	private Scope scope;
	private String dataType;
	private boolean isStatic;
	private boolean isFinal;
	private String initValue;

	public FieldCheckingObject(Scope scope, String dataType, String name, boolean isStatic, boolean isFinal,
			String initValue) {
		this.scope = scope;
		this.dataType = dataType;
		this.name = name;
		this.isStatic = isStatic;
		this.isFinal = isFinal;
		this.initValue = initValue;
	}

	@Override
	public String toString() {
		return "Field: [" + scope + "] " + dataType + " " + name + (isStatic ? " (static)" : "")
				+ (isFinal ? " (final)" : "") + (initValue != null ? " = " + initValue : "");
	}
}
