package student.testcaseCreator;

import java.util.stream.Stream;

import student.checker.FieldChecker;
import student.constant.Constants;
import student.constant.Feedback;
import student.constant.TestcaseType;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.util.ClassUtils;

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

	public ITestCase checkDeclaration(int points, String className, String fieldName, Class<?> type) {
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
	
	public ITestCase checkDeclarations(int points, String className, FieldTesting... fields) {
		String fieldNames = String.join(Constants.COMMA_WITH_SPACE, Stream.of(fields).map(f -> f.getName()).toList());
		
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_FIELD.getName(className, fieldNames);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);
					
					if (fields.length > clazz.getDeclaredFields().length) {
						return false;
					}
					
					for (FieldTesting testingField : fields) {
						if (!fieldChecker.checkPublicStaticFinalField(clazz, clazz.getField(testingField.getName()))) {
							return false;
						}
					}
					return true;
					
				} catch (ClassNotFoundException | NoSuchFieldException | SecurityException e) {
					for (StackTraceElement st : e.getStackTrace()) {
						System.out.println(st);
					}

					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.FIELD_DECLARED_NOT_CORRECT.getContent(className, fieldNames);
			}
		};
	}
}
