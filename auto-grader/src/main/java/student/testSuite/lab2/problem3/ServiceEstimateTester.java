package student.testSuite.lab2.problem3;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.Method;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;

public class ServiceEstimateTester {
	private static ServiceEstimateTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private String className = ClassName.SERVICE_ESTIMATE;

	/*
	 * instance **********
	 */

	public static ServiceEstimateTester getInstance() {
		if (instance == null) {
			instance = new ServiceEstimateTester();
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
		return fieldTester.checkDeclarations(points, className, new FieldTesting(double.class, FieldName.GROOMING_COST),
				new FieldTesting(double.class, FieldName.ADDITIONAL_CARE_COST),
				new FieldTesting(double.class, FieldName.TAX),
				new FieldTesting(student.model.ClassLoader.retrieveClass(ClassName.PET), FieldName.PET));
	}

	/*
	 * Constructor **********
	 */

	public ITestCase checkNoArgsConstructors(int points) throws ClassNotFoundException {
		return classTester.checkNoArgConstructorDeclaration(points, className);
	}

	/*
	 * toString **********
	 */

	public ITestCase checkToStringExistence(int points) {
		return methodTester.checkExistence(points, className, new Method(String.class, MethodName.TO_STRING));
	}

	/*
	 * getTotalCostAfterTax **********
	 */

	public ITestCase checkShowAllServiceEstimatesExistence(int points) {
		return methodTester.checkExistence(points, className,
				new Method(double.class, MethodName.GET_TOTAL_COST_AFTER_TAX));
	}
}
