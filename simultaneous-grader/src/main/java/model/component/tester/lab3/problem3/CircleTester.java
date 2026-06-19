package model.component.tester.lab3.problem3;

import java.lang.reflect.InvocationTargetException;

import common.constant.ClassName;
import common.constant.FieldName;
import common.constant.MethodName;
import model.component.TestCase;
import model.component.tester.Tester;
import model.element.TestingField;
import model.element.TestingMethod;
import model.element.TestingParameter;
import model.exception.TesterGotNoClassNameException;

public class CircleTester extends Tester {

	public CircleTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.CIRCLE;
		super.getCorrespondingClass();
	}
	
	public TestingMethod method() {
		return new TestingMethod(double.class, MethodName.AREA);
	}
	
	/*
	 * argument
	 */
	
	public TestingParameter[] createArgs(double radius) {
		return new TestingParameter[] { new TestingParameter(double.class, FieldName.RADIUS, radius) };
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
		return super.checkFields(points, new TestingField(double.class, FieldName.RADIUS));
	}

	/*
	 * constructor ***************
	 */

	public TestCase constructor(int points) throws ClassNotFoundException {
		return super.checkConstructorDeclaration(points, double.class);
	}
	
	/*
	 * area()
	 */
	
	public TestCase declareArea(int points) {
		return super.methodTester.declare(points, className, method());
	}

	public TestCase operateArea(int points, double radius, double expected) throws Exception {
		return methodTester.checkOperationAsNumberic(points,
				method().config(getCorrespondingClass(), instantiateWithArgs(createArgs(radius)))
						.expectedValue(expected));
	}
}
