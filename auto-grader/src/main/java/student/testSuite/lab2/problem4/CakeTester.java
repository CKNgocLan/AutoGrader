package student.testSuite.lab2.problem4;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

import student.constant.ClassName;
import student.constant.Feedback;
import student.constant.FieldName;
import student.constant.MethodName;
import student.constant.TestcaseType;
import student.model.ClassLoader;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.MethodTesting;
import student.model.ParameterTesting;
import student.testSuite.lab2.CustomerTester;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;
import student.util.MethodUtils;

public class CakeTester {
	private static CakeTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
    private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private static String className = ClassName.CAKE;
	private static Class<?> clazz;

	/*
	 * Instance ***************************************************************************
	 */
	public static CakeTester getInstance() {
		if (instance == null) {
			instance = new CakeTester();
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
	
//	public static Object initObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
//			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
//		return getCorrespondingClass().getDeclaredConstructor().newInstance();
//	}
	
	public static Object initObject(Object customer, Object event, int tierNumber, double price) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return getCorrespondingClass().getDeclaredConstructor(
				CustomerTester.getCorrespondingClass()
				, EventTester.getCorrespondingClass()
				, int.class
				, double.class
		).newInstance(customer, event, tierNumber, price);
	}

	/*
	 * Existence ***************************************************************************
	 */
	public ITestCase checkExistence(int points) {
		return classTester.checkExistence(points, className);
	}

    /*
     * Constructor ***************
     */

	public ITestCase checkPartialArgsConstructors(int points, ParameterTesting... params)
			throws ClassNotFoundException {
		return classTester.checkPartialArgsConstructorDeclaration(points, className, params);
	}

	/*
	 * Fields ***************************************************************************
	 */
	public ITestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarations(points, className
				, new FieldTesting(int.class, FieldName.TIER_NUMBER)
				, new FieldTesting(LocalDate.class, FieldName.EVENT_DATE)
				, new FieldTesting(double.class, FieldName.PRICE)
				, new FieldTesting(EventTester.getCorrespondingClass(), FieldName.TYPE)
				, new FieldTesting(ClassLoader.retrieveClass(ClassName.CUSTOMER), FieldName.CUSTOMER)
		);
	}

    /*
     * Getter ***************************************************************************
     */
    public ITestCase checkGetterDeclaration(int points) {
        return methodTester.checkGetterDeclaration(points, className);
    }

    /*
     * Setter ***************************************************************************
     */
    public ITestCase checkSetterDeclaration(int points) {
        return methodTester.checkSetterDeclaration(points, className);
    }

	/*
	 * toString ***************
	 */
	
	public ITestCase checkToStringDeclaration(int points) {
		return methodTester.checkExistence(points, className, new MethodTesting(String.class, MethodName.TO_STRING));
	}
	
	public ITestCase checkToStringOperation(int points)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		return checkToStringOperation(points, CustomerTester.initObject("Lao Hac"), Class.forName(ClassName.EVENT).getEnumConstants()[0], 2, 2.555);
	}
	
	public ITestCase checkToStringOperation(int points, Object customer, Object event, int tierNumber, double price) throws ClassNotFoundException {
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
					method.setInstance(initObject(customer, event, tierNumber, price));

					// Prepare test data
					String actual = method.invokeToString();

					// Get captured output then compare
					return actual.contains(customer.toString())
							&& actual.contains(event.toString())
							&& actual.contains(String.valueOf(tierNumber))
							&& actual.contains(String.valueOf(price))
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
