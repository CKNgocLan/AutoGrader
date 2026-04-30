package student.testSuite.lab2.problem3;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
import student.model.Method;
import student.model.MethodTesting;
import student.model.Parameter;
import student.testSuite.lab2.CustomerTester;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;
import student.util.SetterUtils;

public class PetShopTester {
	private static PetShopTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private java.lang.ClassLoader targetClassesLoader = ClassLoader.getInstance();
	private static String className = ClassName.PET_SHOP;
	private static Class<?> clazz;

	/*
	 * Instance **********
	 */

	public static PetShopTester getInstance() {
		if (instance == null) {
			instance = new PetShopTester();
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
		return fieldTester.checkDeclarations(points, className,
				new FieldTesting(List.class, student.model.ClassLoader.retrieveClass(ClassName.CUSTOMER),
						FieldName.CUSTOMERS),
				new FieldTesting(List.class, student.model.ClassLoader.retrieveClass(ClassName.PET), FieldName.PETS),
				new FieldTesting(List.class, student.model.ClassLoader.retrieveClass(ClassName.SERVICE_ESTIMATE),
						FieldName.SERVICE_ESTIMATES));
	}

	/*
	 * Constructor **********
	 */

	public ITestCase checkNoArgsConstructors(int points) throws ClassNotFoundException {
		return classTester.checkNoArgConstructorDeclaration(points, className);
	}

	/*
	 * Getter **********
	 */
	public ITestCase checkGetterDeclaration(int points) {
		return methodTester.checkGetterDeclaration(points, className);
	}

	/*
	 * Setter **********
	 */
	public ITestCase checkSetterDeclaration(int points) {
		return methodTester.checkSetterDeclaration(points, className);
	}

	/*
	 * addCustomer **********
	 */

	public ITestCase checkAddCustomerDeclaration(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className,
				new Method(ClassLoader.retrieveClass(ClassName.CUSTOMER), MethodName.ADD_CUSTOMER,
						new Parameter(FieldName.CUSTOMERS, ClassLoader.retrieveClass(ClassName.CUSTOMER))));
	}

