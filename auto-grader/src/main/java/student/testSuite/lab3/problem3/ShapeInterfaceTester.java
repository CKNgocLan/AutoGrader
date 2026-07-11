package student.testSuite.lab3.problem3;

import student.constant.ClassName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.model.TestingMethod;
import student.testSuite.BaseTester;

public class ShapeInterfaceTester extends BaseTester {

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
