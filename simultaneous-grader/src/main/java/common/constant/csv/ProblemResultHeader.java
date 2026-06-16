package common.constant.csv;

public class ProblemResultHeader {
	private static final String[] header = new String[] {
			"Test Case"
			, "Max Points"
			, "Earned Points"
			, "Status"
			, "Feedback"
	};
	public static final String[] getHeaders() {
		return header;
	}
}
