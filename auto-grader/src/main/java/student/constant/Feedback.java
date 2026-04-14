package student.constant;
import java.text.MessageFormat;

public enum Feedback {
	CLASS_NOT_FOUND("Class '{0}' was not found. Make sure {0}.java is in the submission folder.")
	, CONSTRUCTOR_MISSING_NO_ARGS("No-argument constructor {0}() is missing or does not initialize fields to default values.")
	, CONSTRUCTOR_MISSING_FULL_ARGS("Full-argument constructor {0}() is missing or does not set fields correctly.")
	, ATTRIBUTE_DECLARED_NOT_CORRECT("Attributes of class {0} NOT CORRECT.")
	, GETTER_DECLARED_NOT_CORRECT("Getter method of class {0} NOT CORRECT.")
	, SETTER_DECLARED_NOT_CORRECT("Setter method of class {0} NOT CORRECT.")
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
