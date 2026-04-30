package student.testSuite.lab2.problem5;

import student.constant.ClassName;
import student.constant.Feedback;
import student.constant.FieldName;
import student.constant.MethodName;
import student.constant.TestcaseType;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.Method;
import student.model.MethodTesting;
import student.model.ParameterTesting;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;
import student.util.MethodUtils;
import student.util.ParameterTestingUtils;

public class CarTester {
	private static CarTester instance = null;
	private ClassTestcaseCreator classTest = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private MethodTestcaseCreator methodTest = MethodTestcaseCreator.getInstance();
	private ClassLoader targetClassesLoader = student.model.ClassLoader.getInstance();
	private String className = ClassName.CAR;

	/*
	 * instance ***************************************************************************
	 */

	public static CarTester getInstance() {
		if (instance == null) {
			instance = new CarTester();
		}

		return instance;
	}

	/*
	 * Existence ***************************************************************************
	 */

	public ITestCase checkExistence(int points) {
		return classTest.checkExistence(points, className);
	}

	/*
	 * Fields ***************************************************************************
	 */
	
	public ITestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarations(points, className
				, new FieldTesting(String.class, FieldName.MAKE)
				, new FieldTesting(String.class, FieldName.MODEL)
				, new FieldTesting(int.class, FieldName.PERIOD)
				, new FieldTesting(int.class, FieldName.MILEAGE_LIMIT)
				, new FieldTesting(student.model.ClassLoader.retrieveClass(ClassName.CUSTOMER), FieldName.CUSTOMER)
		);
	}

	/*
	 * Constructor ***************************************************************************
	 */

	public ITestCase checkPartialArgsConstructorDeclaration(int points, ParameterTesting... params) {
		return classTest.checkPartialArgsConstructorDeclaration(points, className, params);
	}

	public ITestCase checkPartialArgsConstructorOperation(int points, ParameterTesting... params) {
		return classTest.checkPartialArgsConstructorOperation(points, className, params);
	}

	/*
	 * Accelerate ***************************************************************************
	 */
	
	public ITestCase checkAccelerateDeclaration(int points) {
		return methodTest.checkExistence(points, ClassName.CAR, new Method(MethodName.ACCELERATE, int.class));
	}
	
	public ITestCase checkAccelerateOperation(int points) {
		return new ITestCase() {
			MethodTesting methodTesting = new MethodTesting(MethodName.ACCELERATE, int.class, 5);
			@Override
			public String getName() {
				return TestcaseType.CHECK_METHOD_OPERATION.getName(className, methodTesting.getName());
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);

					if (!MethodUtils.isMethodDeclared(clazz, methodTesting)) {
						return false;
					}
					
					Object instance = clazz.getDeclaredConstructor(int.class, String.class).newInstance(2025, "Mazda");
					
					clazz.getDeclaredMethod(methodTesting.getName()).invoke(instance);
					
					return ParameterTestingUtils.compareTestingValue(clazz, instance,
							new ParameterTesting(FieldName.SPEED, methodTesting.getReturnedType(), methodTesting.getTestingValue())
					);
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
				return Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(className, methodTesting.getName());
			}
		};
	}

	/*
	 * Brake ***************************************************************************
	 */
	
	public ITestCase checkBrakeDeclaration(int points) {
		return methodTest.checkExistence(points, ClassName.CAR, new Method(MethodName.BRAKE, int.class));
	}
	
	public ITestCase checkBrakeOperation(int points) {
		return new ITestCase() {
			MethodTesting methodTesting = new MethodTesting(MethodName.BRAKE, int.class, 0);
			
			@Override
			public String getName() {
				return TestcaseType.CHECK_METHOD_OPERATION.getName(className, methodTesting.getName());
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);

					if (!MethodUtils.isMethodDeclared(clazz, methodTesting)) {
						return false;
					}
					
					Object instance = clazz.getDeclaredConstructor(int.class, String.class).newInstance(2025, "Mazda");
					
					clazz.getDeclaredMethod(MethodName.ACCELERATE).invoke(instance);
					
					clazz.getDeclaredMethod(methodTesting.getName()).invoke(instance);
					
					return ParameterTestingUtils.compareTestingValue(clazz, instance,
							new ParameterTesting(FieldName.SPEED, methodTesting.getReturnedType(), methodTesting.getTestingValue())
					);
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
				return Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(className, methodTesting.getName());
			}
		};
	}
}
