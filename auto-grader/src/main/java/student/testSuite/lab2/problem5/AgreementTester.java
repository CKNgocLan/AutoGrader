package student.testSuite.lab2.problem5;

import student.constant.ClassName;
import student.constant.FieldName;
import student.model.ClassLoader;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;

public class AgreementTester {
	private static AgreementTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
    private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private String className = ClassName.AGREEMENT;

	/*
	 * instance ***************************************************************************
	 */

	public static AgreementTester getInstance() {
		if (instance == null) {
			instance = new AgreementTester();
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
	 * Fields ***************************************************************************
	 */
	
	public ITestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarations(points, className
				, new FieldTesting(String.class, FieldName.PURPOSE)
				, new FieldTesting(double.class, FieldName.BASE_RENTAL_FEE)
				, new FieldTesting(double.class, FieldName.MILEAGE_FEE)
				, new FieldTesting(ClassLoader.retrieveClass(ClassName.CAR), FieldName.CAR)
		);
	}
}
