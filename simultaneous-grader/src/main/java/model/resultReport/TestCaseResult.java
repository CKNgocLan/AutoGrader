package model.resultReport;

import common.constant.Constants;
import common.constant.TestingResult;
import common.util.ReportUtils;

public record TestCaseResult(String testName, int maxPoints, int earnedPoints, Boolean passed, String feedback) {
	public String passedFailed() {
		if (passed == null) {
			return Constants.EMPTY_STRING;
		}

		return passed ? TestingResult.PASSED : TestingResult.FAILED;
	}

	public Object[] toValueArray() {
		return new Object[] { testName, maxPoints, earnedPoints, passedFailed(), feedback };
	}

	public String toCSVRow() {
		return ReportUtils.convertToCsvRow(testName, maxPoints, earnedPoints, passedFailed(), feedback);
	}
}
