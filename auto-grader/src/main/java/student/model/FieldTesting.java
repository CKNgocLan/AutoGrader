package student.model;

import java.util.List;

public class FieldTesting extends Field {
	private boolean isStatic;
	private boolean isFinal;
	private boolean isPublic;

	public FieldTesting(Class<?> type, String name) {
		super(type, name);
	}
	
	public FieldTesting(Class<? extends List> listClass, Class<?> typeParameter, String name) {
		super(listClass, typeParameter, name);
	}
	
	public FieldTesting asStatic() {
		this.isStatic = true;
		return this;
	}
	
	public boolean isStatic() {
		return this.isStatic;
	}
	
	public FieldTesting asFinal() {
		this.isFinal = true;
		return this;
	}
	
	public boolean isFinal() {
		return this.isFinal;
	}
	
	public FieldTesting asPublic() {
		this.isPublic = true;
		return this;
	}
	
	public boolean isPublic() {
		return this.isPublic;
	}
}
