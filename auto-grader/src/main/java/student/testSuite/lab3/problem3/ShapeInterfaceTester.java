package student.testSuite.lab3.problem3;

import student.constant.ClassName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.ITestCase;
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
	public ITestCase declare(int points) {
		return super.declareAsInterface(points);
	}
	
	/*
	 * constructor ***************
	 */

	public ITestCase constructor(int points) throws ClassNotFoundException {
		return super.checkConstructorDeclaration(points, String.class, int.class, double.class);
	}
	
	/*
	 * area()
	 */
	
	public ITestCase declareArea(int points) {
		return super.methodTester.declaredAsSpecialModifers(points, className, defineArea());
	}
}
