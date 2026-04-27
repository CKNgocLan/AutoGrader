package student.testSuite.lab2.problem3;

import student.constant.ClassName;
import student.constant.FieldName;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;

public class PetTester {
	private static PetTester instance = null;
	private ClassTestcaseCreator classTest = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private String className = ClassName.PET;

	/*
	 * instance ***************************************************************************
	 */

	public static PetTester getInstance() {
		if (instance == null) {
			instance = new PetTester();
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
	
	public ITestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarations(className, points,
				new FieldTesting(String.class, FieldName.BREED)
				, new FieldTesting(int.class, FieldName.AGE)
				, new FieldTesting(double.class, FieldName.WEIGHT)
				, new FieldTesting(student.model.ClassLoader.retrieveClass(ClassName.CUSTOMER), FieldName.CUSTOMER)
		);
	}
}
