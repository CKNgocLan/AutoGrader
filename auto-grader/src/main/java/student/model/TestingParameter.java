package student.model;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class TestingParameter {
	private String name;
	private Class<?> type;
	private Object value;
	private boolean skipConstruction;

	public TestingParameter(Class<?> type) {
		this.type = type;
	}

	public TestingParameter(Class<?> type, String name) {
		this.type = type;
		this.name = name;
	}

	public TestingParameter(Class<?> type, String name, Object value) {
		this.type = type;
		this.name = name;
		this.value = value;
	}

	public TestingParameter(Class<?> type, String name, Object value, boolean skipConstruction) {
		this.type = type;
		this.name = name;
		this.value = value;
		this.skipConstruction = skipConstruction;
	}

	public TestingParameter(Parameter parameter) {
		this(parameter.getType(), parameter.getName());
	}

	public TestingParameter(Parameter parameter, boolean skipConstruction) {
		this(parameter.getType(), parameter.getName(), null, skipConstruction);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public TestingParameter value(Object value) {
		this.value = value;
		return this;
	}

	public boolean isSkipConstruction() {
		return skipConstruction;
	}

	public void setSkipConstruction(boolean skipConstruction) {
		this.skipConstruction = skipConstruction;
	}

	public boolean equalsEnumConstant(Class<? extends Enum<?>> enumClass, Object enumValue) {
		if (!this.type.isEnum()) {
			return false;
		}

		try {
			Stream.of(enumClass.getEnumConstants()).filter(e -> e.name().equals(enumValue)).findFirst().get();
		} catch (NoSuchElementException e) {
			return false;
		}

		return true;
	}
}
