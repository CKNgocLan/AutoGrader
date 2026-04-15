package student.constant;
import java.text.MessageFormat;

public enum Feedback {
	CLASS_NOT_FOUND("Class '{0}' was not found. Make sure {0}.java is in the submission folder.")
	, CONSTRUCTOR_MISSING_NO_ARGS("No-Argument constructor {0}() is missing or does not initialize fields to default values.")
	, CONSTRUCTOR_MISSING_FULL_ARGS("Full-Argument constructor {0}() is missing or does not set fields correctly.")
	, CONSTRUCTOR_MISSING_PARTIAL_ARGS("Partial-Argument constructor {0}({1}) is missing or does not set fields correctly.")
	, ATTRIBUTE_DECLARED_NOT_CORRECT("Attributes of class {0} NOT CORRECT ({1}).")
	, GETTER_DECLARED_NOT_CORRECT("Getter(s) of class {0} NOT CORRECT or MISSING ({1}).")
	, SETTER_DECLARED_NOT_CORRECT("Setter(s) of class {0} NOT CORRECT or MISSING ({1}).")
	, GETTER_SETTER_OPERATION_WORKING_NOT_PROPERLY("Getter/Setter of class {0} WORKING NOT PROPERLY. ({1})")
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
