package classTester.lab3.problem1;

import java.lang.reflect.InvocationTargetException;

import classTester.ClassTester;
import common.constant.ClassName;
import common.constant.FieldName;
import common.constant.MethodName;
import model.component.TestCase;
import model.element.TestingField;
import model.element.TestingMethod;
import model.element.TestingParameter;
import model.exception.TesterGotNoClassNameException;

public class CashRegisterTester extends ClassTester {
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

	public TestCase checkFields(int points) throws ClassNotFoundException, TesterGotNoClassNameException {
		return super.checkFieldsAsSpecialModifiers(points,
				new TestingField(retailItemTester.getCorrespondingClass(), FieldName.RETAIL_ITEM),
				new TestingField(int.class, FieldName.QUANTITY),
				new TestingField(double.class, FieldName.UPPERCASE_TAX_RATE).asStatic().asFinal());
	}
	
	/*
	 * declare
	 */

	public TestCase declare() {
		return super.declare(defaultPoints);
	}

	/*
	 * constructor ***************
	 */

	public TestCase checkConstructorDeclaration(int points)
			throws ClassNotFoundException, TesterGotNoClassNameException {
		return super.checkConstructorDeclaration(points, this.retailItemTester.getCorrespondingClass(), int.class);
	}
	
	public TestCase checkConstructorOperation(int points, TestingParameter... params) throws ClassNotFoundException {
		return super.checkConstructorOperation(points, params);
	}
	
	/*
	 * getSubtotal
	 */
	
	public TestCase declareGetSubtotal(int points) {
		return super.methodTester.declare(points, className, new TestingMethod(double.class, MethodName.GET_SUBTOTAL));
	}
	
	public TestCase operateGetSubtotal(int points, double expected, TestingParameter[] args)
			throws ClassNotFoundException, TesterGotNoClassNameException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return super.methodTester.checkOperationAsNumberic(points,
				new TestingMethod(double.class, MethodName.GET_SUBTOTAL)
						.config(getCorrespondingClass(), instantiateWithArgs(args)).expectedValue(expected));
	}
	
	/*
	 * getTax
	 */
	
	public TestCase declareGetTax(int points) {
		return super.methodTester.declare(points, className, new TestingMethod(double.class, MethodName.GET_TAX));
	}
	
	public TestCase operateGetTax(int points, double expected, TestingParameter[] args) throws ClassNotFoundException,
			TesterGotNoClassNameException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		return super.methodTester.checkOperationAsNumberic(points,
				new TestingMethod(double.class, MethodName.GET_TAX).config(getCorrespondingClass(),
						instantiateWithArgs(args)).expectedValue(expected));
	}
	
	/*
	 * getTotal
	 */
	
	public TestCase declareGetTotal(int points) {
		return super.methodTester.declare(points, className, new TestingMethod(double.class, MethodName.GET_TOTAL));
	}
	
	public TestCase operateGetTotal(int points, double expected, TestingParameter[] args) throws ClassNotFoundException,
			TesterGotNoClassNameException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		return super.methodTester.checkOperationAsNumberic(points,
				new TestingMethod(double.class, MethodName.GET_TOTAL).config(getCorrespondingClass(),
						instantiateWithArgs(args)).expectedValue(expected));
	}
}
