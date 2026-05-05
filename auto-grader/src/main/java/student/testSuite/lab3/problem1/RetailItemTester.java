package student.testSuite.lab3.problem1;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.testSuite.BaseTester;

public class RetailItemTester extends BaseTester {

	/*
	 * instantiate ***************
	 */

	public RetailItemTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.RETAIL_ITEM;
		super.getCorrespondingClass();
	}

	/*
	 * field ***************
	 */

	public ITestCase checkFields(int points) {
		return super.checkFields(points, new FieldTesting(String.class, FieldName.DESCRIPTION),
				new FieldTesting(int.class, FieldName.UNITS_ON_HAND), new FieldTesting(double.class, FieldName.PRICE));
	}

	/*
	 * constructor ***************
	 */

	public ITestCase checkConstructorDeclaration(int points) throws ClassNotFoundException {
		return super.checkConstructorDeclaration(points, String.class, int.class, double.class);
	}

	public ITestCase checkConstructorOperation(int points, ParameterTesting... retailItemArgs)
			throws ClassNotFoundException {
		return super.checkConstructorOperation(points, retailItemArgs);
	}
}
