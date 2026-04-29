package student.testSuite.lab2.problem3;

import student.constant.ClassName;
import student.constant.FieldName;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.model.ClassLoader;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;

public class PetTester {
	private static PetTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
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
		return classTester.checkExistence(className, points);
	}

	/*
	 * Fields ***************************************************************************
	 */
	
	public ITestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarations(className, points,
				new FieldTesting(String.class, FieldName.BREED)
				, new FieldTesting(int.class, FieldName.AGE)
				, new FieldTesting(double.class, FieldName.WEIGHT)
				, new FieldTesting(ClassLoader.retrieveClass(ClassName.CUSTOMER), FieldName.CUSTOMER)
		);
	}

	/*
	 * Constructor ***************************************************************************
	 */
	
	public ITestCase checkPartialArgsConstructors(int points, ParameterTesting... params) throws ClassNotFoundException {
		return classTester.checkPartialArgsConstructorDeclaration(className, points, params);
	}
}
