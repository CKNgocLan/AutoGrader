package student.testSuite.lab3.problem1;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestingField;
import student.model.ITestCase;
import student.model.TestingParameter;
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
	 * argument
	 */
	
	public TestingParameter[] createArgs(String description, int unitsOnHand, double price) {
		return new TestingParameter[] {
				new TestingParameter(String.class, FieldName.DESCRIPTION, description),
				new TestingParameter(int.class, FieldName.UNITS_ON_HAND, unitsOnHand),
				new TestingParameter(double.class, FieldName.PRICE, price) };
	}

	public Object instantiate(TestingParameter[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, TesterGotNoClassNameException {
		return super.instantiateWithArgs(args);
	}
	
	/*
	 * declare
	 */

	public ITestCase declare() {
		return super.declare(defaultPoints);
	}

	/*
	 * field ***************
	 */

	public ITestCase checkFields(int points) {
		return super.checkFields(points, new TestingField(String.class, FieldName.DESCRIPTION),
				new TestingField(int.class, FieldName.UNITS_ON_HAND), new TestingField(double.class, FieldName.PRICE));
	}

	/*
	 * constructor ***************
	 */

	public ITestCase checkConstructorDeclaration(int points) throws ClassNotFoundException {
		return super.checkConstructorDeclaration(points, String.class, int.class, double.class);
	}

	public ITestCase checkConstructorOperation(int points, TestingParameter... retailItemArgs)
			throws ClassNotFoundException {
		return super.checkConstructorOperation(points, retailItemArgs);
	}
}
