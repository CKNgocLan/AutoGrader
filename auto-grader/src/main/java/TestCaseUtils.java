
public class TestCaseUtils {
	/**
	 * Class existence testcase
	 * @param className
	 * @return
	 */
	public static TestCase createClassExistsTest(String className) {
		return createClassExistsTest(className, 1);
	}
	
	/**
	 * Class existence testcase
	 * @param className
	 * @param points
	 * @return
	 */
	public static TestCase createClassExistsTest(String className, int points) {
		return new TestCase() {
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
	 * @param className
	 * @return
	 */
	public static TestCase createNoArgConstructorTest(String className) {
		return createNoArgConstructorTest(className, 1);
	}

	/**
	 * No-argument constructor testcase
	 * @param className
	 * @param points
	 * @return
	 */
	public static TestCase createNoArgConstructorTest(String className, int points) {
		return new TestCase() {
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
	 * @param className
	 * @return
	 */
	public static TestCase createFullConstructorTest(String className) {
		return createFullConstructorTest(className, 1);
	}

	/**
	 * Full-argument constructor testcase
	 * @param className
	 * @param points
	 * @return
	 */
	public static TestCase createFullConstructorTest(String className, int points, Class<?>... parameterTypes) {
		return new TestCase() {
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
					Class.forName(className)
							.getDeclaredConstructor(parameterTypes);
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
