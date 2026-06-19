package tester.lab3.problem2;

import java.lang.reflect.InvocationTargetException;

import common.constant.ClassName;
import common.constant.FieldName;
import common.constant.MethodName;
import model.component.TestCase;
import model.element.TestingMethod;
import model.element.TestingParameter;
import model.exception.TesterGotNoClassNameException;
import tester.Tester;

public class ShapeClassTester extends Tester {

	/*
	 * instantiate ***************
	 */

	public ShapeClassTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.SHAPE;
		super.getCorrespondingClass();
	}
	
	/*
	 * declare
	 */

	public TestCase declare() {
		return super.declare(defaultPoints);
	}

	/*
	 * argument ***************
	 */
	
	public TestingParameter[] circleArgs() {
		return circleArgs(null);
	}

	public TestingParameter[] circleArgs(Double radius) {
		return new TestingParameter[] { new TestingParameter(double.class, FieldName.RADIUS, radius) };
	}

	public TestingParameter[] rectangleArgs() {
		return rectangleArgs(null, null);
	}

	public TestingParameter[] rectangleArgs(Long width, Long length) {
		return new TestingParameter[] { new TestingParameter(long.class, FieldName.WIDTH, width),
				new TestingParameter(long.class, FieldName.LENGTH, length) };
	}

	public TestingParameter[] cylinderArgs() {
		return cylinderArgs(null, null);
	}

	public TestingParameter[] cylinderArgs(Double radius, Double height) {
		return new TestingParameter[] { new TestingParameter(double.class, FieldName.RADIUS, radius),
				new TestingParameter(double.class, FieldName.HEIGHT, height) };
	}

	public TestingMethod method(TestingParameter... args) {
		return new TestingMethod(double.class, MethodName.AREA, args);
	}

	/*
	 * circle ***************
	 */

	public TestCase declareAreaCircle(int points) {
		return methodTester.declaredAsSpecialModifers(points, className, method(circleArgs()).asStatic());
	}

	public TestCase operateAreaCircle(int points, double radius, Object expected) throws ClassNotFoundException,
			TesterGotNoClassNameException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		return methodTester.checkOperationAsNumberic(points,
				method(circleArgs(radius)).config(getCorrespondingClass(), instantiate()).expectedValue(expected));
	}

	/*
	 * rectangle ***************
	 */

	public TestCase declareAreaRectangle(int points) {
		return methodTester.declaredAsSpecialModifers(points, className, method(rectangleArgs()).asStatic());
	}

	public TestCase operateAreaRectangle(int points, long width, long length, Object expected)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, TesterGotNoClassNameException {
		return methodTester.checkOperationAsNumberic(points, method(rectangleArgs(width, length))
				.config(getCorrespondingClass(), instantiate()).expectedValue(expected));
	}

	/*
	 * cylinder ***************
	 */

	public TestCase declareAreaCylinder(int points) {
		return methodTester.declaredAsSpecialModifers(points, className, method(cylinderArgs()).asStatic());
	}

	public TestCase operateAreaCylinder(int points, double radius, double height, Object expected)
			throws ClassNotFoundException, TesterGotNoClassNameException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException {
		return methodTester.checkOperationAsNumberic(points, method(cylinderArgs(radius, height))
				.config(getCorrespondingClass(), instantiate()).expectedValue(expected));
	}
}
