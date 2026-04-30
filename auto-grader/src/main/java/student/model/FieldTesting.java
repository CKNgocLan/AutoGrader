package student.model;

import java.util.List;

public class FieldTesting extends Field {

	public FieldTesting(Class<?> type, String name) {
		super(type, name);
	}
	
	public FieldTesting(Class<? extends List> listClass, Class<?> typeParameter, String name) {
		super(listClass, typeParameter, name);
	}
}
