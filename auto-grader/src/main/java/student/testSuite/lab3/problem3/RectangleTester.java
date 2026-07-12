package student.testSuite.lab3.problem3;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.model.TestingField;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.testSuite.BaseTester;

public class RectangleTester extends BaseTester {

	public RectangleTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.RECTANGLE;
		super.getCorrespondingClass();
	}
	
	public TestingMethod method() {
		return new TestingMethod(double.class, MethodName.AREA);
	}
	
	/*
	 * argument
	 */
	
	public TestingParameter[] createArgs(double width, double length) {
		return new TestingParameter[] {
				new TestingParameter(double.class, FieldName.WIDTH, width)
				, new TestingParameter(double.class, FieldName.LENGTH, length)
				};
	}
	
	/*
	 * declare
	 */
	
	public TestCase declare() {
		return super.declare(defaultPoints);
	}
	
	public TestCase implementShape(int points) {
		return super.classTester.checkImplementingInterface(points, className, ClassName.SHAPE);
	}

	/*
	 * field ***************
	 */

	public TestCase checkFields(int points) {
		return super.checkFields(points
				, new TestingField(double.class, FieldName.WIDTH)
				, new TestingField(double.class, FieldName.LENGTH)
				);
	}

	/*
	 * constructor ***************
	 */

	public TestCase constructor(int points) throws ClassNotFoundException {
		return super.checkConstructorDeclaration(points, double.class, double.class);
	}
	
	/*
	 * area()
	 */
	
	public TestCase declareArea(int points) {
		return super.methodTester.declare(points, className, method());
	}

	public TestCase operateArea(int points, double width, double length, double expected) throws Exception {
		return methodTester.checkOperationAsNumberic(points, method()
				.config(getCorrespondingClass(), instantiateWithArgs(createArgs(width, length)))
				.expectedValue(expected));
	}
}
