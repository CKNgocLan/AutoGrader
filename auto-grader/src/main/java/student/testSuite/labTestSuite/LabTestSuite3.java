package student.testSuite.labTestSuite;

import java.util.Arrays;
import java.util.List;

import student.constant.Constants;
import student.constant.Problem;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.model.TestingParameter;
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

public class LabTestSuite3 extends ALabTestSuite {

	@Override
	public List<ITestCase> getAllTests(String question) {
		int defaultPoints = 5;
		try {

			switch (question) {
			case Problem.P1:
				{
					RetailItemTester retailItemTester = new RetailItemTester();
					CashRegisterTester cashRegisterTester = new CashRegisterTester(retailItemTester);
					
					double price = 25.9;
					TestingParameter[] retailItemArgs = retailItemTester
							.createArgs("DESCRIPTION", 15, price);
					
					int quantity = 3;
					TestingParameter[] cashRegisterArgs = cashRegisterTester
							.createArgs(retailItemTester.instantiate(retailItemArgs), quantity);

					double tax_rate = 0.06;
					
					double subtotal = price * quantity;
					double totalTax = subtotal * tax_rate;
					double total = subtotal + totalTax;

					return Arrays.asList(
							// retail item
							retailItemTester.declare()
							, retailItemTester.checkFields(defaultPoints)
							, retailItemTester.checkConstructorDeclaration(defaultPoints)
							, retailItemTester.checkConstructorOperation(defaultPoints, retailItemArgs)
							, retailItemTester.checkGetterDeclaration(defaultPoints)
							, retailItemTester.checkSetterDeclaration(defaultPoints)

							// cash register
							, cashRegisterTester.declare()
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
			case Problem.P2:
				{
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
				}
			case Problem.P3:
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
						, circleTester.declare()
						, circleTester.implementShape(defaultPoints)
						, circleTester.constructor(defaultPoints)
						, circleTester.checkFields(defaultPoints)
						, circleTester.declareArea(defaultPoints)
						, circleTester.operateArea(defaultPoints, radius, Math.PI * Math.pow(radius, 2))

						// rectangle
						, rectangleTester.declare()
						, rectangleTester.implementShape(defaultPoints)
						, rectangleTester.constructor(defaultPoints)
						, rectangleTester.checkFields(defaultPoints)
						, rectangleTester.declareArea(defaultPoints)
						, rectangleTester.operateArea(defaultPoints, width, length, width * length)

						// cylinder
						, cylinderTester.declare()
						, cylinderTester.implementShape(defaultPoints)
						, cylinderTester.constructor(defaultPoints)
						, cylinderTester.checkFields(defaultPoints)
						, cylinderTester.declareArea(defaultPoints)
						, cylinderTester.operateArea(defaultPoints, radius, height, Math.PI * Math.pow(radius, 2) * height)
						);
			}
			case Problem.P4:
			{
				student.testSuite.lab3.problem4.ConstTester constTester = new student.testSuite.lab3.problem4.ConstTester();
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
						, fuelGaugeTester.operateIncreaseGallon(defaultPoints, gallon)
						, fuelGaugeTester.operateIncreaseGallon(defaultPoints, Constants.CAR_MAX_GALLON)
						, fuelGaugeTester.operateDecrementGallon(defaultPoints, gallon)
						, fuelGaugeTester.operateDecrementGallon(defaultPoints, 0)

						// odometer
						, odometerTester.declare(defaultPoints)
						, odometerTester.fields(defaultPoints)
						, odometerTester.declareConstructor(defaultPoints)
						, odometerTester.operateConstructor(defaultPoints, gallon)
						, odometerTester.declareIncrementMileage(defaultPoints)
						, odometerTester.operateIncrementMileage(defaultPoints, 1)
						, odometerTester.declareGetMileage(defaultPoints)
						, odometerTester.operateIncrementMileage(defaultPoints, gallon, Constants.MILES_PER_ONE_GALLON - 1)
						, odometerTester.operateIncrementMileage(defaultPoints, gallon, Constants.ODOMETER_MAXIMUM_MILEAGE_MILES)
						);
			}
			case Problem.P5:
				student.testSuite.lab3.problem5.ConstTester constTester = new student.testSuite.lab3.problem5.ConstTester();
				ParkedCarTester parkedCarTester = new ParkedCarTester();
				String make = "Mazda";
				String model = "CX-5";
				String color = "graphite";
				String licenseNumber = "LIC-12345";
				int parkedMinutes = 90;
				
				ParkingMeterTester parkingMeterTester = new ParkingMeterTester();
				int purchasedMinutes = 60;
				
				PoliceOfficerTester policeOfficerTester = new PoliceOfficerTester(parkedCarTester, parkingMeterTester);
				String officerName = "police name";
				String badgeNumber = "police badge 12345";

				ParkingTicketTester parkingTicketTester = new ParkingTicketTester(parkedCarTester, policeOfficerTester);
				policeOfficerTester.setParkingTicketTester(parkingTicketTester);

				return Arrays.asList(
						// const
						constTester.declare(defaultPoints)
						, constTester.fields(defaultPoints)

						// parkedCar
						, parkedCarTester.declare(defaultPoints)
						, parkedCarTester.declareFields(defaultPoints)
						, parkedCarTester.declareConstructor(defaultPoints)
						, parkedCarTester.operateConstructor(defaultPoints, make, model, color, licenseNumber, parkedMinutes)
						, parkedCarTester.checkGetterDeclaration(defaultPoints)
						, parkedCarTester.checkSetterDeclaration(defaultPoints)
						
						// parkingMeter
						, parkingMeterTester.declare(defaultPoints)
						, parkingMeterTester.declareFields(defaultPoints)
						, parkingMeterTester.declareConstructor(defaultPoints)
						, parkingMeterTester.operateConstructor(defaultPoints, purchasedMinutes)
						, parkingMeterTester.checkGetterDeclaration(defaultPoints)
						, parkingMeterTester.checkSetterDeclaration(defaultPoints)

						// policeOfficer
						, policeOfficerTester.declare(defaultPoints)
						, policeOfficerTester.declareFields(defaultPoints)
						, policeOfficerTester.declareConstructor(defaultPoints)
						, policeOfficerTester.operateConstructor(defaultPoints, officerName, badgeNumber)
						, policeOfficerTester.checkGetterDeclaration(defaultPoints)
						, policeOfficerTester.checkSetterDeclaration(defaultPoints)
						, policeOfficerTester.declareExamineCar(defaultPoints)
						, policeOfficerTester.operateExamineCar(defaultPoints, officerName, badgeNumber, 90, 60)
						, policeOfficerTester.operateExamineCar(defaultPoints, officerName, badgeNumber, 60, 60)
						, policeOfficerTester.operateExamineCar(defaultPoints, 60, 90)
						, policeOfficerTester.declareIssueTicket(defaultPoints)
						, policeOfficerTester.operateIssueTicket(defaultPoints, 30, 60)
						, policeOfficerTester.operateIssueTicket(defaultPoints, 60, 60)
						, policeOfficerTester.operateIssueTicket(defaultPoints, 90, 60)
						, policeOfficerTester.operateIssueTicket(defaultPoints, 150, 60)
						, policeOfficerTester.operateIssueTicket(defaultPoints, 240, 60)

						// parkingTicket
						, parkingTicketTester.declare(defaultPoints)
						, parkingTicketTester.declareFields(defaultPoints)
						, parkingTicketTester.declareConstructor(defaultPoints)
						, parkingTicketTester.checkGetterDeclaration(defaultPoints)
						, parkingTicketTester.checkSetterDeclaration(defaultPoints)
				);
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