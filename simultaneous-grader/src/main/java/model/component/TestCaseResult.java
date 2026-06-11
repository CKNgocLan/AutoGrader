package model.component;

import common.constant.TestingResult;

public record TestCaseResult(String testName, int maxPoints, int earnedPoints, boolean passed, String feedback) {
	public String passedFailed() {
		return passed ? TestingResult.PASSED : TestingResult.FAILED;
	}
}
