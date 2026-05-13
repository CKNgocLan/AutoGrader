package student.testSuite.midterm;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import student.constant.Question;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.testSuite.midterm253.BallpointPenTester;
import student.testSuite.midterm253.BrandName;
import student.testSuite.midterm253.BrandTester;
import student.testSuite.midterm253.Country;
import student.testSuite.midterm253.CountryTester;
import student.testSuite.midterm253.DiscountTester;
import student.testSuite.midterm253.FountainPenTester;
import student.testSuite.midterm253.PenName;
import student.testSuite.midterm253.PenTester;
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
					PenTester penTester = new PenTester(brandTester, discountTester).setPenTypeTester(penTypeTester);
					BallpointPenTester ballpointPenTester = new BallpointPenTester(penTester, brandTester, discountTester)
							.setPenTypeTester(penTypeTester);
					FountainPenTester fountainPenTester = new FountainPenTester(penTester, brandTester)
							.setPenTypeTester(penTypeTester)
							.setDiscountTester(discountTester);
					
					Object sailorBrand = brandTester.instantiate(BrandName.SAILOR, Country.JAPAN);
					Object concopensBrand = brandTester.instantiate(BrandName.CONCOPENS, Country.VIETNAM);
					Object discount = discountTester.instantiate(0.19, LocalDate.now().plusMonths(1));
					
//					List<ITestCase> testcases = penTypeTester.getAllTestcases();
//					testcases.addAll(countryTester.getAllTestcases());
//					testcases.addAll(taxTester.getAllTestcases());
//					testcases.addAll(brandTester.getAllTestcases());
					
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
							, discountTester.operateConstructor(defaultPoints, 0.19, LocalDate.now().plusMonths(1))
							, discountTester.declareGetters(defaultPoints)
							, discountTester.declareSetters(defaultPoints)
							
							// ballpoint pen
							, ballpointPenTester.declare(defaultPoints)
							, ballpointPenTester.declareFields(defaultPoints)
							, ballpointPenTester.declareConstructorWithDiscount(defaultPoints)
							, ballpointPenTester.declareConstructorWithoutDiscount(defaultPoints)
							, ballpointPenTester.declareSuperClass(defaultPoints)
							, ballpointPenTester.operateConstructorWithoutDiscount(defaultPoints
									, PenName.SHIKIORI
									, sailorBrand
									, 51.61
							)
							, ballpointPenTester.operateConstructorWithDiscount(defaultPoints
									, PenName.SHIKIORI
									, sailorBrand
									, 51.61
									, discount
							)
							, ballpointPenTester.operateConstructorWithDiscount(defaultPoints
									, PenName.FRIXION
									, sailorBrand
									, 13.82
									, discount
							)
							, ballpointPenTester.declareGetTax(defaultPoints)
							, ballpointPenTester.operateGetTax(defaultPoints
									, PenName.SHIKIORI
									, sailorBrand
									, 51.61)
							, ballpointPenTester.getDiscountPrice(defaultPoints
									, PenName.SHIKIORI
									, sailorBrand
									, 51.61)
							, ballpointPenTester.getDiscountPrice(defaultPoints
									, PenName.SHIKIORI
									, sailorBrand
									, 51.61
									, 0.19)
							, ballpointPenTester.declareIsDiscounted(defaultPoints)
							, ballpointPenTester.operateIsDiscounted(defaultPoints
									, PenName.SHIKIORI
									, sailorBrand
									, 51.61)
							, ballpointPenTester.operateIsDiscounted(defaultPoints
									, PenName.SHIKIORI
									, sailorBrand
									, 51.61
									, 0.19)
							
							// fountain pen
							, fountainPenTester.declare(defaultPoints)
							, fountainPenTester.declareFields(defaultPoints)
							, fountainPenTester.declareConstructor(defaultPoints)
							, fountainPenTester.declareSuperClass(defaultPoints)
							, fountainPenTester.operateConstructor(defaultPoints
									, PenName.GOLDEN_LOTUS
									, concopensBrand
									, 645.27)
							, fountainPenTester.declareSuperClass(defaultPoints)
							, fountainPenTester.declareGetTax(defaultPoints)
							, fountainPenTester.operateGetTax(defaultPoints
									, PenName.GOLDEN_LOTUS
									, concopensBrand
									, 645.27)
							, fountainPenTester.declareIsDiscounted(defaultPoints)
							, fountainPenTester.operateIsDiscounted(defaultPoints
									, PenName.GOLDEN_LOTUS
									, concopensBrand
									, 645.27)
							, fountainPenTester.operateIsDiscounted(defaultPoints
									, PenName.GOLDEN_LOTUS
									, concopensBrand
									, 645.27
									, 0.19)

							// pen
							, penTester.declare(defaultPoints)
							, penTester.declareFields(defaultPoints)
							, penTester.declareConstructorWithoutDiscount(defaultPoints)
							, penTester.declareConstructorWithDiscount(defaultPoints)
							, penTester.declareFields(defaultPoints)
							, penTester.declareIsDiscounted(defaultPoints)
							, penTester.declareGetDiscountPrice(defaultPoints)
							, penTester.declareGetPriceAfterTax(defaultPoints)
							, penTester.declareGetTax(defaultPoints)
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
