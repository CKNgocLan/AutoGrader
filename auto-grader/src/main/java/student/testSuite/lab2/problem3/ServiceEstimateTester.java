package student.testSuite.lab2.problem3;

import student.constant.ClassName;
import student.constant.FieldName;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;

public class ServiceEstimateTester {
	private static ServiceEstimateTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private String className = ClassName.SERVICE_ESTIMATE;

	/*
	 * instance ***************************************************************************
	 */

	public static ServiceEstimateTester getInstance() {
		if (instance == null) {
			instance = new ServiceEstimateTester();
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
	 * Field ***************************************************************************
	 */
	
	public ITestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarations(className, points,
				new FieldTesting(double.class, FieldName.GROOMING_COST)
				, new FieldTesting(double.class, FieldName.ADDITIONAL_CARE_COST)
				, new FieldTesting(double.class, FieldName.TAX)
				, new FieldTesting(student.model.ClassLoader.retrieveClass(ClassName.PET), FieldName.PET)
		);
	}

	/*
	 * Constructor ***************************************************************************
	 */
	
	public ITestCase checkNoArgsConstructors(int points) throws ClassNotFoundException {
		return classTester.checkNoArgConstructorDeclaration(className, points);
	}
}
