package student.testSuite.lab3.problem3;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestingField;
import student.model.ITestCase;
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
	
	public ITestCase implementShape(int points) {
		return super.classTester.checkImplementingInterface(points, className, ClassName.SHAPE);
	}

	/*
	 * field ***************
	 */

	public ITestCase checkFields(int points) {
		return super.checkFields(points
				, new TestingField(double.class, FieldName.RADIUS)
				, new TestingField(double.class, FieldName.HEIGHT)
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
