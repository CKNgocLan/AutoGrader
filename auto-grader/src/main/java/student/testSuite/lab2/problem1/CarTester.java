package student.testSuite.lab2.problem1;

import student.constant.ClassName;
import student.constant.Feedback;
import student.constant.FieldName;
import student.constant.MethodName;
import student.constant.TestcaseType;
import student.model.TestingField;
import student.model.TestCase;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;
import student.util.ParameterUtils;

public class CarTester {
	private static CarTester instance = null;
	private ClassTestcaseCreator classTest = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private ClassLoader targetClassesLoader = student.model.ClassLoader.getInstance();
	private String className = ClassName.CAR;

	/*
	 * instance **********
	 */

	public static CarTester getInstance() {
		if (instance == null) {
			instance = new CarTester();
		}

		return instance;
	}

	/*
	 * Existence **********
	 */

	public TestCase checkExistence(int points) {
		return classTest.checkExistence(points, className);
	}

	/*
	 * Fields **********
	 */

	public TestCase checkFields(int points) {
		return fieldTester.checkDeclarations(points, className,
				new TestingField(int.class, FieldName.YEAR_MODEL),
				new TestingField(String.class, FieldName.MAKE), new TestingField(int.class, FieldName.SPEED)
				);
	}

	/*
	 * Constructor **********
	 */

	public TestCase checkPartialArgsConstructorDeclaration(int points, TestingParameter... params) {
		return classTest.checkPartialArgsConstructorDeclaration(points, className, params);
	}

	public TestCase checkPartialArgsConstructorOperation(int points, TestingParameter... params) {
		return classTest.checkPartialArgsConstructorOperationViaGetter(points, className, params);
	}

	/*
	 * Accelerate **********
	 */

	public TestCase checkAccelerateDeclaration(int points) {
		return methodTester.declare(points, ClassName.CAR, new TestingMethod(int.class, MethodName.ACCELERATE));
	}

	public TestCase checkAccelerateOperation(int points) {
		TestingMethod methodTesting = new TestingMethod(int.class, MethodName.ACCELERATE).expectedValue(5);

		return new TestCase() {

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

					if (!methodTester.getMethodChecker().isMethodDeclared(clazz, methodTesting)) {
						return false;
					}

					Object instance = clazz.getDeclaredConstructor(int.class, String.class).newInstance(2025, "Mazda");

					clazz.getDeclaredMethod(methodTesting.getName()).invoke(instance);

					return ParameterUtils.compareTestingValueViaGetter(clazz, instance, new TestingParameter(
							methodTesting.getReturnedType(), FieldName.SPEED, methodTesting.getExpectedValue()));
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
	 * Brake **********
	 */

	public TestCase checkBrakeDeclaration(int points) {
		return methodTester.declare(points, ClassName.CAR, new TestingMethod(int.class, MethodName.BRAKE));
	}

	public TestCase checkBrakeOperation(int points) {
		TestingMethod methodTesting = new TestingMethod(int.class, MethodName.BRAKE).expectedValue(0);

		return new TestCase() {

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

					if (!methodTester.getMethodChecker().isMethodDeclared(clazz, methodTesting)) {
						return false;
					}

					Object instance = clazz.getDeclaredConstructor(int.class, String.class).newInstance(2025, "Mazda");

					clazz.getDeclaredMethod(MethodName.ACCELERATE).invoke(instance);

					clazz.getDeclaredMethod(methodTesting.getName()).invoke(instance);

					return ParameterUtils.compareTestingValueViaGetter(clazz, instance, new TestingParameter(
							methodTesting.getReturnedType(), FieldName.SPEED, methodTesting.getExpectedValue()));
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
