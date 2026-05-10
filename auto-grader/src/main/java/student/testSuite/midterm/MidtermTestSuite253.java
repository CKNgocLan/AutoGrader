package student.testSuite.midterm;

import java.util.Arrays;
import java.util.List;

import student.constant.Question;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.testSuite.midterm253.BrandTester;
import student.testSuite.midterm253.CountryTester;
import student.testSuite.midterm253.DiscountTester;
import student.testSuite.midterm253.PenTypeTester;
import student.testSuite.midterm253.TaxTester;

public class MidtermTestSuite253 extends ALabTestSuite {
	@Override
	public List<ITestCase> getAllTests(String question) {
		int defaultPoints = 5;
		try {

			switch (question) {
				case Question.Q1:
					PenTypeTester penTypeTester = new PenTypeTester();
					CountryTester countryTester = new CountryTester();
					TaxTester taxTester = new TaxTester();
					BrandTester brandTester = new BrandTester();
					DiscountTester discountTester = new DiscountTester();
					
					return Arrays.asList(
							// pen type enum
							penTypeTester.declare(defaultPoints)
							, penTypeTester.declareFields(defaultPoints)
							
							// country
							, countryTester.declare(defaultPoints)
							, countryTester.declareFields(defaultPoints)
							
							// tax
							, taxTester.declare(defaultPoints)
							, taxTester.declareFields(defaultPoints)
							
							// brand
							, brandTester.declare(defaultPoints)
							, brandTester.declareFields(defaultPoints)
							, brandTester.declareConstructor(defaultPoints)
							, brandTester.declareGetters(defaultPoints)
							, brandTester.declareSetters(defaultPoints)
							, brandTester.checkToStringDeclaration(defaultPoints)
							
							// discount
							, discountTester.declare(defaultPoints)
							, discountTester.declareFields(defaultPoints)
							, discountTester.declareConstructor(defaultPoints)
							, discountTester.declareGetters(defaultPoints)
							, discountTester.declareSetters(defaultPoints)
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
