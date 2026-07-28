package mermaid;

import java.util.ArrayList;
import java.util.List;

public class ClassCheckingObject extends CheckingObject {
	private ClassType type;
	private List<FieldCheckingObject> fields = new ArrayList<>();
	private List<MethodCheckingObject> methods = new ArrayList<>();

	public ClassCheckingObject(String name, ClassType type) {
		this.name = name;
		this.type = type;
	}

	public void setType(ClassType type) {
		this.type = type;
	}

	public void addField(FieldCheckingObject field) {
		fields.add(field);
	}

	public void addMethod(MethodCheckingObject method) {
		methods.add(method);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--- ").append(type).append(" : ").append(name).append(" ---\n");
		for (FieldCheckingObject f : fields)
			sb.append("  ").append(f.toString()).append("\n");
		for (MethodCheckingObject m : methods)
			sb.append("  ").append(m.toString()).append("\n");
		return sb.toString();
	}
}
