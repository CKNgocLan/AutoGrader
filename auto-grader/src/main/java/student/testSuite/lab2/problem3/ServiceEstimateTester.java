package student.testSuite.lab2.problem3;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.Feedback;
import student.constant.FieldName;
import student.constant.MethodName;
import student.constant.TestcaseType;
import student.exception.InvalidConfigurationException;
import student.model.ClassLoader;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.Method;
import student.model.MethodTesting;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;
import student.util.MethodUtils;
import student.util.SetterUtils;

public class ServiceEstimateTester {
	private static ServiceEstimateTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private static String className = ClassName.SERVICE_ESTIMATE;
	private static Class<?> clazz;
	private final double TAX = 0.08;

	/*
	 * instance **********
	 */

	public static ServiceEstimateTester getInstance() {
		if (instance == null) {
			instance = new ServiceEstimateTester();
		}

		return instance;
	}

	/*
	 * Class ***************
	 */
	
	public static Class<?> getCorrespondingClass() throws ClassNotFoundException {
		if (clazz == null) {
			clazz = Class.forName(className, true, ClassLoader.getInstance());
		}

		return clazz;
	}
	
	/*
	 * initialize
	 */
	
	public static Object initObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return getCorrespondingClass().getDeclaredConstructor().newInstance();
	}
	
	public static Object initObject(double groomingCost, double additionalCareCost, double tax) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
	InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Object instance = getCorrespondingClass().getDeclaredConstructor().newInstance();
		clazz.getMethod(SetterUtils.getSetterName(FieldName.GROOMING_COST), double.class).invoke(instance, groomingCost);
		clazz.getMethod(SetterUtils.getSetterName(FieldName.ADDITIONAL_CARE_COST), double.class).invoke(instance, additionalCareCost);
		clazz.getMethod(SetterUtils.getSetterName(FieldName.TAX), double.class).invoke(instance, tax);
		
		return instance;
	}

	/*
	 * Existence **********
	 */

	public ITestCase checkExistence(int points) {
		return classTester.checkExistence(points, className);
	}

	/*
	 * Field **********
	 */

	public ITestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarations(points, className, new FieldTesting(double.class, FieldName.GROOMING_COST),
				new FieldTesting(double.class, FieldName.ADDITIONAL_CARE_COST),
				new FieldTesting(double.class, FieldName.TAX),
				new FieldTesting(student.model.ClassLoader.retrieveClass(ClassName.PET), FieldName.PET));
	}

	/*
	 * Constructor **********
	 */

	public ITestCase checkNoArgsConstructors(int points) throws ClassNotFoundException {
		return classTester.checkNoArgConstructorDeclaration(points, className);
	}

	/*
	 * toString **********
	 */

	public ITestCase checkToStringExistence(int points) {
		return methodTester.checkExistence(points, className, new Method(String.class, MethodName.TO_STRING));
	}
	
	public ITestCase checkToStringOperation(int points) {
		return checkToStringOperation(points, 1.2, 2.3, TAX);
	}
	
	public ITestCase checkToStringOperation(int points, double groomingCost, double additionalCareCost, double tax) {
		MethodTesting method = MethodUtils.createMethodToString();

		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_METHOD_OPERATION.getName(className, method.getName());
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					method.setClazz(getCorrespondingClass());

					// Prepare test data
					method.setInstance(initObject());
					String actual = method.invokeToString();

					// Get captured output then compare
					return actual.contains(String.valueOf(groomingCost))
							&& actual.contains(String.valueOf(additionalCareCost))
							&& actual.contains(String.valueOf(tax));
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(className, method.getName());
			}
		};
	}

	/*
	 * getTotalCostAfterTax **********
	 */

	public ITestCase checkGetTotalCostAfterTaxExistence(int points) {
		return methodTester.checkExistence(points, className,
				new Method(double.class, MethodName.GET_TOTAL_COST_AFTER_TAX));
	}
	
	public ITestCase checkGetTotalCostAfterTaxOperation(int points) {
		return checkGetTotalCostAfterTaxOperation(points, 2.484, 1.5, 0.8, TAX);
	}
	
	public ITestCase checkGetTotalCostAfterTaxOperation(int points, double expected, double groomingCost, double additionalCareCost, double tax) {
		MethodTesting method = new MethodTesting(double.class, MethodName.GET_TOTAL_COST_AFTER_TAX);

		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_METHOD_OPERATION.getName(className, method.getName());
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					// Prepare test data
					method.setClazz(getCorrespondingClass());
					method.setInstance(initObject(groomingCost, additionalCareCost, tax));

					return Double.valueOf(method.returning().toString()).equals(expected);
				} catch (InvalidConfigurationException e) {
					return false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(className, method.getName());
			}
		};
	}
}
