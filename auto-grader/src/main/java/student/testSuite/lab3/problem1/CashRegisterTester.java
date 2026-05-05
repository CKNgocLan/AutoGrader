package student.testSuite.lab3.problem1;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.testSuite.BaseTester;

public class CashRegisterTester extends BaseTester {
	private CashRegisterTester instance;
	private RetailItemTester retailItemTester;

	/*
	 * instantiate ***************
	 */

	public CashRegisterTester(RetailItemTester retailItemTester)
			throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.CASH_REGISTER;
		super.getCorrespondingClass();

		this.retailItemTester = retailItemTester;
	}

	/*
	 * field ***************
	 */

	public ITestCase checkFields(int points) throws ClassNotFoundException, TesterGotNoClassNameException {
		return super.checkFieldsAsSpecialModifiers(points,
				new FieldTesting(retailItemTester.getCorrespondingClass(), FieldName.RETAIL_ITEM),
				new FieldTesting(int.class, FieldName.QUANTITY),
				new FieldTesting(double.class, FieldName.UPPERCASE_TAX_RATE).asStatic().asFinal());
	}

	/*
	 * constructor ***************
	 */

	public ITestCase checkConstructorDeclaration(int points)
			throws ClassNotFoundException, TesterGotNoClassNameException {
		return super.checkConstructorDeclaration(points, this.retailItemTester.getCorrespondingClass(), int.class);
	}
	
	public ITestCase checkConstructorOperation(int points, ParameterTesting... params) throws ClassNotFoundException {
		return super.checkConstructorOperation(points, params);
	}
}
