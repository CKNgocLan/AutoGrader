package student.testSuite.lab3.problem2;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.ITestCase;
import student.model.MethodTesting;
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
				new MethodTesting(double.class, MethodName.AREA, new ParameterTesting(double.class, FieldName.RADIUS)).asStatic());
	}
	
	public ITestCase operateAreaCircle(int points, double radius, Object expected) throws ClassNotFoundException, TesterGotNoClassNameException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return methodTester.checkOperationAsNumberic(points, new MethodTesting(double.class, MethodName.AREA,
						new ParameterTesting(double.class, FieldName.RADIUS, radius))
				.config(getCorrespondingClass(), instantiate())
				.expectedValue(expected));
	}

	public ITestCase declareAreaRectangle(int points) {
		return methodTester.checkExistence(points, className, new MethodTesting(double.class, MethodName.AREA,
				new ParameterTesting(long.class, FieldName.WIDTH), new ParameterTesting(long.class, FieldName.LENGTH)).asStatic());
	}
	
	public ITestCase operateAreaRectangle(int points, long width, long length, Object expected) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, TesterGotNoClassNameException {
		return methodTester.checkOperationAsNumberic(points, new MethodTesting(double.class, MethodName.AREA
						, new ParameterTesting(long.class, FieldName.WIDTH, width)
						, new ParameterTesting(long.class, FieldName.LENGTH, length))
				.config(getCorrespondingClass(), instantiate())
				.expectedValue(expected));
	}

	public ITestCase declareAreaCylinder(int points) {
		return methodTester.checkExistence(points, className, new MethodTesting(double.class, MethodName.AREA,
				new ParameterTesting(double.class, FieldName.RADIUS), new ParameterTesting(double.class, FieldName.HEIGHT)).asStatic());
	}
	
	public ITestCase operateAreaCylinder(int points, double radius, double height, Object expected) throws ClassNotFoundException, TesterGotNoClassNameException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return methodTester.checkOperationAsNumberic(points, new MethodTesting(double.class, MethodName.AREA
						, new ParameterTesting(double.class, FieldName.RADIUS, radius)
						, new ParameterTesting(double.class, FieldName.HEIGHT, height))
				.config(getCorrespondingClass(), instantiate())
				.expectedValue(expected));
	}
}
