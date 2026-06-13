package common.message;

import java.text.MessageFormat;

public enum ExceptionMessage {
	PROPERTY_NOT_CONFIGURED("Properties Not Configured: {0}")
	, TESTER_GOT_NO_CLASS_NAME("{0}: NO VALID CLASS NAME")
	, INVALID_EMPLOYEE_NUMBER_FORMAT("Invalid employee number format.")
	, NO_SOLUTION_CLASS("No Solution Class Defined for Class {0}.")
	, INVALID_FIELD("INVALID FIELDS: {0}")
	, INVALID_DIRECTORY("Invalid Directory: {0}")
	, INVALID_CLASS_NAME("INVALID CLASS NAME INPUT: {0}")
	, IS_NULL_IN_CLASS("{0} IS NULL in \"{1}\" class")
	, PROPERTY_CANNOT_BE_NULL_OR_EMPTY("Property {0} of {1} cannot be null or empty.")
	, NOT_IMPLEMENTED("{0} is NOT IMPLEMENTED.")
	, CANNOT_CONSTRUCT_INSTANCES_DIRECTLY("Cannot construct {0} instances directly.")
	, EXCEL_REPORT_GENERATION_ERROR("Error generating Excel report: {0}")
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

	public String withMessage(Exception e) {
		return getContent(e.getMessage());
	}
}
