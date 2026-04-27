package student.testcaseCreator;

import student.checker.FieldChecker;
import student.constant.Feedback;
import student.constant.TestcaseType;
import student.model.ITestCase;

public class FieldTestcaseCreator {
	private static FieldTestcaseCreator instance = null;
	private ClassLoader targetClassesLoader = student.model.ClassLoader.getInstance();
	private FieldChecker fieldChecker = FieldChecker.getInstance();

	/*
	 * ***************************************************************************
	 */

	public static FieldTestcaseCreator getInstance() {
		if (instance == null) {
			instance = new FieldTestcaseCreator();
		}

		return instance;
	}

	/*
	 * ***************************************************************************
	 */

	public ITestCase checkDeclaration(String className, int points, String fieldName, Class<?> type) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_FIELD.getName(className, fieldName);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					return fieldChecker.checkDeclaration(
							Class.forName(className, true, targetClassesLoader).getDeclaredField(fieldName),
							fieldName,
							type);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.FIELD_DECLARED_NOT_CORRECT.getContent(className, fieldName);
			}
		};
	}
}
