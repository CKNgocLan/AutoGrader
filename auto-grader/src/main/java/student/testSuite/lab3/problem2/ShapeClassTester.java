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
	public ParameterTesting[] circleArgs() {
		return circleArgs(null);
	}

	public ParameterTesting[] circleArgs(Double radius) {
		return new ParameterTesting[] { new ParameterTesting(double.class, FieldName.RADIUS, radius) };
	}

	public ParameterTesting[] rectangleArgs() {
		return rectangleArgs(null, null);
	}

	public ParameterTesting[] rectangleArgs(Long width, Long length) {
		return new ParameterTesting[] { new ParameterTesting(long.class, FieldName.WIDTH, width),
				new ParameterTesting(long.class, FieldName.LENGTH, length) };
	}

	public ParameterTesting[] cylinderArgs() {
		return cylinderArgs(null, null);
	}

	public ParameterTesting[] cylinderArgs(Double radius, Double height) {
		return new ParameterTesting[] { new ParameterTesting(double.class, FieldName.RADIUS, radius),
				new ParameterTesting(double.class, FieldName.HEIGHT, height) };
	}

	public MethodTesting method(ParameterTesting... args) {
		return new MethodTesting(double.class, MethodName.AREA, args);
	}

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
		return methodTester.checkExistence(points, className, method(circleArgs()).asStatic());
	}

	public ITestCase operateAreaCircle(int points, double radius, Object expected)
			throws ClassNotFoundException
			, TesterGotNoClassNameException
			, InstantiationException
			, IllegalAccessException
			, IllegalArgumentException
			, InvocationTargetException
			, NoSuchMethodException
			, SecurityException {
		return methodTester.checkOperationAsNumberic(points,
				method(circleArgs(radius)).config(getCorrespondingClass(), instantiate()).expectedValue(expected));
	}

	public ITestCase declareAreaRectangle(int points) {
		return methodTester.checkExistence(points, className, method(rectangleArgs()).asStatic());
	}

	public ITestCase operateAreaRectangle(int points, long width, long length, Object expected)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, TesterGotNoClassNameException {
		return methodTester.checkOperationAsNumberic(points, method(rectangleArgs(width, length))
				.config(getCorrespondingClass(), instantiate()).expectedValue(expected));
	}

	public ITestCase declareAreaCylinder(int points) {
		return methodTester.checkExistence(points, className, method(cylinderArgs()).asStatic());
	}

	public ITestCase operateAreaCylinder(int points, double radius, double height, Object expected)
			throws ClassNotFoundException, TesterGotNoClassNameException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException {
		return methodTester.checkOperationAsNumberic(points, method(cylinderArgs(radius, height))
				.config(getCorrespondingClass(), instantiate()).expectedValue(expected));
	}
}
