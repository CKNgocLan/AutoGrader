package student.testSuite.lab2.problem3;

import java.util.List;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.model.ClassLoader;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.Method;
import student.model.Parameter;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;

public class PetShopTester {
	private static PetShopTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private java.lang.ClassLoader targetClassesLoader = ClassLoader.getInstance();
	private String className = ClassName.PET_SHOP;

	/*
	 * instance **********
	 */

	public static PetShopTester getInstance() {
		if (instance == null) {
			instance = new PetShopTester();
		}

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

	/*
	 * addPet **********
	 */

	public ITestCase checkAddPetExistence(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className, new Method(ClassLoader.retrieveClass(ClassName.PET),
				MethodName.ADD_PET, new Parameter(FieldName.PETS, ClassLoader.retrieveClass(ClassName.PET))));
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
	 * showAllCustomers **********
	 */

	public ITestCase checkShowAllCustomersExistence(int points) {
		return methodTester.checkExistence(points, className, new Method(void.class, MethodName.SHOW_ALL_CUSTOMERS));
	}

	/*
	 * showAllPets **********
	 */

	public ITestCase checkShowAllPetsExistence(int points) {
		return methodTester.checkExistence(points, className, new Method(void.class, MethodName.SHOW_ALL_PETS));
	}

	/*
	 * showAllServiceEstimates **********
	 */

	public ITestCase checkShowAllServiceEstimatesExistence(int points) {
		return methodTester.checkExistence(points, className,
				new Method(void.class, MethodName.SHOW_ALL_SERVICE_ESTIMATES));
	}

}
