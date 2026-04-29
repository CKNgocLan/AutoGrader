package student.testSuite.lab2.problem3;

import student.constant.ClassName;
import student.constant.FieldName;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;

public class CustomerTester {
	private static CustomerTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private String className = ClassName.CUSTOMER;

	/*
	 * instance ***************************************************************************
	 */

	public static CustomerTester getInstance() {
		if (instance == null) {
			instance = new CustomerTester();
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
	 * Field ***************************************************************************
	 */
	
	public ITestCase checkFields(int points) {
		return fieldTester.checkDeclarations(points, className
				, new FieldTesting(String.class, FieldName.NAME)
				, new FieldTesting(String.class, FieldName.ADDRESS)
				, new FieldTesting(String.class, FieldName.PHONE_NUMBER)
		);
	}

	/*
	 * Constructor ***************************************************************************
	 */
	
	public ITestCase checkNoArgsConstructors(int points) throws ClassNotFoundException {
		return classTester.checkNoArgConstructorDeclaration(points, className);
	}

	/*
	 * Constructor ***************************************************************************
	 */
	
	public ITestCase checkGetterSetter(int points) {
		return methodTester.checkGetterDeclaration(points, className);
	}
}
