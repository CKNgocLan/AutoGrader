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

public class CylinderTester extends BaseTester {

	public CylinderTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.CYLINDER;
		super.getCorrespondingClass();
	}
	
	public MethodTesting method() {
		return new MethodTesting(double.class, MethodName.AREA);
	}
	
	/*
	 * argument
	 */
	
	public ParameterTesting[] createArgs(double radius, double height) {
		return new ParameterTesting[] {
				new ParameterTesting(double.class, FieldName.RADIUS, radius)
				, new ParameterTesting(double.class, FieldName.HEIGHT, height)
				};
	}

	/*
	 * field ***************
	 */

	public ITestCase checkFields(int points) {
		return super.checkFields(points
				, new FieldTesting(double.class, FieldName.RADIUS)
				, new FieldTesting(double.class, FieldName.HEIGHT)
				);
	}

	/*
	 * constructor ***************
	 */

	public ITestCase checkConstructorDeclaration(int points) throws ClassNotFoundException {
		return super.checkConstructorDeclaration(points, double.class, double.class);
	}
	
	/*
	 * area()
	 */
	
	public ITestCase declareArea(int points) {
		return super.methodTester.checkExistence(points, className, method());
	}

	public ITestCase operateArea(int points, double radius, double height, double expected)
			throws ClassNotFoundException
			, InstantiationException
			, IllegalAccessException
			, IllegalArgumentException
			, InvocationTargetException
			, NoSuchMethodException
			, SecurityException
			, TesterGotNoClassNameException {
		return methodTester.checkOperationAsNumberic(points, method()
				.config(getCorrespondingClass(), instantiateWithArgs(createArgs(radius, height)))
				.expectedValue(expected));
	}
}
