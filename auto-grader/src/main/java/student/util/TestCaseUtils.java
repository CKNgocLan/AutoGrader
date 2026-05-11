package student.util;

import student.constant.Feedback;
import student.constant.TestcaseType;
import student.model.ITestCase;

public class TestCaseUtils {
	public static ITestCase errorTestcase(int points, String className, String message) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_CLASS_ATTRIBUTE.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				return false;
			}

			@Override
			public String getFeedback() {
				return Feedback.FAILED_TESTCASE_DUE_TO_ERROR.getContent(message);
			}
		};
	}
}
