package student.testSuite.lab2.problem4;

import student.constant.ClassName;
import student.constant.FieldName;
import student.model.ClassLoader;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;

public class CakeTester {
	private static CakeTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
    private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private String className = ClassName.CAKE;

	/*
	 * Instance ***************************************************************************
	 */
	public static CakeTester getInstance() {
		if (instance == null) {
			instance = new CakeTester();
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
				, new FieldTesting(int.class, FieldName.TIER_NUMBER)
				, new FieldTesting(int.class, FieldName.MILEAGE_LIMIT)
				, new FieldTesting(ClassLoader.retrieveClass(ClassName.CUSTOMER), FieldName.CUSTOMER)
		);
	}

    /*
     * Getter ***************************************************************************
     */
    public ITestCase checkGetterDeclaration(int points) {
        return methodTester.checkGetterDeclaration(points, className);
    }

    /*
     * Setter ***************************************************************************
     */
    public ITestCase checkSetterDeclaration(int points) {
        return methodTester.checkSetterDeclaration(points, className);
    }
}
