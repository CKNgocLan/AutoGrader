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

public class RectangleTester extends BaseTester {

	public RectangleTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.RECTANGLE;
		super.getCorrespondingClass();
	}
	
	public MethodTesting method() {
		return new MethodTesting(double.class, MethodName.AREA);
	}
	
	/*
	 * argument
	 */
	
	public ParameterTesting[] createArgs(double width, double length) {
		return new ParameterTesting[] {
				new ParameterTesting(double.class, FieldName.WIDTH, width)
				, new ParameterTesting(double.class, FieldName.LENGTH, length)
				};
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
		return super.checkFields(points
				, new FieldTesting(double.class, FieldName.WIDTH)
				, new FieldTesting(double.class, FieldName.LENGTH)
				);
	}

	/*
	 * constructor ***************
	 */

	public ITestCase constructor(int points) throws ClassNotFoundException {
		return super.checkConstructorDeclaration(points, double.class, double.class);
	}
	
	/*
	 * area()
	 */
	
	public ITestCase declareArea(int points) {
		return super.methodTester.checkExistence(points, className, method());
	}

	public ITestCase operateArea(int points, double width, double length, double expected)
			throws ClassNotFoundException
			, InstantiationException
			, IllegalAccessException
			, IllegalArgumentException
			, InvocationTargetException
			, NoSuchMethodException
			, SecurityException
			, TesterGotNoClassNameException {
		return methodTester.checkOperationAsNumberic(points, method()
				.config(getCorrespondingClass(), instantiateWithArgs(createArgs(width, length)))
				.expectedValue(expected));
	}
}
