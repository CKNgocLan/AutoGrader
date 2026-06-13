package model.resultReport;

import common.constant.TestingResult;
import common.util.ReportUtils;

public record TestCaseResult(String testName, int maxPoints, int earnedPoints, boolean passed, String feedback) {
	public String passedFailed() {
		return passed ? TestingResult.PASSED : TestingResult.FAILED;
	}

	public Object[] toValueArray() {
		return new Object[] {testName, maxPoints, earnedPoints, passedFailed(), feedback};
	}

	public String toCSVRow() {
		return ReportUtils.convertToCsvRow(testName, maxPoints, earnedPoints, passedFailed(), feedback);
	}
}
