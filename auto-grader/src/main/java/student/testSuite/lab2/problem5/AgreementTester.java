package student.testSuite.lab2.problem5;

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
import student.model.MethodTesting;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;

public class AgreementTester {
	private static AgreementTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private static String className = ClassName.AGREEMENT;
	private static Class<?> clazz;

	/*
	 * instance
	 * ***************************************************************************
	 */

	public static AgreementTester getInstance() {
		if (instance == null) {
			instance = new AgreementTester();
		}

		return instance;
	}

	/*
	 * class ***************
	 */
	
	public static Class<?> getCorrespondingClass() throws ClassNotFoundException {
		if (clazz == null) {
			clazz = Class.forName(className, true, ClassLoader.getInstance());
		}

		return clazz;
	}
	
	/*
	 * initialize object ***************
	 */
	
	public static Object initObject(Object car, String purpose, double baseRentalFee, double mileageFee)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		return clazz
				.getDeclaredConstructor(CarRentalTester.getCorrespondingClass(), String.class, double.class, double.class)
				.newInstance(car, purpose, baseRentalFee, mileageFee);
	}

	/*
	 * Existence
	 * ***************************************************************************
	 */

	public ITestCase checkExistence(int points) {
		return classTester.checkExistence(points, className);
	}

	/*
	 * Fields
	 * ***************************************************************************
	 */

	public ITestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarations(points, className, new FieldTesting(String.class, FieldName.PURPOSE),
				new FieldTesting(double.class, FieldName.BASE_RENTAL_FEE),
				new FieldTesting(double.class, FieldName.MILEAGE_FEE),
				new FieldTesting(CarRentalTester.getCorrespondingClass(), FieldName.CAR));
	}

	/*
	 * getRentalCostAfterTax **********
	 */
	
	public ITestCase checkGetRentalCostAfterTaxOperation(int points, Object car, String purpose, double baseRentalFee, double mileageFee, double rentalCostAfterTax) {
		MethodTesting method = new MethodTesting(double.class, MethodName.GET_RENTAL_COST_AFTER_TAX);

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
					return method
							.config(getCorrespondingClass(), initObject(car, purpose, baseRentalFee, mileageFee))
							.assertExpectedValue(rentalCostAfterTax);
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
				return Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(className, method.getName());
			}
		};
	}
}
