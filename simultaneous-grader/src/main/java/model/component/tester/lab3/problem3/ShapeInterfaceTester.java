package model.component.tester.lab3.problem3;

import common.constant.ClassName;
import common.constant.MethodName;
import model.component.TestCase;
import model.component.tester.Tester;
import model.element.TestingMethod;
import model.exception.TesterGotNoClassNameException;

public class ShapeInterfaceTester extends Tester {

	/*
	 * instantiate ***************
	 */

	public ShapeInterfaceTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.SHAPE;
		super.getCorrespondingClass();
	}
	
	/*
	 * define area
	 */
	private TestingMethod defineArea() {
		return new TestingMethod(double.class, MethodName.AREA).asAbstract();
	}
	
	/*
	 * declaration
	 */
	public TestCase declare(int points) {
		return super.declareAsInterface(points);
	}
	
	/*
	 * constructor ***************
	 */

	public TestCase constructor(int points) throws ClassNotFoundException {
		return super.checkConstructorDeclaration(points, String.class, int.class, double.class);
	}
	
	/*
	 * area()
	 */
	
	public TestCase declareArea(int points) {
		return super.methodTester.declaredAsSpecialModifers(points, className, defineArea());
	}
}
