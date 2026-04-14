package student.testSuite.classTestSuite;

import student.constant.Feedback;
import student.constant.TestcaseType;
import student.model.ITestCase;

public class ClassTest {
	/**
	 * Class existence testcase
	 * 
	 * @param className
	 * @return
	 */
	public static ITestCase checkExistence(String className) {
		return checkExistence(className, 1);
	}

	/**
	 * Class existence testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public static ITestCase checkExistence(String className, int points) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_CLASS_EXISTENCE.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class.forName(className);
					return true;
				} catch (ClassNotFoundException e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.CLASS_NOT_FOUND.getContent(className);
			}
		};
	}

	/**
	 * No-argument constructor testcase
	 * 
	 * @param className
	 * @return
	 */
	public static ITestCase checkNoArgConstructor(String className) {
		return checkNoArgConstructor(className, 1);
	}

	/**
	 * No-argument constructor testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public static ITestCase checkNoArgConstructor(String className, int points) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_CONSTRUCTOR_NO_ARGS.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class.forName(className).getDeclaredConstructor().newInstance();
					return true;
				} catch (Exception e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.CONSTRUCTOR_MISSING_NO_ARGS.getContent(className);
			}
		};
	}

	/**
	 * Full-argument constructor testcase
	 * 
	 * @param className
	 * @return
	 */
	public static ITestCase checkFullArgsConstructor(String className) {
		return checkFullArgsConstructor(className, 1);
	}

	/**
	 * Full-argument constructor testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public static ITestCase checkFullArgsConstructor(String className, int points, Class<?>... parameterTypes) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_CONSTRUCTOR_FULL_ARGS.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class.forName(className).getDeclaredConstructor(parameterTypes);
					return true;
				} catch (Exception e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.CONSTRUCTOR_MISSING_FULL_ARGS.getContent(className);
			}
		};
	}
}
