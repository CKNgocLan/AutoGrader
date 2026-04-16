package student.testSuite.classTestSuite;

import java.lang.reflect.Field;
import java.util.List;

import student.checker.FieldChecker;
import student.constant.Constants;
import student.constant.Feedback;
import student.constant.TestcaseType;
import student.model.ITestCase;
import student.util.ClassUtils;
import student.util.GetterUtils;
import student.util.SetterUtils;

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
	public ITestCase checkNoArgConstructorOperation(String className, int points, String fieldName, Class<?> fieldType,
			Object defaultValue) {
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

					Object castValue = (fieldType.isPrimitive() ? ClassUtils.boxing(fieldType) : fieldType)
							.cast(clazz.getDeclaredMethod(GetterUtils.getGetterName(fieldName))
									.invoke(clazz.getDeclaredConstructor().newInstance()));
					return castValue != null ? castValue.equals(defaultValue) : defaultValue == null;
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
	 * Full-argument constructor testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkFullArgsConstructorDeclaration(String className, int points, Class<?>... parameterTypes) {
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
					Class.forName(className).getDeclaredConstructor(parameterTypes);
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
