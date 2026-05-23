package student.testcaseCreator;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import student.checker.FieldChecker;
import student.constant.Constants;
import student.constant.Country;
import student.constant.Feedback;
import student.constant.FieldName;
import student.constant.TestcaseType;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.util.ParameterTestingUtils;

public class ClassTestcaseCreator {
	private static ClassTestcaseCreator instance;
	private FieldChecker fieldChecker = FieldChecker.getInstance();
	private ClassLoader targetClassesLoader = student.model.ClassLoader.getInstance();

	/*
	 * ***************************************************************************
	 */

	public static ClassTestcaseCreator getInstance() {
		if (instance == null) {
			instance = new ClassTestcaseCreator();
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
	 * @param points
	 * @return
	 */
	public ITestCase checkExistence(int points, String className) {
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
					Class.forName(className, true, targetClassesLoader);
					return true;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
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
	 * Class existence testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase declareAsInterface(int points, String className) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_INTERFACE_EXISTENCE.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					return Class.forName(className, true, targetClassesLoader).isInterface();
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
	
	public ITestCase declareAsEnum(int points, String className) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_ENUM_EXISTENCE.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					return Class.forName(className, true, targetClassesLoader).isEnum();
				} catch (ClassNotFoundException e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.ENUM_NOT_FOUND.getContent(className);
			}
		};
	}
	
	public ITestCase declareAsAbstract(int points, String className) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_ABSTRACT_CLASS_DECLARATION.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					return Modifier.isAbstract(Class.forName(className, true, targetClassesLoader).getModifiers());
				} catch (ClassNotFoundException e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.CLASS_NOT_ABSTRACT.getContent(className);
			}
		};
	}

	/**
	 * Class existence testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkExistenceAsEnum(int points, String className) {
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
					return Class.forName(className, true, targetClassesLoader).isEnum();
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
	
	public ITestCase checkImplementingInterface(int points, String className, String interfaceName) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_INTERFACE_IMPLEMENTED_BY_CLASS.getName(className, interfaceName);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					for (Class<?> clazz : Class.forName(className, true, targetClassesLoader).getInterfaces()) {
						if (clazz.getName().contains(interfaceName)) {
							return true;
						}
					}
					
					return false;
				} catch (ClassNotFoundException e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.CLASS_NOT_IMPLEMENTING_INTERFACE.getContent(className, interfaceName);
			}
		};
	}
	
	public ITestCase declareSuperclass(int points, String className, Class<?> superclass) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_SUPERCLASS_EXTENDED_BY_CLASS.getName(className, superclass.getSimpleName());
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					return Class.forName(className, true, targetClassesLoader).getSuperclass().equals(superclass);
				} catch (ClassNotFoundException e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.CLASS_NOT_EXTENDING_SUPERCLASS.getContent(className, superclass.getSimpleName());
			}
		};
	}
	
	public ITestCase operateConstructorViaSuper(int points, String className, ParameterTesting... args) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_OPERATION_OF_CONSTRUCTOR_HAVING_SUPERCLASS.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);
					Class<?> superclazz = clazz.getSuperclass();
					
					Object instance = clazz
							.getDeclaredConstructor(ParameterTestingUtils.mapToConstructorType(args))
							.newInstance(ParameterTestingUtils.mapToConstructorValue(args));
					
					for (java.lang.reflect.Field actualField : superclazz.getDeclaredFields()) {
						if (!actualField.canAccess(instance)) {
							actualField.setAccessible(true);
						}
						
						Optional<ParameterTesting> paramTestingOptional = Arrays.stream(args)
								.filter(arg -> arg.getName()
								.equals(actualField.getName()))
								.findFirst();
						if (paramTestingOptional.isEmpty()) {
							System.out.println("Element is not present: %s".formatted(actualField.getName()));
							continue;
						}
						
						if (actualField.getType().isEnum() && !paramTestingOptional.get().equalsEnumConstant((Class<? extends Enum<?>>) paramTestingOptional.get().getType().asSubclass(Enum.class), actualField.get(instance))) {
							return false;
						}
						
						if (!paramTestingOptional.get().getValue().equals(actualField.get(instance))) {
							return false;
						}
					}
					
					return true;
				} catch (NoSuchElementException e) {
					System.out.println("INVALID FIELDS: %s".formatted(e.getMessage()));
					return false;
				} catch (Exception e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.ARGS_CONSTRUCTOR_AMONG_SUPERCLASS_OPERATION_NOT_CORRECT.getContent(className);
			}
		};
	}
	
	/*
	 * constructor ***************
	 */

	/**
	 * No-argument constructor DECLARATION testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkNoArgConstructorDeclaration(int points, String className) {
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
	public ITestCase checkNoArgConstructorOperation(int points, String className, ParameterTesting params) {
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

					return ParameterTestingUtils.compareTestingValueViaGetter(clazz, clazz.getDeclaredConstructor().newInstance(),
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
	 * Full-argument constructor DECLARATION testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkFullArgsConstructorDeclaration(int points, String className, ParameterTesting... params) {
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
	public ITestCase checkFullArgsConstructorOperation(int points, String className, ParameterTesting... params) {
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
					Object[] testValues = Stream.of(params).map(pt -> pt.getValue()).toArray(Object[]::new);
					Object instance = clazz.getDeclaredConstructor(types).newInstance(testValues);

					for (ParameterTesting param : params) {
						if (!ParameterTestingUtils.compareTestingValueViaGetter(clazz, instance, param)) {
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
	
	
	public ITestCase checkPartialArgsConstructorDeclaration(int points, String className, Class<?>... paramTypes) {
		return checkPartialArgsConstructorDeclaration(points, className, ParameterTestingUtils.mapFromTypes(paramTypes));
	}

	/**
	 * Partial-argument constructor DECLARATION testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkPartialArgsConstructorDeclaration(int points, String className, ParameterTesting... params) {
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
	public ITestCase checkPartialArgsConstructorOperationViaGetter(int points, String className, ParameterTesting... params) {
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
					
					// TODO compare field as Object without using getter/setter
					Object instance = clazz
							.getDeclaredConstructor(ParameterTestingUtils.mapToType(params))
							.newInstance(ParameterTestingUtils.mapToTestingValue(params));

					for (ParameterTesting param : params) {
						if (!ParameterTestingUtils.compareTestingValueViaGetter(clazz, instance, param)) {
							return false;
						}
					}

					return true;
				} catch (Exception e) {
					e.printStackTrace();
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
	
	// TODO operateConstructorHavingSuperclass
	@Deprecated
	public ITestCase operateConstructorHavingSuperclass(int points, String className, String superclassName, ParameterTesting... params) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_OPERATION_OF_CONSTRUCTOR_HAVING_SUPERCLASS.getName(className, superclassName);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);
					Class<?> superclazz = Class.forName(superclassName, true, targetClassesLoader);
					Object instance = clazz
							.getDeclaredConstructor(ParameterTestingUtils.mapToType(params))
							.newInstance(ParameterTestingUtils.mapToTestingValue(params));

					for (ParameterTesting param : params) {
						if (!ParameterTestingUtils.compareTestingValueViaGetter(clazz, instance, param)) {
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
				return Feedback.ARGS_CONSTRUCTOR_AMONG_SUPERCLASS_OPERATION_NOT_CORRECT.getContent(className, superclassName);
			}
		};
	}
	
	/*
	 * attribute ***************
	 */

	/**
	 * Attribute declaration testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkAttributes(int points, String className) {
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
