package student.testSuite.lab2;

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
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;
import student.util.MethodUtils;
import student.util.SetterUtils;

public class CustomerTester {
	private static CustomerTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
    private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private static String className = ClassName.CUSTOMER;
	private static Class<?> clazz;

	/*
	 * Instance ***************
	 */

	public static CustomerTester getInstance() throws ClassNotFoundException {
		if (instance == null) {
			instance = new CustomerTester();
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
	
	public static Object initObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return getCorrespondingClass().getDeclaredConstructor().newInstance();
	}
	
	public static Object initObject(String name) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		getCorrespondingClass();
		Object instance = clazz.getDeclaredConstructor().newInstance();
		clazz.getMethod(MethodName.SET_NAME, String.class).invoke(instance, name);
		
		return instance;
	}
	
	public static Object initObject(String name, String address, String phoneNumber) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Object instance = clazz.getDeclaredConstructor().newInstance();
		clazz.getMethod(SetterUtils.getSetterName(FieldName.NAME), String.class).invoke(instance, name);
		clazz.getMethod(SetterUtils.getSetterName(FieldName.ADDRESS), String.class).invoke(instance, address);
		clazz.getMethod(SetterUtils.getSetterName(FieldName.PHONE_NUMBER), String.class).invoke(instance, phoneNumber);
		
		return instance;
	}

	/*
	 * Existence ***************
	 */

	public ITestCase checkExistence(int points) {
		return classTester.checkExistence(points, className);
	}

    /*
     * Constructor ***************
     */
    
    public ITestCase checkNoArgsConstructors(int points) throws ClassNotFoundException {
        return classTester.checkNoArgConstructorDeclaration(points, className);
    }

	/*
	 * Field ***************
	 */
	
	public ITestCase checkFields(int points) {
		return fieldTester.checkDeclarations(points, className
				, new FieldTesting(String.class, FieldName.NAME)
				, new FieldTesting(String.class, FieldName.ADDRESS)
				, new FieldTesting(String.class, FieldName.PHONE_NUMBER)
		);
	}

    /*
     * Getter ***************
     */
    public ITestCase checkGetterDeclaration(int points) {
        return methodTester.checkGetterDeclaration(points, className);
    }

    /*
     * Setter ***************
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
	
	public ITestCase checkToStringOperation(int points) throws ClassNotFoundException {
		return checkToStringOperation(points, "Old Man Hac", "Nam Cao", "uncle.golden@gmail.com");
	}
	
	public ITestCase checkToStringOperation(int points, String name, String address, String phoneNumber) throws ClassNotFoundException {
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
					method.setInstance(initObject(name, address, phoneNumber));

					// Prepare test data
					String actual = method.invokeToString();

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
