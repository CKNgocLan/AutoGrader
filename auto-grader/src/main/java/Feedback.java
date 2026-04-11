import java.text.MessageFormat;

public enum Feedback {
	CLASS_NOT_FOUND("Class '{0}' was not found. Make sure {0}.java is in the submission folder.")
	;
	
	private final String value;
	
	private Feedback(String label) {
        this.value = label;
    }
	
	public String getValue() {
		return this.value;
	}
	
	public String getContent(Object... args) {
		return MessageFormat.format(this.value, args);
	}
}
