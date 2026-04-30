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
import student.model.Method;
import student.model.MethodTesting;
import student.model.Setter;
import student.util.MethodUtils;

/**
 * Test suite for the Employee class. Tests constructors, getters, and setters
 * as per the uploaded Employee.java
 */
public class MethodTestcaseCreator {
	private static MethodTestcaseCreator instance = null;
	private MethodChecker methodChecker = MethodChecker.getInstance();
	private ClassLoader targetClassesLoader = student.model.ClassLoader.getInstance();

	/*
	 * ***************************************************************************
	 */

	public static MethodTestcaseCreator getInstance() {
		if (instance == null) {
			instance = new MethodTestcaseCreator();
		}

		return instance;
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
						String.join(Constants.COMMA, invalid.stream().map(Method::getName).toList()));
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
						String.join(Constants.COMMA, invalid.stream().map(Method::getName).toList()));
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
								String.join(Constants.COMMA, invalidGetter.stream().map(Method::getName).toList()))
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
	 * ***************************************************************************
	 */

	public ITestCase checkExistence(int points, String className, Method method) {
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
					return MethodUtils.isMethodDeclared(Class.forName(className, true, targetClassesLoader), method);
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
				return Feedback.METHOD_DECLARED_NOT_CORRECT.getContent(className, method.getName());
			}
		};
	}

	/*
	 * ***************************************************************************
	 */

//	public ITestCase checkToString(int points, Class<?> clazz, Object instance) {
//		MethodTesting method = MethodUtils.createMethodToString();
//
//		return new ITestCase() {
//			@Override
//			public String getName() {
//				return TestcaseType.CHECK_METHOD_OPERATION.getName(clazz.getName(), method.getName());
//			}
//
//			@Override
//			public int getPoints() {
//				return points;
//			}
//
//			@Override
//			public boolean runTest() {
//				try {
//					method.setClazz(clazz);
//
//					// Prepare test data
//					String actual = method.invokeToString(instance);
//
//					// Get captured output then compare
//					return actual.contains(name) && actual.contains(address) && actual.contains(phoneNumber);
//				} catch (Exception e) {
//					System.out.println(e.getMessage());
//					return false;
//				}
//			}
//
//			@Override
//			public String getFeedback() {
//				return Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(clazz.getName(), method.getName());
//			}
//		};
//	}
}