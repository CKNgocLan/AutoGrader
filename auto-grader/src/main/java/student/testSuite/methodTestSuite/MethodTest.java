package student.testSuite.methodTestSuite;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import student.checker.MethodChecker;
import student.constant.Constants;
import student.constant.Feedback;
import student.constant.TestcaseType;
import student.model.Getter;
import student.model.ITestCase;
import student.model.InvalidMethod;
import student.model.Setter;

/**
 * Test suite for the Employee class. Tests constructors, getters, and setters
 * as per the uploaded Employee.java
 */
public class MethodTest {
	private static MethodTest instance = null;
	private MethodChecker methodChecker = MethodChecker.getInstance();
//	private GetterChecker getterChecker = GetterChecker.getInstance();
//	private SetterChecker setterChecker = SetterChecker.getInstance();

	/*
	 * ***************************************************************************
	 */

	public static MethodTest getInstance() {
		if (instance == null) {
			instance = new MethodTest();
		}

		return instance;
	}

	/*
	 * ***************************************************************************
	 */

	/**
	 * Getter declaration testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkGetterDeclaration(String className, int points) {
		return new ITestCase() {
			List<Getter> invalid = new ArrayList<Getter>();

			@Override
			public String getName() {
				return TestcaseType.CHECK_CLASS_GETTER_DECLARATION.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					return methodChecker.checkMissingGetter(Class.forName(className), invalid);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.GETTER_DECLARED_NOT_CORRECT.getContent(className,
						String.join(Constants.COMMA, invalid.stream().map(InvalidMethod::getName).toList()));
			}
		};
	}

	/**
	 * Setter declaration testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkSetterDeclaration(String className, int points) {
		return new ITestCase() {
			List<Setter> invalid = new ArrayList<Setter>();

			@Override
			public String getName() {
				return TestcaseType.CHECK_CLASS_SETTER_DECLARATION.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					return methodChecker.checkMissingSetter(Class.forName(className), invalid);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.SETTER_DECLARED_NOT_CORRECT.getContent(className,
						String.join(Constants.COMMA, invalid.stream().map(InvalidMethod::getName).toList()));
			}
		};
	}

	/**
	 * Getter/Setter operation testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkGetterSetterOperation(String className, int points) {
		return new ITestCase() {
			List<Getter> invalidGetter = new ArrayList<Getter>();
			String invalidField = "";

			@Override
			public String getName() {
				return TestcaseType.CHECK_CLASS_GETTER_OPERATION.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className);
					if (!methodChecker.checkMissingGetter(clazz, invalidGetter)
							|| !methodChecker.checkMissingSetter(clazz, null)) {
						return false;
					}

					boolean pass = false;
					for (Field field : clazz.getDeclaredFields()) {
						if (String.class.equals(field.getType())) {
							pass = methodChecker.checkStringGetset(clazz, field.getName(), "Michale Jackson");
						} else if (int.class.equals(field.getType())) {
							pass = methodChecker.checkIntGetset(clazz, field.getName(), 5678);
						}

						if (!pass) {
							invalidField = field.getName();
							return false;
						}
					}

					return true;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return invalidGetter.size() > 0
						? Feedback.GETTER_DECLARED_NOT_CORRECT.getContent(className,
								String.join(Constants.COMMA,
										invalidGetter.stream().map(InvalidMethod::getName).toList()))
						: Feedback.GETTER_SETTER_OPERATION_WORKING_NOT_PROPERLY.getContent(invalidField);
			}
		};
	}

	/**
	 * String Getter/Setter operation testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkStringGetsetOperation(String className, int points, String fieldName, String testValue) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_CLASS_SPECIFIC_GETTER_OPERATION.getName(className, fieldName);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					return methodChecker.checkStringGetset(Class.forName(className), fieldName, testValue);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.GETTER_SETTER_OPERATION_WORKING_NOT_PROPERLY.getContent(className, fieldName);
			}
		};
	}

	/**
	 * int Getter/Setter operation testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkIntGetsetOperation(String className, int points, String fieldName, int testValue) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_CLASS_SPECIFIC_GETTER_OPERATION.getName(className, fieldName);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					return methodChecker.checkIntGetset(Class.forName(className), fieldName, testValue);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.GETTER_SETTER_OPERATION_WORKING_NOT_PROPERLY.getContent(className, fieldName);
			}
		};
	}
}