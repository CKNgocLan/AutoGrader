package student.testSuite.lab2.problem4;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
import student.util.SetterUtils;

public class QuoteTester {
	private static QuoteTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
    private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private static String className = ClassName.QUOTE;
	private static Class<?> clazz;

	/*
	 * Instance ***************************************************************************
	 */

	public static QuoteTester getInstance() {
		if (instance == null) {
			instance = new QuoteTester();
		}

		return instance;
	}

	/*
	 * Existence ***************************************************************************
	 */

	public ITestCase checkExistence(int points) {
		return classTester.checkExistence(points, className);
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
     * Constructor ***************
     */
    
    public ITestCase checkPartialArgsConstructorDeclaration(int points, ParameterTesting... params) throws ClassNotFoundException {
        return classTester.checkPartialArgsConstructorDeclaration(points, className, params);
    }

	/*
	 * initialize
	 */

	public static Object initObject(Object cake, double laborCharge, double deliveryFee) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return getCorrespondingClass()
				.getDeclaredConstructor(CakeTester.getCorrespondingClass(), double.class, double.class)
				.newInstance(cake, laborCharge, deliveryFee);
	}

	public static Object initObject(List<String> ingredient, Object cake, double laborCharge, double deliveryFee) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Object quoteInstance = getCorrespondingClass()
				.getDeclaredConstructor(CakeTester.getCorrespondingClass(), double.class, double.class)
				.newInstance(cake, laborCharge, deliveryFee);
		clazz.getMethod(SetterUtils.getSetterName(FieldName.INGREDIENT), List.class).invoke(quoteInstance, ingredient);
		
		return quoteInstance;
	}

	/*
	 * Fields ***************
	 */
	
	public ITestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarations(points, className
				, new FieldTesting(List.class, FieldName.INGREDIENT)
				, new FieldTesting(double.class, FieldName.LABOR_CHARGE)
				, new FieldTesting(double.class, FieldName.DELIVERY_FEE)
				, new FieldTesting(CakeTester.getCorrespondingClass(), FieldName.CAKE)
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
	
	public ITestCase checkToStringOperation(int points, List<String> ingredient, double laborCharge, double deliveryFee, Object cake) {
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
//					Object cake = CakeTester.initObject(CustomerTester.initObject("Lao Hac"), EventTester.initObject(0), 2, 2.55);
					Object instance = initObject(ingredient, cake, laborCharge, deliveryFee);
					
					method.setInstance(instance);
					String actual = method.invokeToString();

					// Get captured output then compare
					return actual.contains(String.valueOf(ingredient))
							&& actual.contains(String.valueOf(laborCharge))
							&& actual.contains(String.valueOf(deliveryFee))
							&& actual.contains(cake.toString())
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

	/*
	 * getPriceAfterTax **********
	 */

	public ITestCase checkGetPriceAfterTaxDeclaration(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className,
				new MethodTesting(double.class, MethodName.GET_PRICE_AFTER_TAX));
	}

	public ITestCase checkGetPriceAfterTaxOperation(int points, double laborCharge, double deliveryFee, double priceAfterTax) throws ClassNotFoundException {
		MethodTesting method = new MethodTesting(double.class, MethodName.GET_PRICE_AFTER_TAX);

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
					
					Object cake = CakeTester.initObject(CustomerTester.initObject("Lao Hac"), EventTester.initObject(0), 2, 2.55);
					method.setInstance(initObject(cake, laborCharge, deliveryFee));
					
					return method.boxingReturnedType().cast(method.returning()).equals(priceAfterTax);
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
