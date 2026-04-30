package student.testSuite.lab2.problem3;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.Feedback;
import student.constant.FieldName;
import student.constant.MethodName;
import student.constant.TestcaseType;
import student.model.ClassLoader;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.Method;
import student.model.MethodTesting;
import student.model.ParameterTesting;
import student.testSuite.lab2.CustomerTester;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;
import student.util.MethodUtils;

public class PetTester {
	private static PetTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private static String className = ClassName.PET;
	private static Class<?> clazz;

	/*
	 * instance ***************
	 */

	public static PetTester getInstance() {
		if (instance == null) {
			instance = new PetTester();
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
	
	public static Object initObject(Object customer) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return getCorrespondingClass().getDeclaredConstructor(CustomerTester.getCorrespondingClass()).newInstance(customer);
	}

	/*
	 * Existence ***************
	 */

	public ITestCase checkExistence(int points) {
		return classTester.checkExistence(points, className);
	}

	/*
	 * Fields ***************
	 */

	public ITestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarations(points, className, new FieldTesting(String.class, FieldName.BREED),
				new FieldTesting(int.class, FieldName.AGE), new FieldTesting(double.class, FieldName.WEIGHT),
				new FieldTesting(ClassLoader.retrieveClass(ClassName.CUSTOMER), FieldName.CUSTOMER));
	}

	/*
	 * Constructor ***************
	 */

	public ITestCase checkPartialArgsConstructors(int points, ParameterTesting... params)
			throws ClassNotFoundException {
		return classTester.checkPartialArgsConstructorDeclaration(points, className, params);
	}

	/*
	 * toString ***************
	 */

	public ITestCase checkToStringExistence(int points) {
		return methodTester.checkExistence(points, className, new Method(String.class, MethodName.TO_STRING));
	}
	
	public ITestCase checkToStringOperation(int points) {
		return checkToStringOperation(points, "Alice", "New York", "0123456789");
	}
	
	public ITestCase checkToStringOperation(int points, String name, String address, String phoneNumber) {
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
					Object customer = CustomerTester.initObject(name, address, phoneNumber);
					String actual = method.invokeToString(initObject(customer));

					// Get captured output then compare
					return actual.contains(name) && actual.contains(address) && actual.contains(phoneNumber);
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
