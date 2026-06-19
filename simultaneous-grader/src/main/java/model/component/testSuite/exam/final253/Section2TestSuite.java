package model.component.testSuite.exam.final253;

import java.util.Arrays;
import java.util.List;

import model.component.TestCase;
import model.component.testSuite.TestSuite;
import model.component.tester.exam.final253.section1.BrandName;
import model.component.tester.exam.final253.section1.ModelName;
import model.component.tester.exam.final253.section2.PenBuilderTester;
import model.component.tester.exam.final253.section2.PenTester;

public class Section2TestSuite extends TestSuite {
	private static Section2TestSuite instance;

	public static Section2TestSuite getInstance() {
		if (instance == null) {
			instance = new Section2TestSuite();
		}
		return instance;
	}

	@Override
	public List<TestCase> getTestCases() {
		try {
			PenBuilderTester builderTester = new PenBuilderTester();
			PenTester penTester = new PenTester(builderTester);

			String brand = BrandName.CONCOPENS;
			String model = ModelName.GOLDEN_LOTUS;
			double price = 61;

			return Arrays.asList(
					penTester.declare()
					, penTester.declareFields()
					, penTester.declarePrivateConstructor()
					, penTester.checkGetterDeclaration(defaultPoints)
					, penTester.checkToStringDeclaration(defaultPoints)
					, penTester.operateToString(brand, model, price)

					// builder
					, builderTester.declare()
					, builderTester.declareFields()
					, builderTester.declareSetBrand()
					, builderTester.operateSetBrand(brand)
					, builderTester.declareSetModel()
					, builderTester.operateSetModel(model)
					, builderTester.declareSetPrice()
					, builderTester.operateSetPrice(price)
					, builderTester.declareBuild()
					, builderTester.operateBuild(brand, model, price)
			);
		} catch (Exception e) {
			e.printStackTrace();
			return List.of();
		}
	}
}