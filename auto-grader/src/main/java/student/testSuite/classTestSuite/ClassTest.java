package student.testSuite.classTestSuite;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import student.checker.FieldChecker;
import student.constant.Constants;
import student.constant.Feedback;
import student.constant.TestcaseType;
import student.model.ITestCase;
import student.model.ParameterTest;
import student.util.ClassUtils;
import student.util.GetterUtils;
import student.util.SetterUtils;
import sun.nio.ch.Streams;

public class ClassTest {
	private static ClassTest instance;
	private FieldChecker fieldChecker = FieldChecker.getInstance();

	/*
	 * ***************************************************************************
	 */
	
	public static ClassTest getInstance() {
		if (instance == null) {
			instance = new ClassTest();
		}
		
		return instance;
	}

	/*
	 * ***************************************************************************
	 */
	
	/**
	 * Class existence testcase
	 * 
	 * @param className
	 * @return
	 */
	public ITestCase checkExistence(String className) {
		return checkExistence(className, 1);
	}

	/**
	 * Class existence testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkExistence(String className, int points) {
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
	 * No-argument constructor DECLARATION testcase
	 * 
	 * @param className
	 * @return
	 */
	public ITestCase checkNoArgConstructorDeclaration(String className) {
		return checkNoArgConstructorDeclaration(className, 1);
	}

	/**
	 * No-argument constructor DECLARATION testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkNoArgConstructorDeclaration(String className, int points) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_DECLARATION_OF_CONSTRUCTOR_NO_ARGS.getName(className);
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
				return Feedback.NO_ARGS_CONSTRUCTOR_DECLARATION_MISSING.getContent(className);
			}
		};
	}

	/**
	 * No-argument constructor OPERATION testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkNoArgConstructorOperation(String className, int points, ParameterTest testField) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_OPERATION_OF_CONSTRUCTOR_NO_ARGS.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className);

					Object castValue = (testField.getType().isPrimitive() ? ClassUtils.boxing(testField.getType()) : testField.getType())
							.cast(clazz.getDeclaredMethod(GetterUtils.getGetterName(testField.getName()))
									.invoke(clazz.getDeclaredConstructor().newInstance()));
					return castValue != null ? castValue.equals(testField.getTestValue()) : testField.getTestValue() == null;
				} catch (Exception e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.NO_ARGS_CONSTRUCTOR_OPERATION_NOT_CORRECT.getContent(className);
			}
		};
	}

	/**
	 * Full-argument constructor testcase
	 * 
	 * @param className
	 * @return
	 */
	public ITestCase checkFullArgsConstructorDeclaration(String className) {
		return checkFullArgsConstructorDeclaration(className, 1);
	}

	/**
	 * Full-argument constructor DECLARATION testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
//	public ITestCase checkFullArgsConstructorDeclaration(String className, int points, Class<?>... parameterTypes) {
	public ITestCase checkFullArgsConstructorDeclaration(String className, int points, ParameterTest... parameters) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_DECLARATION_OF_CONSTRUCTOR_FULL_ARGS.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class.forName(className).getDeclaredConstructor(
							Stream.of(parameters).map(p -> p.getType()).toArray(Class<?>[]::new));
					return true;
				} catch (Exception e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.FULL_ARGS_CONSTRUCTOR_DECLARATION_MISSING.getContent(className);
			}
		};
	}

	/**
	 * Full-argument constructor OPERATION testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkFullArgsConstructorOperation(String className, int points, ParameterTest... parameterTest) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_OPERATION_OF_CONSTRUCTOR_FULL_ARGS.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className);

					Class<?>[] types = Stream.of(parameterTest).map(pt -> pt.getType()).toArray(Class<?>[]::new);
					Object[] testValues = Stream.of(parameterTest).map(pt -> pt.getTestValue()).toArray(Object[]::new);
					clazz.getDeclaredConstructor(types).newInstance(testValues);

					for (ParameterTest pt : parameterTest) {
						Object castValue = (pt.getType().isPrimitive() ? ClassUtils.boxing(pt.getType()) : pt.getType())
								.cast(clazz.getDeclaredMethod(GetterUtils.getGetterName(pt.getName()))
										.invoke(clazz.getDeclaredConstructor(types).newInstance(testValues)));
						if (castValue != null ? !castValue.equals(pt.getTestValue()) : pt.getTestValue() != null) {
							return false;
						}
					}

					return true;
				} catch (Exception e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.FULL_ARGS_CONSTRUCTOR_OPERATION_NOT_CORRECT.getContent(className);
			}
		};
	}

	/**
	 * Partial-argument constructor testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkPartialArgsConstructorDeclaration(String className, int points, Class<?>... parameterTypes) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_DECLARATION_OF_CONSTRUCTOR_PARTIAL_ARGS.getName(className);
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
				return Feedback.PARTIAL_ARGS_CONSTRUCTOR_OPERATION_NOT_CORRECT.getContent(className, String.join(Constants.COMMA_WITH_SPACE,
						List.of(parameterTypes).stream().map(type -> type.getName()).toList()));
			}
		};
	}
	
	/**
	 * Attribute declaration testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkAttributes(String className, int points) {
		return new ITestCase() {
			String invalidAttrName = null;
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
				try {
					for(Field field : Class.forName(className).getDeclaredFields()) {
						if (!fieldChecker.checkField(field)) {
							invalidAttrName = field.getName();
							return false;
						}
					}

					return true;
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
				return Feedback.ATTRIBUTE_DECLARED_NOT_CORRECT.getContent(className, invalidAttrName);
			}
		};
	}
}
