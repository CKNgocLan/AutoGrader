package student.consts;
import java.text.MessageFormat;

public enum TestcaseType {
	CHECK_CLASS_EXISTENCE("Check Class Existence: {0}")
	, CHECK_CONSTRUCTOR_NO_ARGS("Check No-Args Constructor of Class: {0}")
	, CHECK_CONSTRUCTOR_FULL_ARGS("Check Full-Args Constructor of Class: {0}")
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
