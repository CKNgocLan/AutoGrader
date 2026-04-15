package student.testSuite.classTestSuite;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import student.checker.GetterChecker;
import student.constant.Constants;
import student.constant.Feedback;
import student.constant.TestcaseType;
import student.model.Getter;
import student.model.ITestCase;
import student.model.InvalidMethod;
import student.model.Setter;
import student.util.TestCaseUtil;
import sun.nio.ch.Streams;

public class ClassTest {
	private static ClassTest instance;
	
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
	 * Partial-argument constructor testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkPartialArgsConstructor(String className, int points, Class<?>... parameterTypes) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_CONSTRUCTOR_PARTIAL_ARGS.getName(className);
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
				return Feedback.CONSTRUCTOR_MISSING_PARTIAL_ARGS.getContent(className, String.join(Constants.COMMA_WITH_SPACE,
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
						if (!TestCaseUtil.checkField(field)) {
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
					return TestCaseUtil.checkGetter(Class.forName(className), invalid);
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
	 * Setter method declaration testcase
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
					return TestCaseUtil.checkSetter(Class.forName(className), invalid);
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
	 * Getter method logic testcase
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
					if (!TestCaseUtil.checkGetter(clazz, invalidGetter)) {
						return false;
					}
					
//					TODO if (!GetterChecker.check()) {
//						return false;
//					}
					
					boolean pass = false;
					for (Field field : clazz.getDeclaredFields()) {
						if (String.class.equals(field.getType())) {
							pass = GetterChecker.checkStringGetter(clazz, field.getName(), "Michale Jackson");
						} else if (int.class.equals(field.getType())) {
							pass = GetterChecker.checkIntGetter(clazz, field.getName(), 5678);
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
}
