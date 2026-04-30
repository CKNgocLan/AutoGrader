package student.testSuite.lab2;

import student.constant.ClassName;
import student.constant.FieldName;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;

public class CustomerTester {
	private static CustomerTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
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
		return classTester.checkExistence(className, points);
	}

	/*
	 * Fields ***************************************************************************
	 */
	
	public ITestCase checkFields(int points) {
		return fieldTester.checkDeclarations(className, points,
				new FieldTesting(String.class, FieldName.NAME)
				, new FieldTesting(String.class, FieldName.ADDRESS)
				, new FieldTesting(String.class, FieldName.PHONE_NUMBER)
		);
	}
}
