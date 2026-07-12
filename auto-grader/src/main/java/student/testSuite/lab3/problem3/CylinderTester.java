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

public class CylinderTester extends BaseTester {

	public CylinderTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.CYLINDER;
		super.getCorrespondingClass();
	}
	
	public TestingMethod method() {
		return new TestingMethod(double.class, MethodName.AREA);
	}
	
	/*
	 * argument
	 */
	
	public TestingParameter[] createArgs(double radius, double height) {
		return new TestingParameter[] {
				new TestingParameter(double.class, FieldName.RADIUS, radius)
				, new TestingParameter(double.class, FieldName.HEIGHT, height)
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
				, new TestingField(double.class, FieldName.RADIUS)
				, new TestingField(double.class, FieldName.HEIGHT)
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

	public TestCase operateArea(int points, double radius, double height, double expected) throws Exception {
		return methodTester.checkOperationAsNumberic(points, method()
				.config(getCorrespondingClass(), instantiateWithArgs(createArgs(radius, height)))
				.expectedValue(expected));
	}
}
