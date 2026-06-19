package model.exception;

import common.message.ExceptionMessage;

@SuppressWarnings("serial")
public class InvalidConfigurationException extends Exception {
	public InvalidConfigurationException(String propertyName) {
		super(ExceptionMessage.PROPERTY_NOT_CONFIGURED.getContent(propertyName));
	}
}
