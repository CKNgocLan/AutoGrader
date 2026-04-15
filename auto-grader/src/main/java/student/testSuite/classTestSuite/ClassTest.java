package student.testSuite.classTestSuite;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import student.checker.GetterChecker;
import student.constant.Constants;
import student.constant.Feedback;
import student.constant.TestcaseType;
import student.model.InvalidMethod;
import student.model.ITestCase;
import student.util.TestCaseUtil;

public class ClassTest {
	private static ClassTest instance;
//	private TestCaseUtil testCaseUtil = TestCaseUtil.getInstance();
	
	public static ClassTest getInstance() {
		if (instance == null) {
			instance = new ClassTest();
		}
		
		return instance;
	}
	
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
	 * No-argument constructor testcase
	 * 
	 * @param className
	 * @return
	 */
	public ITestCase checkNoArgConstructor(String className) {
		return checkNoArgConstructor(className, 1);
	}

	/**
	 * No-argument constructor testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkNoArgConstructor(String className, int points) {
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
	public ITestCase checkFullArgsConstructor(String className) {
		return checkFullArgsConstructor(className, 1);
	}

	/**
	 * Full-argument constructor testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkFullArgsConstructor(String className, int points, Class<?>... parameterTypes) {
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
						if (!TestCaseUtil.checkField(field)
							|| (Modifier.isStatic(field.getModifiers()) && !TestCaseUtil.checkPrivateStaticField(field))) {
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
	
	/**
	 * Getter method declaration testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkGetterDeclaration(String className, int points) {
		return new ITestCase() {
			List<InvalidMethod> invalid = List.of();
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
					return TestCaseUtil.checkGetter(Class.forName(className), invalid);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.GETTER_DECLARED_NOT_CORRECT.getContent(className,
						String.join(Constants.COMMA, invalid.stream().map(InvalidMethod::getName).collect(Collectors.toList())));
			}
		};
	}
	
	/**
	 * Setter method declaration testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkSetterDeclaration(String className, int points) {
		return new ITestCase() {
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
					return TestCaseUtil.checkSetter(Class.forName(className));
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.SETTER_DECLARED_NOT_CORRECT.getContent(className);
			}
		};
	}
	
	/**
	 * Getter method logic testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkGetterOperation(String className, int points) {
		return new ITestCase() {
			List<InvalidMethod> invalid = List.of();
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
					if (!TestCaseUtil.checkGetter(clazz, invalid)) {
						return false;
					}
					
					if (!GetterChecker.check()) {
						return false;
					}
					
					if (!GetterChecker.checkStringGetter(clazz, "name", "Michale Jackson")) {
						return false;
					}
					
					return GetterChecker.checkIntGetter(clazz, "idNumber", 5678);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.GETTER_OPERATION_WORKING_NOT_PROPERLY.getContent(className,
						String.join(Constants.COMMA, invalid.stream().map(InvalidMethod::getName).collect(Collectors.toList())));
			}
		};
	}
}
