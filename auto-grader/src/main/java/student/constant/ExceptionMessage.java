package student.constant;

import java.text.MessageFormat;

public enum ExceptionMessage {
	PROPERTY_NOT_CONFIGURED("Properties Not Configured: {0}")
	;
	
	private final String value;
	
	private ExceptionMessage(String label) {
        this.value = label;
    }
	
	public String getValue() {
		return this.value;
	}
	
	public String getContent(Object... args) {
		return MessageFormat.format(this.value, args);
	}
}
