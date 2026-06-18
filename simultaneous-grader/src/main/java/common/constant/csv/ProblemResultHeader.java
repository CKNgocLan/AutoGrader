package common.constant.csv;

public class ProblemResultHeader {
	private static final String[] csvHeader = new String[] {
			"Test Case"
			, "Max Points"
			, "Earned Points"
			, "Status"
			, "Feedback"
	};

	private static final String[] excelHeader = new String[] {
			"No."
			, "Test Case Name"
			, "Result"
			, "Feedback"
	};

	public static final String[] getExcelHeader() {
		return excelHeader;
	}
	
	public static final String[] getCSVHeaders() {
		return csvHeader;
	}
}
