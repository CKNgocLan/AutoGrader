package student.testSuite.lab;

import java.util.Arrays;
import java.util.List;

import student.constant.FieldName;
import student.constant.Question;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.testSuite.classTestSuite.CarTest;
import student.testSuite.classTestSuite.ClassTest;
import student.testSuite.methodTestSuite.MethodTest;
import student.util.ParameterTestingUtils;

/**
 * Test suite for the Employee class. Tests constructors, getters, and setters
 * as per the uploaded Employee.java
 */
public class LabTestSuite2 extends ALabTestSuite {
	private ClassTest classTest = ClassTest.getInstance();
	private MethodTest methodTest = MethodTest.getInstance();
	private CarTest carTest = CarTest.getInstance();
	
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
					carTest.checkExistence(5)
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