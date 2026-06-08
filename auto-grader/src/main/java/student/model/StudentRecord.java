package student.model;

import student.constant.ExceptionMessage;
import student.constant.FieldName;
import student.util.StringUtils;

public record StudentRecord(String number, String name) {
	public StudentRecord {
		if (StringUtils.isNullOrEmpty(name)) {
			throw new IllegalArgumentException(ExceptionMessage.PROPERTY_CANNOT_BE_NULL_OR_EMPTY.getContent(FieldName.NAME, this.getClass().getName()));
		}
		if (StringUtils.isNullOrEmpty(number)) {
			throw new IllegalArgumentException(ExceptionMessage.PROPERTY_CANNOT_BE_NULL_OR_EMPTY.getContent(FieldName.NUMBER, this.getClass().getName()));
		}
	}

	public StudentRecord(String[] fileName) {
		this(fileName[0], fileName[1]);
	}

	@Override
	public String toString() {
		return "Student Infor: Number: %s - Name: %s".formatted(this.number, this.name);
	}
}
