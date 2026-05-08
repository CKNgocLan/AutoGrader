package student.testSuite.lab3.problem3;

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

public class CircleTester extends BaseTester {

	public CircleTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.CIRCLE;
		super.getCorrespondingClass();
	}
	
	public MethodTesting method() {
		return new MethodTesting(double.class, MethodName.AREA);
	}
	
	/*
	 * argument
	 */
	
	public ParameterTesting[] createArgs(double radius) {
		return new ParameterTesting[] { new ParameterTesting(double.class, FieldName.RADIUS, radius) };
	}
	
	/*
	 * declare
	 */
	
	public ITestCase implementShape(int points) {
		return super.classTester.checkImplementingInterface(points, className, ClassName.SHAPE);
	}

	/*
	 * field ***************
	 */

	public ITestCase checkFields(int points) {
		return super.checkFields(points, new FieldTesting(double.class, FieldName.RADIUS));
	}

	/*
	 * constructor ***************
	 */

	public ITestCase constructor(int points) throws ClassNotFoundException {
		return super.checkConstructorDeclaration(points, double.class);
	}
	
	/*
	 * area()
	 */
	
	public ITestCase declareArea(int points) {
		return super.methodTester.checkExistence(points, className, method());
	}

	public ITestCase operateArea(int points, double radius, double expected)
			throws ClassNotFoundException
			, InstantiationException
			, IllegalAccessException
			, IllegalArgumentException
			, InvocationTargetException
			, NoSuchMethodException
			, SecurityException
			, TesterGotNoClassNameException {
		return methodTester.checkOperationAsNumberic(points,
				method().config(getCorrespondingClass(), instantiateWithArgs(createArgs(radius)))
						.expectedValue(expected));
	}
}
