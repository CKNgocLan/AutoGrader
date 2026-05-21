package student.testSuite.labTestSuite;

import java.util.Arrays;
import java.util.List;

import student.constant.FieldName;
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
import student.testSuite.lab3.problem4.ConstTester;
import student.testSuite.lab3.problem4.FuelGaugeTester;
import student.testSuite.lab3.problem4.OdometerTester;

public class LabTestSuite3 extends ALabTestSuite {

	@Override
	public List<ITestCase> getAllTests(String question) {
		int defaultPoints = 5;
		try {

			switch (question) {
			case Question.Q1:
				{
					RetailItemTester retailItemTester = new RetailItemTester();
					CashRegisterTester cashRegisterTester = new CashRegisterTester(retailItemTester);
					
					double price = 25.9;
					ParameterTesting[] retailItemArgs = retailItemTester
							.createArgs("DESCRIPTION", 15, price);
					
					int quantity = 3;
					ParameterTesting[] cashRegisterArgs = cashRegisterTester
							.createArgs(retailItemTester.instantiateWithArgs(retailItemArgs), quantity);

					double tax_rate = 0.06;
					
					double subtotal = price * quantity;
					double totalTax = subtotal * tax_rate;
					double total = subtotal + totalTax;

					return Arrays.asList(
							// retail item
							retailItemTester.declare(defaultPoints)
							, retailItemTester.checkFields(defaultPoints)
							, retailItemTester.checkConstructorDeclaration(defaultPoints)
							, retailItemTester.checkConstructorOperation(defaultPoints, retailItemArgs)
							, retailItemTester.checkGetterDeclaration(defaultPoints)
							, retailItemTester.checkSetterDeclaration(defaultPoints)

							// cash register
							, cashRegisterTester.declare(defaultPoints)
							, cashRegisterTester.checkFields(defaultPoints)
							, cashRegisterTester.checkConstructorDeclaration(defaultPoints)
							, cashRegisterTester.checkConstructorOperation(defaultPoints, cashRegisterArgs)
							, cashRegisterTester.checkGetterDeclaration(defaultPoints)
							, cashRegisterTester.checkSetterDeclaration(defaultPoints)
							, cashRegisterTester.declareGetSubtotal(defaultPoints)
							, cashRegisterTester.operateGetSubtotal(defaultPoints, subtotal, cashRegisterArgs)
							, cashRegisterTester.declareGetTax(defaultPoints)
							, cashRegisterTester.operateGetTax(defaultPoints, totalTax, cashRegisterArgs)
							, cashRegisterTester.declareGetTotal(defaultPoints)
							, cashRegisterTester.operateGetTotal(defaultPoints, total, cashRegisterArgs)
							);
				}
			case Question.Q2:
				{
					ShapeClassTester shapeClassTester = new ShapeClassTester();
					double radius = 5;
					long width = 5;
					long length = 5;
					double height = 9;

					return Arrays.asList(
							shapeClassTester.declare(defaultPoints)
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
				}
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
			{
				ConstTester constTester = new ConstTester();
				FuelGaugeTester fuelGaugeTester = new FuelGaugeTester();
				OdometerTester odometerTester = new OdometerTester(fuelGaugeTester);
				
				int gallon = 9;
				
//				Object fuelGaugeInstance = fuelGaugeTester.instantiate(gallon); 

				return Arrays.asList(
						// const
						constTester.declare(defaultPoints)
						, constTester.fields(defaultPoints)

						//fuelGauge
						, fuelGaugeTester.declare(defaultPoints)
						, fuelGaugeTester.fields(defaultPoints)
						, fuelGaugeTester.declareConstructor(defaultPoints)
						, fuelGaugeTester.operateConstructor(defaultPoints, gallon)

						// odometer
						, odometerTester.declare(defaultPoints)
						, odometerTester.fields(defaultPoints)
						, odometerTester.declareConstructor(defaultPoints)
						, odometerTester.operateConstructor(defaultPoints, gallon)
						, odometerTester.declareIncrementMileage(defaultPoints)
						, odometerTester.operateIncrementMileage(defaultPoints, 1)
						, odometerTester.declareGetMileage(defaultPoints)
						);
			}
			case Question.Q5:

				return Arrays.asList();
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