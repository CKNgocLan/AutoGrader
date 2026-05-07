package student.testcaseCreator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import student.checker.MethodChecker;
import student.constant.Constants;
import student.constant.Feedback;
import student.constant.TestcaseType;
import student.model.Getter;
import student.model.ITestCase;
import student.model.MethodTesting;
import student.model.Setter;

/**
 * Test suite for the Employee class. Tests constructors, getters, and setters
 * as per the uploaded Employee.java
 */
public class MethodTestcaseCreator {
	private static MethodTestcaseCreator instance = null;
	private ClassLoader targetClassesLoader = student.model.ClassLoader.getInstance();
	private MethodChecker methodChecker = MethodChecker.getInstance();

	/*
	 * ***************************************************************************
	 */

	public static MethodTestcaseCreator getInstance() {
		if (instance == null) {
			instance = new MethodTestcaseCreator();
		}

		return instance;
	}
	
	public MethodChecker getMethodChecker() {
		return this.methodChecker;
	}

	/*
	 * ***************************************************************************
	 */

	public ITestCase checkGetterDeclaration(int points, String className) {
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
					return methodChecker.checkMissingGetter(Class.forName(className, true, targetClassesLoader),
							invalid);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.GETTER_DECLARED_NOT_CORRECT.getContent(className,
						String.join(Constants.COMMA, invalid.stream().map(MethodTesting::getName).toList()));
			}
		};
	}

	/*
	 * ***************************************************************************
	 */

	public ITestCase checkSetterDeclaration(int points, String className) {
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
					return methodChecker.checkMissingSetter(Class.forName(className, true, targetClassesLoader),
							invalid);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.SETTER_DECLARED_NOT_CORRECT.getContent(className,
						String.join(Constants.COMMA, invalid.stream().map(MethodTesting::getName).toList()));
			}
		};
	}

	/*
	 * ***************************************************************************
	 */

	public ITestCase checkGetterSetterOperation(int points, String className) {
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
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);
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
								String.join(Constants.COMMA, invalidGetter.stream().map(MethodTesting::getName).toList()))
						: Feedback.GETTER_SETTER_OPERATION_WORKING_NOT_PROPERLY.getContent(invalidField);
			}
		};
	}

	/*
	 * ***************************************************************************
	 */

	public ITestCase checkStringGetsetOperation(int points, String className, String fieldName, String testValue) {
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
					return methodChecker.checkStringGetset(Class.forName(className, true, targetClassesLoader),
							fieldName, testValue);
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

	/*
	 * ***************************************************************************
	 */

	public ITestCase checkIntGetsetOperation(int points, String className, String fieldName, int testValue) {
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
					return methodChecker.checkIntGetset(Class.forName(className, true, targetClassesLoader), fieldName,
							testValue);
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

	/*
	 * existence ***************
	 */

	public ITestCase checkExistence(int points, String className, MethodTesting method) {
		return new ITestCase() {

			@Override
			public String getName() {
				return TestcaseType.CHECK_METHOD_EXISTENCE.getName(className, method.getName());
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					if (method.isStatic()) {
						return methodChecker.isMethodDeclaredAsStatic(Class.forName(className, true, targetClassesLoader), method);
					}
					
					return methodChecker.isMethodDeclared(Class.forName(className, true, targetClassesLoader), method);
				} catch (NoSuchMethodException | IllegalArgumentException e) {
					System.out.println("NoSuchMethodException | IllegalArgumentException e");
					System.out.println(e.getMessage());
					return false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				} finally {
					
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.METHOD_DECLARED_NOT_CORRECT.getContent(className, method.getName());
			}
		};
	}

	/*
	 * operate ***************
	 */

	public ITestCase checkOperationAsNumberic(int points, MethodTesting method) {
		return new ITestCase() {

			@Override
			public String getName() {
				return TestcaseType.CHECK_METHOD_OPERATION.getName(method.getCorrespondingClassName(), method.getName());
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Double abs = method.returnNumbericAbs();
					return Double.isNaN(abs) || abs < Constants.ERROR_BOUND;
				} catch (NoSuchMethodException e) {
					return false;
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
					return false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(method.getCorrespondingClassName(), method.getName());
			}
		};
	}
}