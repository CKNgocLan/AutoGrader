package student.constant;
import java.text.MessageFormat;

public enum Feedback {
	CLASS_NOT_FOUND("Class '{0}' was not found. Make sure {0}.java is in the submission folder.")
	, NO_ARGS_CONSTRUCTOR_DECLARATION_MISSING("No-Argument constructor DECLARATION of {0}() is missing or does not initialize fields to default values.")
	, NO_ARGS_CONSTRUCTOR_OPERATION_NOT_CORRECT("No-Argument constructor OPERATION of {0}() is NOT CORRECT.")
	, FULL_ARGS_CONSTRUCTOR_DECLARATION_MISSING("Full-Argument constructor DECLARATION of {0}() is missing or does not set fields correctly.")
	, FULL_ARGS_CONSTRUCTOR_OPERATION_NOT_CORRECT("Full-Argument constructor DECLARATION of {0}() is NOT CORRECT.")
	, PARTIAL_ARGS_CONSTRUCTOR_DECLARATION_MISSING("Partial-Argument constructor DECLARATION of {0}({1}) is missing or does not set fields correctly.")
	, PARTIAL_ARGS_CONSTRUCTOR_OPERATION_NOT_CORRECT("Partial-Argument constructor DECLARATION of {0}({1}) is NOT CORRECT.")
	, ATTRIBUTE_DECLARED_NOT_CORRECT("Attributes of class {0} NOT CORRECT ({1}).")
	, GETTER_DECLARED_NOT_CORRECT("Getter(s) of class {0} NOT CORRECT or MISSING ({1}).")
	, SETTER_DECLARED_NOT_CORRECT("Setter(s) of class {0} NOT CORRECT or MISSING ({1}).")
	, GETTER_OPERATION_WORKING_NOT_PROPERLY("Getter of field {0} WORKING NOT PROPERLY. ({1})")
	, SETTER_OPERATION_WORKING_NOT_PROPERLY("Setter of field {0} WORKING NOT PROPERLY. ({1})")
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
