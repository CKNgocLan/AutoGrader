package student.testSuite.classTestSuite;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import student.checker.FieldChecker;
import student.constant.Constants;
import student.constant.Feedback;
import student.constant.TestcaseType;
import student.model.ITestCase;
import student.model.ParameterTest;
import student.util.ParameterTestUtils;

public class ClassTest {
	private static ClassTest instance;
	private FieldChecker fieldChecker = FieldChecker.getInstance();
	private ClassLoader targetClassesLoader = student.model.ClassLoader.getInstance();

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
//					Class.forName(className, true, targetClassesLoader);
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
					Class.forName(className, true, targetClassesLoader).getDeclaredConstructor().newInstance();
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
	public ITestCase checkNoArgConstructorOperation(String className, int points, ParameterTest params) {
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
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);

					return ParameterTestUtils.compareTestValue(clazz, clazz.getDeclaredConstructor().newInstance(),
							params);
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
	public ITestCase checkFullArgsConstructorDeclaration(String className, int points, ParameterTest... params) {
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
					Class.forName(className, true, targetClassesLoader)
							.getDeclaredConstructor(Stream.of(params).map(p -> p.getType()).toArray(Class<?>[]::new));
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
	public ITestCase checkFullArgsConstructorOperation(String className, int points, ParameterTest... params) {
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
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);

					Class<?>[] types = Stream.of(params).map(pt -> pt.getType()).toArray(Class<?>[]::new);
					Object[] testValues = Stream.of(params).map(pt -> pt.getTestValue()).toArray(Object[]::new);
					Object instance = clazz.getDeclaredConstructor(types).newInstance(testValues);

					for (ParameterTest param : params) {
						if (!ParameterTestUtils.compareTestValue(clazz, instance, param)) {
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
	 * Partial-argument constructor DECLARATION testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkPartialArgsConstructorDeclaration(String className, int points, ParameterTest... params) {
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
					Class.forName(className, true, targetClassesLoader)
							.getDeclaredConstructor(Stream.of(params).map(p -> p.getType()).toArray(Class<?>[]::new));
					return true;
				} catch (Exception e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.PARTIAL_ARGS_CONSTRUCTOR_DECLARATION_MISSING.getContent(className, String
						.join(Constants.COMMA_WITH_SPACE, Stream.of(params).map(param -> param.getName()).toList()));
			}
		};
	}

	/**
	 * Partial-argument constructor OPERATION testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkPartialArgsConstructorOperation(String className, int points, ParameterTest... params) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_OPERATION_OF_CONSTRUCTOR_PARTIAL_ARGS.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);

					Class<?>[] types = Stream.of(params).map(pt -> pt.getType()).toArray(Class<?>[]::new);
					Object[] testValues = Stream.of(params).map(pt -> pt.getTestValue()).toArray(Object[]::new);
					Object instance = clazz.getDeclaredConstructor(types).newInstance(testValues);

					for (ParameterTest param : params) {
						if (!ParameterTestUtils.compareTestValue(clazz, instance, param)) {
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
				return Feedback.PARTIAL_ARGS_CONSTRUCTOR_OPERATION_NOT_CORRECT.getContent(className, String
						.join(Constants.COMMA_WITH_SPACE, Stream.of(params).map(param -> param.getName()).toList()));
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
					for (Field field : Class.forName(className, true, targetClassesLoader).getDeclaredFields()) {
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
