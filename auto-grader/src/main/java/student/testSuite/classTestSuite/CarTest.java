package student.testSuite.classTestSuite;

import student.checker.MethodChecker;
import student.constant.ClassName;
import student.constant.Feedback;
import student.constant.FieldName;
import student.constant.MethodName;
import student.constant.TestcaseType;
import student.model.ITestCase;
import student.model.Method;
import student.model.MethodTesting;
import student.model.ParameterTesting;
import student.testSuite.methodTestSuite.MethodTest;
import student.util.MethodUtils;
import student.util.ParameterTestingUtils;
import student.util.SetterUtils;

public class CarTest {
	private static CarTest instance = null;
	private ClassTest classTest = ClassTest.getInstance();
	private MethodTest methodTest = MethodTest.getInstance();
	private MethodChecker methodChecker = MethodChecker.getInstance();
	private ClassLoader targetClassesLoader = student.model.ClassLoader.getInstance();
	private String className = ClassName.CAR;

	/*
	 * ***************************************************************************
	 */

	public static CarTest getInstance() {
		if (instance == null) {
			instance = new CarTest();
		}

		return instance;
	}

	/*
	 * ***************************************************************************
	 */

	/**
	 * Class existence testcase
	 * 
	 * @param points
	 * @return
	 */
	public ITestCase checkExistence(int points) {
		return classTest.checkExistence(className, points);
	}

	/**
	 * Partial-argument constructor DECLARATION testcase
	 * 
	 * @param points
	 * @return
	 */
	public ITestCase checkPartialArgsConstructorDeclaration(int points, ParameterTesting... params) {
		return classTest.checkPartialArgsConstructorDeclaration(className, points, params);
	}
	


	/**
	 * Partial-argument constructor OPERATION testcase
	 * 
	 * @param className
	 * @param points
	 * @return
	 */
	public ITestCase checkPartialArgsConstructorOperation(int points, ParameterTesting... params) {
		return classTest.checkPartialArgsConstructorOperation(className, points, params);
	}
	
	/**
	 * Method operation testcase
	 * 
	 * @param points
	 * @return
	 */
	public ITestCase checkAccelerateDeclaration(int points) {
		return methodTest.checkExistence(ClassName.CAR, points, new Method(MethodName.ACCELERATE, int.class));
	}
	
	/**
	 * Method operation testcase
	 * 
	 * @param points
	 * @return
	 */
	public ITestCase checkBrakeDeclaration(int points) {
		return methodTest.checkExistence(ClassName.CAR, points, new Method(MethodName.BRAKE, int.class));
	}
	
	/**
	 * Method operation testcase
	 * 
	 * @param points
	 * @return
	 */
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
