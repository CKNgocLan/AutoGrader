package student.testSuite.labTestSuite;

import java.util.Arrays;
import java.util.List;

import student.constant.FieldName;
import student.constant.Question;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.testSuite.lab2.problem1.CarTester;
import student.testSuite.lab2.problem2.TemperatureTester;
import student.testSuite.lab2.problem3.CustomerTester;
import student.testSuite.lab2.problem3.PetShopTester;
import student.testSuite.lab2.problem3.PetTester;
import student.testSuite.lab2.problem3.ServiceEstimateTester;
import student.util.ParameterTestingUtils;

/**
 * Test suite for the Employee class. Tests constructors, getters, and setters
 * as per the uploaded Employee.java
 */
public class LabTestSuite2 extends ALabTestSuite {
	
	@Override
	public List<ITestCase> getAllTests(String question) {
		switch (question) {
		case Question.Q1:
			CarTester car = CarTester.getInstance();

			return Arrays.asList(
					car.checkExistence(5)
					
					// partial-args constructor
					, car.checkPartialArgsConstructorDeclaration(5, ParameterTestingUtils.toArray(int.class, String.class))
					, car.checkPartialArgsConstructorOperation(10,
							new ParameterTesting(FieldName.YEAR_MODEL, int.class, 2025)
							, new ParameterTesting(FieldName.MAKE, String.class, "Mazda")
							, new ParameterTesting(FieldName.SPEED, int.class, 0, true)
					)

					// accelerate()
					, car.checkAccelerateDeclaration(25)
					, car.checkAccelerateOperation(15)

					// brake()
					, car.checkBrakeDeclaration(25)
					, car.checkBrakeOperation(15)
			);
		case Question.Q2:
			TemperatureTester temperature = TemperatureTester.getInstance();

			return Arrays.asList(
					temperature.checkExistence(5)
					, temperature.checkField(5, FieldName.FTEMP, double.class)
					, temperature.checkSetgetFahrenheit(10, 50)
					, temperature.checkGetCelsius(15, 50, 10)
					, temperature.checkGetKelvin(15, 50, 283)
			);
		case Question.Q3:
			CustomerTester customer = CustomerTester.getInstance();
			PetTester pet = PetTester.getInstance();
			ServiceEstimateTester serviceEstimate = ServiceEstimateTester.getInstance();
			PetShopTester petShop = PetShopTester.getInstance();
			
			try {
				return Arrays.asList(
						// existence
						customer.checkExistence(5)
						, pet.checkExistence(5)
						, serviceEstimate.checkExistence(5)
						, petShop.checkExistence(5)
						
						// field declarations
						, customer.checkFields(15)
						, pet.checkFields(15)
						, serviceEstimate.checkFields(15)
						, petShop.checkFields(15)
				);
			} catch (ClassNotFoundException e) {
				System.out.println("Class Not Found: %s".formatted(e.getMessage()));
				return List.of();
			}
		case Question.Q4:
			return null;
		case Question.Q5:
			return null;
		case Question.Q6:
			return null;
		default:
			return null;
		}
	}
}