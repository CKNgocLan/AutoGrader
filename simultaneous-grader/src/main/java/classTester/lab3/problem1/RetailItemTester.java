package classTester.lab3.problem1;

import java.lang.reflect.InvocationTargetException;

import classTester.ClassTester;
import common.constant.ClassName;
import common.constant.FieldName;
import model.component.TestCase;
import model.element.TestingField;
import model.element.TestingParameter;
import model.exception.TesterGotNoClassNameException;

public class RetailItemTester extends ClassTester {

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

	public TestCase declare() {
		return super.declare(defaultPoints);
	}

	/*
	 * field ***************
	 */

	public TestCase checkFields(int points) {
		return super.checkFields(points, new TestingField(String.class, FieldName.DESCRIPTION),
				new TestingField(int.class, FieldName.UNITS_ON_HAND), new TestingField(double.class, FieldName.PRICE));
	}

	/*
	 * constructor ***************
	 */

	public TestCase checkConstructorDeclaration(int points) throws ClassNotFoundException {
		return super.checkConstructorDeclaration(points, String.class, int.class, double.class);
	}

	public TestCase checkConstructorOperation(int points, TestingParameter... retailItemArgs)
			throws ClassNotFoundException {
		return super.checkConstructorOperation(points, retailItemArgs);
	}
}
