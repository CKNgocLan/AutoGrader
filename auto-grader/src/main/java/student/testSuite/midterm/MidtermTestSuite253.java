package student.testSuite.midterm;

import java.util.Arrays;
import java.util.List;

import student.constant.Question;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.testSuite.midterm253.CountryTester;
import student.testSuite.midterm253.PenTypeTester;

public class MidtermTestSuite253 extends ALabTestSuite {
	@Override
	public List<ITestCase> getAllTests(String question) {
		int defaultPoints = 5;
		try {

			switch (question) {
				case Question.Q1:
					PenTypeTester penTypeTester = new PenTypeTester();
					CountryTester countryTester = new CountryTester();
					
					return Arrays.asList(
							// pen type enum
							penTypeTester.declare(defaultPoints)
							, penTypeTester.declareFields(defaultPoints)
							
							// country enum
							, countryTester.declare(defaultPoints)
							, countryTester.declareFields(defaultPoints)
							);
				default:
					return null;
			}
		} catch (ClassNotFoundException e) {
			return null;
		} catch (Exception e) {
			System.out.println("Class Not Found: %s".formatted(e.getMessage()));
			for (StackTraceElement st : e.getStackTrace()) {
				System.out.println(st);
			}

			return null;
		}
	}
}
