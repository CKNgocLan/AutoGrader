package common.util;

import common.constant.Feedback;
import common.constant.TestcaseType;
import model.ITestCase;

public class TestCaseUtils {
	public static ITestCase errorTestcase(int points, String className, Exception e) {
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
				return Feedback.FAILED_TESTCASE_DUE_TO_ERROR.getContent(e.getMessage());
			}
		};
	}

	public static ITestCase passByDefault(int points, String className) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.PASSED_TESTCASE.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				return true;
			}

			@Override
			public String getFeedback() {
				return Feedback.PASSED_TESTCASE_BUT_EXCEPTION.getContent(className);
			}
		};
	}
	
	public static ITestCase pass(int points, String className, TestcaseType testcaseType, Feedback feedback) {
		return new ITestCase() {
			@Override
			public String getName() {
				return testcaseType.getName(className);
			}
			
			@Override
			public int getPoints() {
				return points;
			}
			
			@Override
			public boolean runTest() {
				return true;
			}
			
			@Override
			public String getFeedback() {
				return feedback.getContent(className);
			}
		};
	}
	
	public static ITestCase pass(int points, String className, String type, String feedback) {
		return new ITestCase() {
			@Override
			public String getName() {
				return type;
			}
			
			@Override
			public int getPoints() {
				return points;
			}
			
			@Override
			public boolean runTest() {
				return true;
			}
			
			@Override
			public String getFeedback() {
				return feedback;
			}
		};
	}
	
	public static ITestCase fail(int points, String className, String type, String feedback) {
		return new ITestCase() {
			@Override
			public String getName() {
				return type;
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
				return feedback;
			}
		};
	}
	
	public static ITestCase fail(int points, String className, TestcaseType testcaseType, Feedback feedback) {
		return new ITestCase() {
			@Override
			public String getName() {
				return testcaseType.getName(className);
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
				return feedback.getContent(className);
			}
		};
	}
}
