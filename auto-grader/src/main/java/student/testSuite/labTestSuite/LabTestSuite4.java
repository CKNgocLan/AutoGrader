package student.testSuite.labTestSuite;

import java.util.Arrays;
import java.util.List;

import student.constant.Constants;
import student.constant.Question;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.testSuite.lab3.problem1.CashRegisterTester;
import student.testSuite.lab3.problem1.RetailItemTester;
import student.testSuite.lab3.problem2.ShapeClassTester;
import student.testSuite.lab3.problem3.CircleTester;
import student.testSuite.lab3.problem3.CylinderTester;
import student.testSuite.lab3.problem3.RectangleTester;
import student.testSuite.lab3.problem3.ShapeInterfaceTester;
import student.testSuite.lab3.problem4.FuelGaugeTester;
import student.testSuite.lab3.problem4.OdometerTester;
import student.testSuite.lab3.problem5.ParkedCarTester;
import student.testSuite.lab3.problem5.ParkingMeterTester;
import student.testSuite.lab3.problem5.ParkingTicketTester;
import student.testSuite.lab3.problem5.PoliceOfficerTester;

public class LabTestSuite4 extends ALabTestSuite {

	@Override
	public List<ITestCase> getAllTests(String question) {
		int defaultPoints = 5;
		try {

			switch (question) {
			case Question.Q1:
			case Question.Q2:
			case Question.Q3:
			{
				ShapeInterfaceTester shapeInterfaceTester = new ShapeInterfaceTester();
				CircleTester circleTester = new CircleTester();
				RectangleTester rectangleTester = new RectangleTester();
				CylinderTester cylinderTester = new CylinderTester();
				
				double radius = 5;
				long width = 4;
				long length = 5;
				double height = 10;

				return Arrays.asList(
						// shape
						shapeInterfaceTester.declare(defaultPoints)
						, shapeInterfaceTester.declareArea(defaultPoints)

						// circle
						, circleTester.declare(defaultPoints)
						, circleTester.implementShape(defaultPoints)
						, circleTester.constructor(defaultPoints)
						, circleTester.checkFields(defaultPoints)
						, circleTester.declareArea(defaultPoints)
						, circleTester.operateArea(defaultPoints, radius, Math.PI * Math.pow(radius, 2))

						// rectangle
						, rectangleTester.declare(defaultPoints)
						, rectangleTester.implementShape(defaultPoints)
						, rectangleTester.constructor(defaultPoints)
						, rectangleTester.checkFields(defaultPoints)
						, rectangleTester.declareArea(defaultPoints)
						, rectangleTester.operateArea(defaultPoints, width, length, width * length)

						// cylinder
						, cylinderTester.declare(defaultPoints)
						, cylinderTester.implementShape(defaultPoints)
						, cylinderTester.constructor(defaultPoints)
						, cylinderTester.checkFields(defaultPoints)
						, cylinderTester.declareArea(defaultPoints)
						, cylinderTester.operateArea(defaultPoints, radius, height, Math.PI * Math.pow(radius, 2) * height)
						);
			}
			case Question.Q4:
			{}
			default:
				return null;
			}
		} catch (Exception e) {
			System.out.println("Class Not Found: %s".formatted(e.getMessage()));
			for (StackTraceElement st : e.getStackTrace()) {
				System.out.println(st);
			}

			return null;
		}
	}
}