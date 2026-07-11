package student.testSuite.lab2.problem5;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.Feedback;
import student.constant.FieldName;
import student.constant.TestcaseType;
import student.model.ClassLoader;
import student.model.TestingField;
import student.model.TestCase;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;
import student.util.MethodUtils;

public class CarRentalTester {
	private static CarRentalTester instance = null;
	private ClassTestcaseCreator classTest = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private static String className = ClassName.CAR;
	private static Class<?> clazz;

	/*
	 * instance ***************************************************************************
	 */

	public static CarRentalTester getInstance() {
		if (instance == null) {
			instance = new CarRentalTester();
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
	// String make, String model, int period, int mileageLimit, Customer customer
	public static Object initObject(String make, String model, int period, int mileageLimit, Object customer)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		return getCorrespondingClass()
				.getDeclaredConstructor(String.class, String.class, int.class, int.class,
						CarRentalCustomerTester.getCorrespondingClass())
				.newInstance(make, model, period, mileageLimit, customer);
	}

	/*
	 * Existence ***************************************************************************
	 */

	public TestCase checkExistence(int points) {
		return classTest.checkExistence(points, className);
	}

	/*
	 * Fields ***************************************************************************
	 */
	
	public TestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarations(points, className
				, new TestingField(String.class, FieldName.MAKE)
				, new TestingField(String.class, FieldName.MODEL)
				, new TestingField(int.class, FieldName.PERIOD)
				, new TestingField(int.class, FieldName.MILEAGE_LIMIT)
				, new TestingField(CarRentalCustomerTester.getCorrespondingClass(), FieldName.CUSTOMER)
		);
	}

	/*
	 * Constructor ***************************************************************************
	 */

	public TestCase checkPartialArgsConstructorDeclaration(int points, TestingParameter... params) {
		return classTest.checkPartialArgsConstructorDeclaration(points, className, params);
	}

	public TestCase checkPartialArgsConstructorOperation(int points, TestingParameter... params) {
		return classTest.checkPartialArgsConstructorOperationViaGetter(points, className, params);
	}

    /*
     * Getter ***************************************************************************
     */
    public TestCase checkGetterDeclaration(int points) {
        return methodTester.checkGetterDeclaration(points, className);
    }

    /*
     * Setter ***************************************************************************
     */
    public TestCase checkSetterDeclaration(int points) {
        return methodTester.checkSetterDeclaration(points, className);
    }

	/*
	 * toString ***************
	 */
	
	public TestCase checkToStringOperation(int points, String make, String model, int period, int mileageLimit, Object customer) throws ClassNotFoundException {
		TestingMethod method = MethodUtils.createMethodToString();

		return new TestCase() {
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
					method.setInstance(initObject(make, model, period, mileageLimit, customer));

					// Prepare test data
					String actual = method.invokeToString();

					// Get captured output then compare
					return actual.contains(make)
							&& actual.contains(model)
							&& actual.contains(String.valueOf(period))
							&& actual.contains(String.valueOf(mileageLimit))
							&& actual.contains(String.valueOf(customer))
							;
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
