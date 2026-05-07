package student.testSuite.lab3.problem2;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.ITestCase;
import student.model.MethodTesting;
import student.model.Parameter;
import student.model.ParameterTesting;
import student.testSuite.BaseTester;

public class ShapeClassTester extends BaseTester {

	/*
	 * instantiate ***************
	 */

	public ShapeClassTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.SHAPE;
		super.getCorrespondingClass();
	}

	/*
	 * area ***************
	 */
	
	public ITestCase declareAreaCircle(int points) {
		return methodTester.checkExistence(points, className,
				new MethodTesting(double.class, MethodName.AREA, new Parameter(FieldName.RADIUS, double.class)).asStatic());
	}
	
	public ITestCase operateAreaCircle(int points, double radius, Object expected) throws ClassNotFoundException, TesterGotNoClassNameException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return methodTester.checkOperationAsNumberic(points, new MethodTesting(double.class, MethodName.AREA,
						new ParameterTesting(FieldName.RADIUS, double.class, radius))
				.config(getCorrespondingClass(), instantiate())
				.expectedValue(expected));
	}

	public ITestCase declareAreaRectangle(int points) {
		return methodTester.checkExistence(points, className, new MethodTesting(double.class, MethodName.AREA,
				new Parameter(FieldName.WIDTH, long.class), new Parameter(FieldName.LENGTH, long.class)).asStatic());
	}
	
	public ITestCase operateAreaRectangle(int points, long width, long length, Object expected) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, TesterGotNoClassNameException {
		return methodTester.checkOperationAsNumberic(points, new MethodTesting(double.class, MethodName.AREA
						, new ParameterTesting(FieldName.WIDTH, long.class, width)
						, new ParameterTesting(FieldName.LENGTH, long.class, length))
				.config(getCorrespondingClass(), instantiate())
				.expectedValue(expected));
	}

	public ITestCase declareAreaCylinder(int points) {
		return methodTester.checkExistence(points, className, new MethodTesting(double.class, MethodName.AREA,
				new Parameter(FieldName.RADIUS, double.class), new Parameter(FieldName.HEIGHT, double.class)).asStatic());
	}
	
	public ITestCase operateAreaCylinder(int points, double radius, double height, Object expected) throws ClassNotFoundException, TesterGotNoClassNameException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return methodTester.checkOperationAsNumberic(points, new MethodTesting(double.class, MethodName.AREA
						, new ParameterTesting(FieldName.RADIUS, double.class, radius)
						, new ParameterTesting(FieldName.HEIGHT, double.class, height))
				.config(getCorrespondingClass(), instantiate())
				.expectedValue(expected));
	}
}
