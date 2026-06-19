package model.component.testSuite.concrete.lab3;

import java.util.Arrays;
import java.util.List;

import model.component.TestCase;
import model.component.testSuite.TestSuite;
import model.component.tester.lab3.problem2.ShapeClassTester;

public class Problem2TestSuite extends TestSuite {
	private static Problem2TestSuite instance;

	public static Problem2TestSuite getInstance() {
		if (instance == null) {
			instance = new Problem2TestSuite();
		}
		return instance;
	}

	@Override
	public List<TestCase> getTestCases() {
		try {
			ShapeClassTester shapeClassTester = new ShapeClassTester();
			double radius = 5;
			long width = 5;
			long length = 5;
			double height = 9;

			return Arrays.asList(
					shapeClassTester.declare()
					, shapeClassTester.declareAreaCircle(defaultPoints)
					, shapeClassTester.operateAreaCircle(defaultPoints, radius, Math.PI * Math.pow(radius, 2))
					, shapeClassTester.operateAreaCircle(defaultPoints, Double.MAX_VALUE, Math.PI * Math.pow(Double.MAX_VALUE, 2))
					, shapeClassTester.declareAreaRectangle(defaultPoints)
					, shapeClassTester.operateAreaRectangle(defaultPoints, width, length, width * length)
					, shapeClassTester.operateAreaRectangle(defaultPoints, Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE * Long.MAX_VALUE)
					, shapeClassTester.declareAreaCylinder(defaultPoints)
					, shapeClassTester.operateAreaCylinder(defaultPoints, radius, height, Math.PI * Math.pow(radius, 2) * height)
					, shapeClassTester.operateAreaCylinder(defaultPoints, Double.MAX_VALUE, Double.MAX_VALUE, Math.PI * Math.pow(Double.MAX_VALUE, 2) * Double.MAX_VALUE)
					);
		} catch (Exception e) {
			e.printStackTrace();
			return List.of();
		}
	}

}
