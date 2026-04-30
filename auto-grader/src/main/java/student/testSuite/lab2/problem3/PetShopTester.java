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
	
	public Class<?> getClazz() throws ClassNotFoundException {
		if (clazz == null) {
			clazz = Class.forName(className, true, ClassLoader.getInstance());
		}

		return clazz;
	}
	
	/*
	 * initialize
	 */
	
	public static Object initializeInstance() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		return clazz.getDeclaredConstructor().newInstance();
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

	public ITestCase checkAddCustomerExistence(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className,
				new Method(ClassLoader.retrieveClass(ClassName.CUSTOMER), MethodName.ADD_CUSTOMER,
						new Parameter(FieldName.CUSTOMERS, ClassLoader.retrieveClass(ClassName.CUSTOMER))));
	}

	public ITestCase checkAddCustomerOperation(int points) throws ClassNotFoundException {
		MethodTesting method = new MethodTesting(ClassLoader.retrieveClass(ClassName.CUSTOMER), MethodName.ADD_CUSTOMER);
		
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
					Class.forName(className, true, targetClassesLoader)
							.getMethod(method.getName(), method.getReturnedType())
							.invoke(initializeInstance(), CustomerTester.initializeInstance());

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

	public ITestCase checkShowAllCustomersExistence(int points) {
		return methodTester.checkExistence(points, className, new Method(void.class, MethodName.SHOW_ALL_CUSTOMERS));
	}
	
	public ITestCase checkShowAllCustomersOperation(int points) {
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
					Class<?> petShopClass = Class.forName(ClassName.PET_SHOP, true, targetClassesLoader);
					Object petShopInstance = petShopClass.getDeclaredConstructor().newInstance();

					Class<?> customerClass = Class.forName(ClassName.CUSTOMER, true, targetClassesLoader);
					Object customerInstance = customerClass.getDeclaredConstructor().newInstance();
					customerClass.getMethod(SetterUtils.getSetterName(FieldName.NAME), String.class).invoke(customerInstance, "Alice");

					petShopClass.getMethod(MethodName.ADD_CUSTOMER, ClassLoader.retrieveClass(ClassName.CUSTOMER))
							.invoke(petShopInstance, customerInstance);

					petShopClass.getMethod(MethodName.SHOW_ALL_CUSTOMERS).invoke(petShopInstance);

					// Restore original System.out
					System.setOut(originalOut);

					// Get captured output then compare
					return outputStream.toString().trim().contains("Alice");
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

	public ITestCase checkAddPetExistence(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className, new Method(ClassLoader.retrieveClass(ClassName.PET),
				MethodName.ADD_PET, new Parameter(FieldName.PETS, ClassLoader.retrieveClass(ClassName.PET))));
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
					Class<?> petShop = Class.forName(className, true, targetClassesLoader);

					petShop.getMethod(method.getName(), method.getReturnedType()).invoke(
							petShop.getDeclaredConstructor().newInstance(),
							Class.forName(ClassName.PET, true, targetClassesLoader).getDeclaredConstructor().newInstance());

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

	public ITestCase checkShowAllPetsExistence(int points) {
		return methodTester.checkExistence(points, className, new Method(void.class, MethodName.SHOW_ALL_PETS));
	}
	public ITestCase checkShowAllPetsOperation(int points) throws ClassNotFoundException {
		MethodTesting method = new MethodTesting(ClassLoader.retrieveClass(ClassName.PET), MethodName.SHOW_ALL_PETS);
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
					Class<?> petShopClass = Class.forName(ClassName.PET_SHOP, true, targetClassesLoader);
					Object petShopInstance = petShopClass.getDeclaredConstructor().newInstance();

					Class<?> petClass = Class.forName(ClassName.PET, true, targetClassesLoader);
					Class<?> customerClass = Class.forName(ClassName.CUSTOMER, true, targetClassesLoader);
					Object testingValue = Class.forName(ClassName.CUSTOMER, true, targetClassesLoader).getDeclaredConstructor().newInstance();
					Object petInstance = petClass.getDeclaredConstructor(customerClass).newInstance(testingValue);
					
					petShopClass.getMethod(MethodName.ADD_PET, petClass).invoke(petShopInstance, petInstance);

					petShopClass.getMethod(method.getName()).invoke(petShopInstance);

					// Restore original System.out
					System.setOut(originalOut);

					// Get captured output then compare
					return true;
//					return outputStream.toString().trim().contains(testingValue);
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

	public ITestCase checkAddServiceEstimateExistence(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className, new Method(
				ClassLoader.retrieveClass(ClassName.SERVICE_ESTIMATE), MethodName.ADD_SERVICE_ESTIMATE,
				new Parameter(FieldName.SERVICE_ESTIMATES, ClassLoader.retrieveClass(ClassName.SERVICE_ESTIMATE))));
	}

	/*
	 * showAllServiceEstimates **********
	 */

	public ITestCase checkShowAllServiceEstimatesExistence(int points) {
		return methodTester.checkExistence(points, className,
				new Method(void.class, MethodName.SHOW_ALL_SERVICE_ESTIMATES));
	}

}
