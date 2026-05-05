package student.testSuite.labTestSuite;

import java.util.Arrays;
import java.util.List;

import student.constant.Question;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.testSuite.BaseTester;
import student.testSuite.lab3.problem1.CashRegisterTester;
import student.testSuite.lab3.problem1.RetailItemTester;

public class LabTestSuite3 extends ALabTestSuite {

	@Override
	public List<ITestCase> getAllTests(String question) {
		int defaultPoints = 5;
		try {

			switch (question) {
			case Question.Q1:
				BaseTester retailItem = new RetailItemTester().getInstance();
				BaseTester cashRegister = new CashRegisterTester().getInstance();

				return Arrays.asList(
						// retail item
						retailItem.checkExistence(defaultPoints)
						
						// cash register
						, cashRegister.checkExistence(defaultPoints)
				);
			case Question.Q2:
				return Arrays.asList();
			case Question.Q3:

				return Arrays.asList();
			case Question.Q4:
				return Arrays.asList();
			case Question.Q5:

				return Arrays.asList();
			case Question.Q6:
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