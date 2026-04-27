package student.testSuite;

import java.util.ArrayList;
import java.util.List;

import student.constant.Constants;
import student.constant.Feedback;
import student.constant.TestcaseType;
import student.model.FieldTesting;
import student.model.Getter;
import student.model.ITestCase;
import student.model.Method;
import student.util.FieldUtils;

public class FieldTester {
	private static FieldTester instance = null;
	private ClassLoader targetClassesLoader = student.model.ClassLoader.getInstance();
	private String className;

	/*
	 * ***************************************************************************
	 */

	public static FieldTester getInstance() {
		if (instance == null) {
			instance = new FieldTester();
		}

		return instance;
	}
	
	public void setClassName(String className) {
		this.className = className;
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
					return FieldUtils.checkFieldDeclaration(
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
