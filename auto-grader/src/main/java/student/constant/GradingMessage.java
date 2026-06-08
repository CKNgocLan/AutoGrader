package student.constant;

import java.text.MessageFormat;

public enum GradingMessage {
	NO_JAVA_FILE_FOUND_IN_FOLDER("No .java files found in the folder: {0}!")
	, PLEASE_SELECT_LAB("Please select a Lab!")
	, PLEASE_SELECT_QUESTION("Please select a Question!")
	, FOLDER_CONTAINING_JAVA_FILE_NOT_FOUND("Folder not found!\nPlease select a valid folder containing your .java files.")
	, STARTING_GRADING_FOR_FOLDER("Starting Grading for folder: {0}")
	, RUNNING_TEST_CASES_NEWLINE("Running Test Cases...\n")
	, UPPERCASE_ATTENTION("ATTENTION")
	, SUCCESS("Success")
	, WARNING("Warning")
	, ERROR("Error")
	, UNEXPECTED_ERROR_WITH_MESSAGE("Unexpected error: {0}")
	, ERROR_CHECK_TERMINAL("ERROR! CHECK TERMINAL FOR ERROR STACK TRACE!")
	, TEST_SUITE_UNDER_CONSTRUCTION("TEST SUITE IS UNDER CONSTRUCTION!\n(Lab {0} - Problem {1})")
	, COMPILING_JAVA_FILES_NEWLINE("Compiling Java files...\n")
	, COMPILATION_ERRORS_WITH_MESSAGE("Compilation Errors: {0}")
	, COMPILATION_FAILED("Compilation failed. Please check your code for errors.")
	, COMPILATION_SUCCESSFUL_NEWLINE("Compilation successful!\n")
	, EXCELLENT_ALL_TESTS_PASSED("Excellent! All tests passed.")
	, GOOD_WORK_REVIEW_FAILED_TESTS("Good work! Review the failed tests below.")
	, DETAILED_REPORT_SAVED_IN_FOLDER("Detailed report saved in {0} folder.")
	, PASSED_TESTCASE_RATE("Passed Testcase(s) Rate: : {0}/{1}")
	, PERCENTAGE("Percentage: {0}")
	, GRADING_COMPLETED_WITH_PASSED_TESTCASE("Grading Completed!\nYou have passed {0}/{1} testcase.")
	;

	
	private final String value;
	
	private GradingMessage(String label) {
        this.value = label;
    }
	
	public String getValue() {
		return this.value;
	}
	
	public String getContent(Object... args) {
		return MessageFormat.format(this.value, args);
	}
}
