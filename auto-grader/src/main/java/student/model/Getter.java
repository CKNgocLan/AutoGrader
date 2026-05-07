package student.model;

import java.lang.reflect.Field;

import student.util.GetterUtils;

public class Getter extends MethodTesting {
	public Getter() {
		super();
	}

	public Getter(Field field) {
		super(field.getType(), GetterUtils.getGetterName(field.getName()));
	}
	
	public Getter(String name, Class<?> returnType) {
		super(returnType, name);
	}
	
	@Override
	public String toString() {
		return "{%s %s() %s}".formatted(this.getModifier(), this.getName(), this.getReturnedType());
	}
	
	public boolean equals(Getter getter) {
		return getter.getModifier() == this.getModifier()
				&& getter.getName().equals(this.getName())
				&& getter.getReturnedType().equals(this.getReturnedType())
				;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		
		return this.equals((Getter)obj);
	}
}
