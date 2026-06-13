package model.exception;

import common.message.ExceptionMessage;

public class IllegalConstructorParameterException extends IllegalArgumentException {

	public IllegalConstructorParameterException(String name, Object value) {
		super(ExceptionMessage.ILLEGAL_CONSTRUCTOR_PARAMETER.getContent(name, value));
	}

}
