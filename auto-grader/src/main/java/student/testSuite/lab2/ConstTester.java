package student.testSuite.lab2;

import student.constant.ClassName;
import student.constant.FieldName;
import student.model.TestingField;
import student.model.TestCase;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;

public class ConstTester {
	private static ConstTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private String className = ClassName.CONST;

	/*
	 * instance ***************
	 */

	public static ConstTester getInstance() {
		if (instance == null) {
			instance = new ConstTester();
		}

		return instance;
	}

	/*
	 * declaration ***************
	 */

	public TestCase checkDeclaration(int points) {
		return classTester.checkExistence(points, className);
	}

	/*
	 * Fields ***************
	 */
	
	public TestCase checkFields(int points) {
		return fieldTester.checkDeclarationsAsPublicStaticFinal(points, className
				, new TestingField(double.class, FieldName.UPPERCASE_TAX)
		);
	}
	
	/*
	 * Class
	 */
}
