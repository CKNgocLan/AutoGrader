package student.testSuite.lab3.problem1;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestingField;
import student.model.ITestCase;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.testSuite.BaseTester;

public class CashRegisterTester extends BaseTester {
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
	 * argument
	 */
	
	public TestingParameter[] createArgs(Object retailItemInstance, int quantity)
			throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingParameter[] {
				new TestingParameter(retailItemTester.getCorrespondingClass(), FieldName.RETAIL_ITEM, retailItemInstance),
				new TestingParameter(int.class, FieldName.QUANTITY, quantity) };
	}

	/*
	 * field ***************
	 */

	public ITestCase checkFields(int points) throws ClassNotFoundException, TesterGotNoClassNameException {
		return super.checkFieldsAsSpecialModifiers(points,
				new TestingField(retailItemTester.getCorrespondingClass(), FieldName.RETAIL_ITEM),
				new TestingField(int.class, FieldName.QUANTITY),
				new TestingField(double.class, FieldName.UPPERCASE_TAX_RATE).asStatic().asFinal());
	}

	/*
	 * constructor ***************
	 */

	public ITestCase checkConstructorDeclaration(int points)
			throws ClassNotFoundException, TesterGotNoClassNameException {
		return super.checkConstructorDeclaration(points, this.retailItemTester.getCorrespondingClass(), int.class);
	}
	
	public ITestCase checkConstructorOperation(int points, TestingParameter... params) throws ClassNotFoundException {
		return super.checkConstructorOperation(points, params);
	}
	
	/*
	 * getSubtotal
	 */
	
	public ITestCase declareGetSubtotal(int points) {
		return super.methodTester.checkExistence(points, className, new TestingMethod(double.class, MethodName.GET_SUBTOTAL));
	}
	
	public ITestCase operateGetSubtotal(int points, double expected, TestingParameter[] args)
			throws ClassNotFoundException, TesterGotNoClassNameException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return super.methodTester.checkOperationAsNumberic(points,
				new TestingMethod(double.class, MethodName.GET_SUBTOTAL)
						.config(getCorrespondingClass(), instantiateWithArgs(args)).expectedValue(expected));
	}
	
	/*
	 * getTax
	 */
	
	public ITestCase declareGetTax(int points) {
		return super.methodTester.checkExistence(points, className, new TestingMethod(double.class, MethodName.GET_TAX));
	}
	
	public ITestCase operateGetTax(int points, double expected, TestingParameter[] args) throws ClassNotFoundException,
			TesterGotNoClassNameException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		return super.methodTester.checkOperationAsNumberic(points,
				new TestingMethod(double.class, MethodName.GET_TAX).config(getCorrespondingClass(),
						instantiateWithArgs(args)).expectedValue(expected));
	}
	
	/*
	 * getTotal
	 */
	
	public ITestCase declareGetTotal(int points) {
		return super.methodTester.checkExistence(points, className, new TestingMethod(double.class, MethodName.GET_TOTAL));
	}
	
	public ITestCase operateGetTotal(int points, double expected, TestingParameter[] args) throws ClassNotFoundException,
			TesterGotNoClassNameException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		return super.methodTester.checkOperationAsNumberic(points,
				new TestingMethod(double.class, MethodName.GET_TOTAL).config(getCorrespondingClass(),
						instantiateWithArgs(args)).expectedValue(expected));
	}
}
