package student.testSuite.lab2.problem6;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.model.ClassLoader;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;
import student.util.MethodUtils;

public class UserTester {
	private static UserTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
    private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private static String className = ClassName.USER;
	private static Class<?> clazz;

	/*
	 * instance ***************
	 */

	public static UserTester getInstance() {
		if (instance == null) {
			instance = new UserTester();
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
	
	public static Object initObject(String id, String name) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return getCorrespondingClass().getDeclaredConstructor(String.class, String.class).newInstance(id, name);
	}

	/*
	 * existence ***************
	 */

	public ITestCase checkExistence(int points) {
		return classTester.checkExistence(points, className);
	}

	/*
	 * constructor ***************
	 */

	public ITestCase checkPartialArgsConstructorDeclaration(int points, ParameterTesting... params) {
		return classTester.checkPartialArgsConstructorDeclaration(points, className, params);
	}

	/*
	 * fields ***************
	 */
	
	public ITestCase checkFields(int points) {
		return fieldTester.checkDeclarations(points, className
				, new FieldTesting(String.class, FieldName.ID)
				, new FieldTesting(String.class, FieldName.NAME)
				, new FieldTesting(String.class, FieldName.EMAIL)
		);
	}

    /*
     * getter ***************
     */
	
    public ITestCase checkGetterDeclaration(int points) {
        return methodTester.checkGetterDeclaration(points, className);
    }

    /*
     * setter ***************
     */
    
    public ITestCase checkSetterDeclaration(int points) {
        return methodTester.checkSetterDeclaration(points, className);
    }

    /*
     * equals ***************
     */

    public ITestCase checkEqualsDeclaration(int points) throws ClassNotFoundException {
    	return methodTester.checkExistence(points, className,
				MethodUtils.createMethodEquals(FieldName.USER, UserTester.getCorrespondingClass()));
    }
    
//    public ITestCase checkEqualsDeclaration(int points) throws ClassNotFoundException {
//		MethodTesting method = new MethodTesting(boolean.class, MethodName.EQUALS,
//				new Parameter(FieldName.USER, UserTester.getCorrespondingClass()));
//
//		return new ITestCase() {
//			@Override
//			public String getName() {
//				return TestcaseType.CHECK_METHOD_OPERATION.getName(className, method.getName());
//			}
//
//			@Override
//			public int getPoints() {
//				return points;
//			}
//
//			@Override
//			public boolean runTest() {
//				try {
//					method.setClazz(getCorrespondingClass());
//					method.setInstance(initObject(make, model, period, mileageLimit, customer));
//
//					// Prepare test data
//					String actual = method.invokeToString();
//
//					// Get captured output then compare
//					return actual.contains(make)
//							&& actual.contains(model)
//							&& actual.contains(String.valueOf(period))
//							&& actual.contains(String.valueOf(mileageLimit))
//							&& actual.contains(String.valueOf(customer))
//							;
//				} catch (Exception e) {
//					System.out.println(e.getMessage());
//					return false;
//				}
//			}
//
//			@Override
//			public String getFeedback() {
//				return Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(className, method.getName());
//			}
//		};
//	}
}
