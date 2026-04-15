package student.constant;
import java.text.MessageFormat;

public enum TestcaseType {
	CHECK_CLASS_EXISTENCE("Check Class Existence: {0}")
	, CHECK_CONSTRUCTOR_NO_ARGS("Check No-Argument Constructor of Class: {0}")
	, CHECK_CONSTRUCTOR_FULL_ARGS("Check Full-Argument Constructor of Class: {0}")
	, CHECK_CONSTRUCTOR_PARTIAL_ARGS("Check Partial-Argument Constructor of Class: {0}")
	, CHECK_CLASS_ATTRIBUTE("Check Attribute Declaration of Class: {0}")
	, CHECK_CLASS_GETTER_DECLARATION("Check Getter Method Declaration of Class: {0}")
	, CHECK_CLASS_SETTER_DECLARATION("Check Setter Method Declaration of Class: {0}")
	, CHECK_CLASS_GETTER_OPERATION("Check GETTER Operation of Class: {0}")
	, CHECK_CLASS_SETTER_OPERATION("Check SETTER Operation of Class: {0}")
	, CHECK_CLASS_GETTER_OPERATION_FOR_FIELD("Check GETTER Operation of Class: {0} for field {1}")
	, CHECK_CLASS_SETTER_OPERATION_FOR_FIELD("Check SETTER Operation of Class: {0} for field {1}")
	;
	
	private final String value;
	
	private TestcaseType(String label) {
        this.value = label;
    }
	
	public String getValue() {
		return this.value;
	}
	
	public String getName(Object... args) {
		return MessageFormat.format(this.value, args);
	}
}
