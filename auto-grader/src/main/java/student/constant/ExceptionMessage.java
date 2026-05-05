package student.constant;

import java.text.MessageFormat;

public enum ExceptionMessage {
	PROPERTY_NOT_CONFIGURED("Properties Not Configured: {0}")
	, TESTER_GOT_NO_CLASS_NAME("{0}: NO VALID CLASS NAME")
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
