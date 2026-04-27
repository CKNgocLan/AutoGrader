package student.testSuite.labTestSuite;

import java.util.Arrays;
import java.util.List;

import student.constant.FieldName;
import student.constant.Question;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.testSuite.lab2.problem1.CarTest;
import student.testSuite.lab2.problem2.TemperatureTest;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;
import student.util.ParameterTestingUtils;

/**
 * Test suite for the Employee class. Tests constructors, getters, and setters
 * as per the uploaded Employee.java
 */
public class LabTestSuite2 extends ALabTestSuite {
	private CarTest carTest = CarTest.getInstance();
	private TemperatureTest temperatureTest = TemperatureTest.getInstance();
	
	@Override
	public List<ITestCase> getAllTests(String question) {
		switch (question) {
		case Question.Q1:
			return Arrays.asList(
					carTest.checkExistence(5)
					
					// partial-args constructor
					, carTest.checkPartialArgsConstructorDeclaration(5, ParameterTestingUtils.toArray(int.class, String.class))
					, carTest.checkPartialArgsConstructorOperation(10,
							new ParameterTesting(FieldName.YEAR_MODEL, int.class, 2025)
							, new ParameterTesting(FieldName.MAKE, String.class, "Mazda")
							, new ParameterTesting(FieldName.SPEED, int.class, 0, true)
					)

					// accelerate()
					, carTest.checkAccelerateDeclaration(25)
					, carTest.checkAccelerateOperation(15)

					// brake()
					, carTest.checkBrakeDeclaration(25)
					, carTest.checkBrakeOperation(15)
			);
		case Question.Q2:
			return Arrays.asList(
					temperatureTest.checkExistence(5)
					, temperatureTest.checkField(5, FieldName.FTEMP, double.class)
					, temperatureTest.checkSetgetFahrenheit(10, 50)
					, temperatureTest.checkGetCelsius(15, 50, 10)
					, temperatureTest.checkGetKelvin(15, 50, 283)
			);
		case Question.Q3:
			return null;
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