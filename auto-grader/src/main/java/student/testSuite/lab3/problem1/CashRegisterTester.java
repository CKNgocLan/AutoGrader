package student.testSuite.lab3.problem1;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.MethodTesting;
import student.model.ParameterTesting;
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
	
	public ParameterTesting[] createArgs(Object retailItemInstance, int quantity)
			throws ClassNotFoundException, TesterGotNoClassNameException {
		return new ParameterTesting[] {
				new ParameterTesting(retailItemTester.getCorrespondingClass(), FieldName.RETAIL_ITEM, retailItemInstance),
				new ParameterTesting(int.class, FieldName.QUANTITY, quantity) };
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
	
	/*
	 * getSubtotal
	 */
	
	public ITestCase declareGetSubtotal(int points) {
		return super.methodTester.checkExistence(points, className, new MethodTesting(double.class, MethodName.GET_SUBTOTAL));
	}
	
	public ITestCase operateGetSubtotal(int points, double expected, ParameterTesting[] args)
			throws ClassNotFoundException, TesterGotNoClassNameException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return super.methodTester.checkOperationAsNumberic(points,
				new MethodTesting(double.class, MethodName.GET_SUBTOTAL)
						.config(getCorrespondingClass(), instantiateWithArgs(args)).expectedValue(expected));
	}
	
	/*
	 * getTax
	 */
	
	public ITestCase declareGetTax(int points) {
		return super.methodTester.checkExistence(points, className, new MethodTesting(double.class, MethodName.GET_TAX));
	}
	
	public ITestCase operateGetTax(int points, double expected, ParameterTesting[] args) throws ClassNotFoundException,
			TesterGotNoClassNameException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		return super.methodTester.checkOperationAsNumberic(points,
				new MethodTesting(double.class, MethodName.GET_TAX).config(getCorrespondingClass(),
						instantiateWithArgs(args)).expectedValue(expected));
	}
	
	/*
	 * getTotal
	 */
	
	public ITestCase declareGetTotal(int points) {
		return super.methodTester.checkExistence(points, className, new MethodTesting(double.class, MethodName.GET_TOTAL));
	}
	
	public ITestCase operateGetTotal(int points, double expected, ParameterTesting[] args) throws ClassNotFoundException,
			TesterGotNoClassNameException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		return super.methodTester.checkOperationAsNumberic(points,
				new MethodTesting(double.class, MethodName.GET_TOTAL).config(getCorrespondingClass(),
						instantiateWithArgs(args)).expectedValue(expected));
	}
}