	public ITestCase checkAddCustomerOperation(int points) throws ClassNotFoundException {
		MethodTesting method = new MethodTesting(ClassLoader.retrieveClass(ClassName.CUSTOMER),
				MethodName.ADD_CUSTOMER);

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
					getCorrespondingClass().getMethod(method.getName(), method.getReturnedType()).invoke(initObject(),
							CustomerTester.initObject());

					return true;
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
	 * showAllCustomers **********
	 */

	public ITestCase checkShowAllCustomersDeclaration(int points) {
		return methodTester.checkExistence(points, className, new Method(void.class, MethodName.SHOW_ALL_CUSTOMERS));
	}

	public ITestCase checkShowAllCustomersOperation(int points) {
		String customerName = "Alice";
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_METHOD_OPERATION.getName(className, MethodName.SHOW_ALL_CUSTOMERS);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					// Save original System.out
					PrintStream originalOut = System.out;
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					PrintStream testOut = new PrintStream(outputStream);

					// Redirect System.out to capture output
					System.setOut(testOut);

					// Prepare test data
					Object petShopInstance = initObject();

					Object customerInstance = CustomerTester.initObject();
					CustomerTester.getCorrespondingClass()
							.getMethod(SetterUtils.getSetterName(FieldName.NAME), String.class)
							.invoke(customerInstance, customerName);

					clazz.getMethod(MethodName.ADD_CUSTOMER, CustomerTester.getCorrespondingClass())
							.invoke(petShopInstance, customerInstance);

					clazz.getMethod(MethodName.SHOW_ALL_CUSTOMERS).invoke(petShopInstance);

					// Restore original System.out
					System.setOut(originalOut);

					// Get captured output then compare
					return outputStream.toString().trim().contains(customerName);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(className, MethodName.SHOW_ALL_CUSTOMERS);
			}
		};
	}

	/*
	 * addPet **********
	 */

	public ITestCase checkAddPetDeclaration(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className, new Method(void.class,
				MethodName.ADD_PET, new Parameter(FieldName.PETS, PetTester.getCorrespondingClass())));
	}

	public ITestCase checkAddPetOperation(int points) throws ClassNotFoundException {
		MethodTesting method = new MethodTesting(ClassLoader.retrieveClass(ClassName.PET), MethodName.ADD_PET);

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
					getCorrespondingClass();

					clazz.getMethod(method.getName(), method.getReturnedType()).invoke(
							clazz.getDeclaredConstructor().newInstance(),
							PetTester.initObject(CustomerTester.initObject()));

					return true;
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
	 * showAllPets **********
	 */

	public ITestCase checkShowAllPetsDeclaration(int points) {
		return methodTester.checkExistence(points, className, new Method(void.class, MethodName.SHOW_ALL_PETS));
	}

	public ITestCase checkShowAllPetsOperation(int points) throws ClassNotFoundException {
		MethodTesting method = new MethodTesting(PetTester.getCorrespondingClass(), MethodName.SHOW_ALL_PETS);
		final String customerName = "Old Man Hac";

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
					// Save original System.out
					PrintStream originalOut = System.out;
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					PrintStream testOut = new PrintStream(outputStream);

					// Redirect System.out to capture output
					System.setOut(testOut);

					// Prepare test data
					Object petShopInstance = initObject();

					Object petInstance = PetTester.initObject(CustomerTester.initObject(customerName));

					clazz.getMethod(MethodName.ADD_PET, PetTester.getCorrespondingClass()).invoke(petShopInstance, petInstance);
					clazz.getMethod(method.getName()).invoke(petShopInstance);

					// Restore original System.out
					System.setOut(originalOut);

					// Get captured output then compare
					return outputStream.toString().trim().contains(customerName);
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
	 * addServiceEstimate **********
	 */

	public ITestCase checkAddServiceEstimateDeclaration(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className, new Method(void.class, MethodName.ADD_SERVICE_ESTIMATE,
				new Parameter(FieldName.SERVICE_ESTIMATES, ServiceEstimateTester.getCorrespondingClass())));
	}

	public ITestCase checkAddServiceEstimateOperation(int points) throws ClassNotFoundException {
		MethodTesting method = new MethodTesting(void.class, MethodName.ADD_SERVICE_ESTIMATE);

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
					clazz.getMethod(method.getName(), method.getReturnedType()).invoke(
							clazz.getDeclaredConstructor().newInstance(),
							PetTester.initObject(CustomerTester.initObject()));

					return true;
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
	 * showAllServiceEstimates **********
	 */

	public ITestCase checkShowAllServiceEstimatesDeclaration(int points) {
		return methodTester.checkExistence(points, className,
				new Method(void.class, MethodName.SHOW_ALL_SERVICE_ESTIMATES));
	}

	public ITestCase checkShowAllServiceEstimatesOperation(int points) throws ClassNotFoundException {
		MethodTesting method = new MethodTesting(PetTester.getCorrespondingClass(), MethodName.SHOW_ALL_SERVICE_ESTIMATES);
		final String customerName = "Old Man Hac";

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
					// Save original System.out
					PrintStream originalOut = System.out;
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					PrintStream testOut = new PrintStream(outputStream);

					// Redirect System.out to capture output
					System.setOut(testOut);

					// Prepare test data
					Object petShopInstance = initObject();

					clazz.getMethod(MethodName.ADD_SERVICE_ESTIMATE, ServiceEstimateTester.getCorrespondingClass()).invoke(petShopInstance, ServiceEstimateTester.initObject());
					clazz.getMethod(method.getName()).invoke(petShopInstance);

					// Restore original System.out
					System.setOut(originalOut);

					// Get captured output then compare
//					return outputStream.toString().trim().contains(customerName);
					return true;
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
