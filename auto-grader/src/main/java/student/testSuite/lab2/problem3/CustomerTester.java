package student.testSuite.lab2.problem3;

import student.constant.ClassName;
import student.model.ITestCase;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;

public class CustomerTester {
	private static CustomerTester instance = null;
	private ClassTestcaseCreator classTest = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private ClassLoader targetClassesLoader = student.model.ClassLoader.getInstance();
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
		return classTest.checkExistence(className, points);
	}

	/*
	 * Fields ***************************************************************************
	 */

}
