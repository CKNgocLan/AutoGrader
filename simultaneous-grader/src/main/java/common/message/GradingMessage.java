package common.message;

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
	, FOLDER_COMPILATION_SUCCESSFUL("Compilation successful for Folder: {0}")
	, EXCELLENT_ALL_TESTS_PASSED("Excellent! All tests passed.")
	, GOOD_WORK_REVIEW_FAILED_TESTS("Good work! Review the failed tests below.")
	, DETAILED_REPORT_SAVED_IN_FOLDER("Detailed report saved in {0} folder.")
	, PASSED_TESTCASE_RATE("Passed Testcase(s) Rate: : {0}/{1}")
	, PERCENTAGE("Percentage: {0}")
	, GRADING_COMPLETED_WITH_PASSED_TESTCASE("Grading Completed!\nYou have passed {0}/{1} testcase.")

	, START_GRADING_PROBLEM_OF_STUDENT("Start grading \"{0}\" of \"{1}\"")
	, GENERATE_CSV_REPORT_SUCCESSFULLY("CSV report successfully created at:\n\t{0}\n\twith name: {1}")
	, WRITE_STUDENT_SUBMISSION_RESULT_TO_CSV("Write Submission for \"{0}\" of \"{1}\" to CSV.")
	, INVALID_OR_NOT_FOUND_SUBMISSION("Invalid or Not Found Submission")
	, INVALID_OR_NOT_FOUND_SUBMISSION_OF("Invalid or Not Found Submission: {0}")
	, NOT_FOUND_SUBMISSION("Not Found Submission")
	, NOT_FOUND_SUBMISSION_OF("Not Found Submission: {0}")
	, COMPILATION_ERROR("Compilation Error: {0}")
	, FINISH_GRADING_SUBMISSION_OF_STUDENT("Finish Grading Submission \"{0}\" of \"{1}\"")
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

	public void printErrorContent(Object... args) {
		System.err.println(getContent(args));
	}

	public void printContent(Object... args) {
		System.out.println(getContent(args));
	}
}
