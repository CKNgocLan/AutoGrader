package student.constant;
import java.text.MessageFormat;

public enum TestcaseType {
	CHECK_CLASS_EXISTENCE("Check Class Existence: {0}")
	, CHECK_INTERFACE_EXISTENCE("Check INTERFACE Existence: {0}")
	, CHECK_ENUM_EXISTENCE("Check ENUM Existence: {0}")
	, CHECK_ABSTRACT_CLASS_DECLARATION("Check Class Declared as Abstract: {0}")
	, CHECK_SUPERCLASS_EXTENDED_BY_CLASS("Check Super Class \"{1}\" EXTENDED by Class \"{0}\"")
	, CHECK_INTERFACE_IMPLEMENTED_BY_CLASS("Check INTERFACE \"{1}\" Implemented by Class \"{0}\"")
	, CHECK_DECLARATION_OF_CONSTRUCTOR_NO_ARGS("Check No-Argument Constructor DECLARATION of Class: {0}")
	, CHECK_DECLARATION_OF_CONSTRUCTOR_FULL_ARGS("Check Full-Argument Constructor DECLARATION of Class: {0}")
	, CHECK_DECLARATION_OF_CONSTRUCTOR_PARTIAL_ARGS("Check Partial-Argument Constructor DECLARATION of Class: {0}")
	, CHECK_OPERATION_OF_CONSTRUCTOR_NO_ARGS("Check No-Argument Constructor OPERATION of Class: {0}")
	, CHECK_OPERATION_OF_CONSTRUCTOR_FULL_ARGS("Check Full-Argument Constructor OPERATION of Class: {0}")
	, CHECK_OPERATION_OF_CONSTRUCTOR_PARTIAL_ARGS("Check Partial-Argument Constructor OPERATION of Class: {0}")
	, CHECK_CLASS_ATTRIBUTE("Check Attribute Declaration of Class: {0}")
	, CHECK_CLASS_GETTER_DECLARATION("Check Getter Method Declaration of Class: {0}")
	, CHECK_CLASS_SETTER_DECLARATION("Check Setter Method Declaration of Class: {0}")
	, CHECK_CLASS_GETTER_OPERATION("Check GETTER Operation of Class: {0}")
	, CHECK_CLASS_SETTER_OPERATION("Check SETTER Operation of Class: {0}")
	, CHECK_CLASS_GETTER_OPERATION_FOR_FIELD("Check GETTER Operation of Class: {0} for field {1}")
	, CHECK_CLASS_SETTER_OPERATION_FOR_FIELD("Check SETTER Operation of Class: {0} for field {1}")
	, CHECK_CLASS_SPECIFIC_GETTER_OPERATION("Check \"{1}\" Getter Operation of Class: {0}")
	, CHECK_OPERATION_OF_CONSTRUCTOR_HAVING_SUPERCLASS("Check Constructor \"{0}\" OPERATION including SUPERCLASS of Class: {1}")
	, CHECK_CLASS_EXCLUDING_METHOD("Check {0} Class Excluding Method: {1}")
	
	, CHECK_FIELD("Check Field(s) of Class {0}: {1}")
	, CHECK_NO_FIELD("Check If No Field Declared in Class {0}")

	, CHECK_METHOD_EXISTENCE("Check Declaration of method {1}() in Class {0}")
	, CHECK_METHOD_OPERATION("Check Operation of method {1}() in Class {0}")
	
	, ERROR_TESTCASE("Failed Testcase while checking Class {0}.")
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
