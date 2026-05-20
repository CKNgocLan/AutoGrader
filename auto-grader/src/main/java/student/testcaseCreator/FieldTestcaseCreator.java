package student.testcaseCreator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

import student.checker.FieldChecker;
import student.constant.Constants;
import student.constant.Feedback;
import student.constant.TestcaseType;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.util.ClassUtils;

public class FieldTestcaseCreator {
	private static FieldTestcaseCreator instance = null;
	private ClassLoader targetClassesLoader = student.model.ClassLoader.getInstance();
	private FieldChecker fieldChecker = FieldChecker.getInstance();

	/*
	 * ***************************************************************************
	 */

	public static FieldTestcaseCreator getInstance() {
		if (instance == null) {
			instance = new FieldTestcaseCreator();
		}

		return instance;
	}

	/*
	 * ***************************************************************************
	 */

	public ITestCase checkDeclaration(int points, String className, String fieldName, Class<?> type) {
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
					return fieldChecker.checkDeclaration(
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
	
	public ITestCase checkDeclarations(int points, String className, FieldTesting... fields) {
		String fieldNames = String.join(Constants.COMMA_WITH_SPACE, Stream.of(fields).map(f -> f.getName()).toList());
		
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_FIELD.getName(className, fieldNames);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);
					
					if (fields.length > clazz.getDeclaredFields().length) {
						return false;
					}
					
					for (FieldTesting testingField : fields) {
						if (!ClassUtils.containField(clazz, testingField)) {
							return false;
						}
					}
					return true;
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.FIELD_DECLARED_NOT_CORRECT.getContent(className, fieldNames);
			}
		};
	}
	
	public ITestCase noField(int points, String className) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_NO_FIELD.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);
					
					if (clazz.getDeclaredFields().length > 0) {
						return false;
					}
					
					return true;
					
				} catch (ClassNotFoundException e) {
					for (StackTraceElement st : e.getStackTrace()) {
						System.out.println(st);
					}

					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.FIELD_MUST_NOT_BE_DECLARED.getContent(className);
			}
		};
	}
	
	public ITestCase checkDeclarationsAsSpecialModifiers(int points, String className, FieldTesting... fields) {
		String fieldNames = String.join(Constants.COMMA_WITH_SPACE, Stream.of(fields).map(f -> f.getName()).toList());
		
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_FIELD.getName(className, fieldNames);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);
					
					if (fields.length > clazz.getDeclaredFields().length) {
						return false;
					}
					
					for (FieldTesting testingField : fields) {
						if (!ClassUtils.containFieldButModifiers(clazz, testingField)) {
							return false;
						}
						
						int declaredModifier = clazz.getDeclaredField(testingField.getName()).getModifiers();
						if ((testingField.isStatic() && !Modifier.isStatic(declaredModifier))
								|| testingField.isFinal() && !Modifier.isFinal(declaredModifier)) {
							return false;
						}
					}
					return true;
					
				} catch (ClassNotFoundException | NoSuchFieldException e) {
					for (StackTraceElement st : e.getStackTrace()) {
						System.out.println(st);
					}

					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.FIELD_DECLARED_NOT_CORRECT.getContent(className, fieldNames);
			}
		};
	}
	
	public ITestCase checkDeclarationsAsPublicStaticFinal(int points, String className, FieldTesting... fields) {
		String fieldNames = String.join(Constants.COMMA_WITH_SPACE, Stream.of(fields).map(f -> f.getName()).toList());
		
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_FIELD.getName(className, fieldNames);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);
					Object instance = clazz.getDeclaredConstructor().newInstance();
					
					if (fields.length > clazz.getDeclaredFields().length) {
						return false;
					}
					
					for (FieldTesting testingField : fields) {
						java.lang.reflect.Field reflectField = clazz.getField(testingField.getName());

						if (!fieldChecker.checkPublicStaticFinalField(clazz, reflectField)) {
							return false;
						}
						
						if (!reflectField.getType().equals(testingField.getType())) {
							return false;
						}
						
						if(!fieldChecker.compareValue(testingField, reflectField.get(instance))) {
							return false;
						}
					}
					return true;
					
				} catch (ClassNotFoundException | NoSuchFieldException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
					e.printStackTrace();
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.FIELD_DECLARED_NOT_CORRECT.getContent(className, fieldNames);
			}
		};
	}
}
