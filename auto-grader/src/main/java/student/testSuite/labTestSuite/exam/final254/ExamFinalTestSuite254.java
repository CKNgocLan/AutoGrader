package student.testSuite.labTestSuite.exam.final254;

import java.util.Arrays;
import java.util.List;

import student.model.ALabTestSuite;
import student.model.TestCase;
import student.testSuite.exam.final254.CartBuilderTester;
import student.testSuite.exam.final254.CartTester;
import student.testSuite.exam.final254.GrapefruitTeaFactoryTester;
import student.testSuite.exam.final254.GrapefruitTeaTester;
import student.testSuite.exam.final254.OrderedItemTester;
import student.testSuite.exam.final254.PremiumLotusTeaFactoryTester;
import student.testSuite.exam.final254.PremiumLotusTeaTester;
import student.testSuite.exam.final254.PremiumMocCauTeaFactoryTester;
import student.testSuite.exam.final254.PremiumMocCauTeaTester;
import student.testSuite.exam.final254.SpecialDinhTeaFactoryTester;
import student.testSuite.exam.final254.SpecialDinhTeaTester;
import student.testSuite.exam.final254.TaxTester;
import student.testSuite.exam.final254.TeaCategoryTester;
import student.testSuite.exam.final254.TeaFactoryTester;
import student.testSuite.exam.final254.TeaTester;

public class ExamFinalTestSuite254 extends ALabTestSuite {
	@Override
	public List<TestCase> getAllTests(String question) {
		try {
			TeaCategoryTester teaCategoryTester = new TeaCategoryTester();
			TaxTester taxTester = new TaxTester();

			TeaTester teaTester = new TeaTester()
					.teaCategoryTester(teaCategoryTester);
			TeaFactoryTester teaFactoryTester = new TeaFactoryTester()
					.teaTester(teaTester);

			OrderedItemTester orderedItemTester = new OrderedItemTester()
					.teaFactoryTester(teaFactoryTester)
					.teaTester(teaTester);
			
			CartBuilderTester builderTester = new CartBuilderTester()
					.orderedItemTester(orderedItemTester);
			CartTester cartTester = new CartTester(builderTester)
					.orderedItemTester(orderedItemTester);

			PremiumMocCauTeaTester premiumMocCauTeaTester = new PremiumMocCauTeaTester(teaTester)
					.teaCategoryTester(teaCategoryTester);
			PremiumMocCauTeaFactoryTester premiumMocCauTeaFactoryTester = new PremiumMocCauTeaFactoryTester(teaTester, teaFactoryTester);
//			Object premiumMocCauTeaFactoryInstance = premiumMocCauTeaFactoryTester.toFactory();
			
			SpecialDinhTeaTester specialDinhTeaTester = new SpecialDinhTeaTester(teaTester)
					.teaCategoryTester(teaCategoryTester);
			SpecialDinhTeaFactoryTester specialDinhTeaFactoryTester = new SpecialDinhTeaFactoryTester(teaTester, teaFactoryTester);
//			Object specialDinhTeaFactoryInstance = specialDinhTeaFactoryTester.toFactory();
			
			PremiumLotusTeaTester premiumLotusTeaTester = new PremiumLotusTeaTester(teaTester)
					.teaCategoryTester(teaCategoryTester);
			PremiumLotusTeaFactoryTester premiumLotusTeaFactoryTester = new PremiumLotusTeaFactoryTester(teaTester, teaFactoryTester);
//			Object premiumLotusTeaFactoryInstance = premiumLotusTeaFactoryTester.toFactory();

			GrapefruitTeaTester grapefruitTeaTester = new GrapefruitTeaTester(teaTester)
					.teaCategoryTester(teaCategoryTester);
			GrapefruitTeaFactoryTester grapefruitTeaFactoryTester = new GrapefruitTeaFactoryTester(teaTester, teaFactoryTester);
//			Object grapefruitTeaFactory = grapefruitTeaFactoryTester.toFactory();
			
			return Arrays.asList(
					// category
					teaCategoryTester.declare()
					, teaCategoryTester.declareFields()
					
					// tax
					, taxTester.declare()
					, taxTester.declareFields()

					// tea
					, teaTester.declare()
					, teaTester.declareGetName()
					, teaTester.declareGetPrice()
					, teaTester.declareGetCategory()
					, teaTester.declareEquals()
					
					// ordered item
					, orderedItemTester.declare()
					, orderedItemTester.declareFields()
					, orderedItemTester.declareConstructor()
					, orderedItemTester.checkGetterDeclaration(defaultPoints)
					, orderedItemTester.declareAddWeight()
					, orderedItemTester.declareGetPriceAfterTax()
					, orderedItemTester.checkToStringDeclaration(defaultPoints)
					, orderedItemTester.declareEqualsMethod(Object.class)
					, orderedItemTester.declareEqualsMethod(orderedItemTester.getCorrespondingClass())
					
					// premium moc cau tea
					, premiumMocCauTeaTester.declare()
					, premiumMocCauTeaTester.implementInterface()
					, premiumMocCauTeaTester.haveOnlyDefaultConstructor()
					, premiumMocCauTeaTester.declareGetName()
					, premiumMocCauTeaTester.operateGetName()
					, premiumMocCauTeaTester.declareGetPrice()
					, premiumMocCauTeaTester.operateGetPrice()
					, premiumMocCauTeaTester.declareGetCategory()
					, premiumMocCauTeaTester.operateGetCategory()

					// premium moc cau tea factory
					, premiumMocCauTeaFactoryTester.declare()
					, premiumMocCauTeaFactoryTester.implementInterface()
					, premiumMocCauTeaFactoryTester.haveOnlyDefaultConstructor()
					, premiumMocCauTeaFactoryTester.declareCreateTea()
					, premiumMocCauTeaFactoryTester.operateCreateTea()
					
					// special dinh tea
					, specialDinhTeaTester.declare()
					, specialDinhTeaTester.implementInterface()
					, specialDinhTeaTester.haveOnlyDefaultConstructor()
					, specialDinhTeaTester.declareGetName()
					, specialDinhTeaTester.operateGetName()
					, specialDinhTeaTester.declareGetPrice()
					, specialDinhTeaTester.operateGetPrice()
					, specialDinhTeaTester.declareGetCategory()
					, specialDinhTeaTester.operateGetCategory()
					
					// special dinh tea factory
					, specialDinhTeaFactoryTester.declare()
					, specialDinhTeaFactoryTester.implementInterface()
					, specialDinhTeaFactoryTester.haveOnlyDefaultConstructor()
					, specialDinhTeaFactoryTester.declareCreateTea()
					, specialDinhTeaFactoryTester.operateCreateTea()

					// premium lotus tea
					, premiumLotusTeaTester.declare()
					, premiumLotusTeaTester.implementInterface()
					, premiumLotusTeaTester.haveOnlyDefaultConstructor()
					, premiumLotusTeaTester.declareGetName()
					, premiumLotusTeaTester.operateGetName()
					, premiumLotusTeaTester.declareGetPrice()
					, premiumLotusTeaTester.operateGetPrice()
					, premiumLotusTeaTester.declareGetCategory()
					, premiumLotusTeaTester.operateGetCategory()

					// premium lotus tea factory
					, premiumLotusTeaFactoryTester.declare()
					, premiumLotusTeaFactoryTester.implementInterface()
					, premiumLotusTeaFactoryTester.haveOnlyDefaultConstructor()
					, premiumLotusTeaFactoryTester.declareCreateTea()
					, premiumLotusTeaFactoryTester.operateCreateTea()

					// grapefruit tea
					, grapefruitTeaTester.declare()
					, grapefruitTeaTester.implementInterface()
					, grapefruitTeaTester.haveOnlyDefaultConstructor()
					, grapefruitTeaTester.declareGetName()
					, grapefruitTeaTester.operateGetName()
					, grapefruitTeaTester.declareGetPrice()
					, grapefruitTeaTester.operateGetPrice()
					, grapefruitTeaTester.declareGetCategory()
					, grapefruitTeaTester.operateGetCategory()

					// grapefruit tea factory
					, grapefruitTeaFactoryTester.declare()
					, grapefruitTeaFactoryTester.implementInterface()
					, grapefruitTeaFactoryTester.haveOnlyDefaultConstructor()
					, grapefruitTeaFactoryTester.declareCreateTea()
					, grapefruitTeaFactoryTester.operateCreateTea()

					// cart builder
					, builderTester.declare()
					, builderTester.declareFields()
					, builderTester.declareConstructor()
					, builderTester.declareFields()
					, builderTester.declareAddOrderedItem()
					, builderTester.operateAddOrderedItem(premiumMocCauTeaFactoryTester.toFactory(), 1)
					, builderTester.operateAddOrderedItem(premiumMocCauTeaFactoryTester.toFactory(), 2)
					, builderTester.operateAddOrderedItem(specialDinhTeaFactoryTester.toFactory(), 1.5)
					, builderTester.operateAddOrderedItem(premiumLotusTeaFactoryTester.toFactory(), 0.5)
					, builderTester.operateAddOrderedItem(grapefruitTeaFactoryTester.toFactory(), 0.25)
					, builderTester.operateAddNulldOrderedItem()
					, builderTester.operateAddOrderedItemWithoutWeight(premiumMocCauTeaFactoryTester.toFactory())
					, builderTester.operateAddOrderedItemDuplicated(premiumMocCauTeaFactoryTester.toFactory(), 1, 2, 3)
					, builderTester.operateAddOrderedItemDuplicated(specialDinhTeaFactoryTester.toFactory(), 1.25, 0.2)
					, builderTester.operateAddOrderedItemDuplicated(premiumLotusTeaFactoryTester.toFactory(), 0.25, 1.2)
					, builderTester.operateAddOrderedItemDuplicated(grapefruitTeaFactoryTester.toFactory(), 5, 0.25)
					, builderTester.declareBuild()
					, builderTester.operateBuild(
							builderTester.add(premiumMocCauTeaFactoryTester.toFactory(), premiumMocCauTeaFactoryTester.defaultWeight)
					)
					, builderTester.operateBuild(
							builderTester.add(premiumMocCauTeaFactoryTester.toFactory(), premiumMocCauTeaFactoryTester.defaultWeight)
							, builderTester.add(specialDinhTeaFactoryTester.toFactory(), specialDinhTeaFactoryTester.defaultWeight)
							, builderTester.add(premiumLotusTeaFactoryTester.toFactory(), premiumLotusTeaFactoryTester.defaultWeight)
							, builderTester.add(grapefruitTeaFactoryTester.toFactory(), grapefruitTeaFactoryTester.defaultWeight)
							)
					
					// cart
					, cartTester.declare()
					, cartTester.declareFields()
					, cartTester.declareConstructor()
					, cartTester.haveOnlyOneConstructor()
					, cartTester.declareValidate()
					, cartTester.declarePrintOrderedItems()
					, cartTester.operatePrintOrderedItems()
					, cartTester.declareGetTotalPriceAfterTax()
					, cartTester.operateGetTotalPriceAfterTax()
					, cartTester.operateGetTotalPriceAfterTax(
							builderTester.add(premiumMocCauTeaFactoryTester.toFactory()
								, premiumMocCauTeaFactoryTester.defaultWeight
								, teaTester.priceAfterTax(premiumMocCauTeaTester.price, premiumMocCauTeaFactoryTester.defaultWeight)
							)
					)
					, cartTester.operateGetTotalPriceAfterTax(
							builderTester.add(premiumMocCauTeaFactoryTester.toFactory()
									, premiumMocCauTeaFactoryTester.defaultWeight
									, teaTester.priceAfterTax(premiumMocCauTeaTester.price, premiumMocCauTeaFactoryTester.defaultWeight)
									)
					)
					, cartTester.operateGetTotalPriceAfterTax(
							builderTester.add(premiumMocCauTeaFactoryTester.toFactory()
									, premiumMocCauTeaFactoryTester.defaultWeight
									, teaTester.priceAfterTax(premiumMocCauTeaTester.price, premiumMocCauTeaFactoryTester.defaultWeight)
									)
							, builderTester.add(specialDinhTeaFactoryTester.toFactory()
									, specialDinhTeaFactoryTester.defaultWeight
									, teaTester.priceAfterTax(specialDinhTeaTester.price, specialDinhTeaFactoryTester.defaultWeight)
									)
							, builderTester.add(premiumLotusTeaFactoryTester.toFactory()
									, premiumLotusTeaFactoryTester.defaultWeight
									, teaTester.priceAfterTax(premiumLotusTeaTester.price, premiumLotusTeaFactoryTester.defaultWeight)
									)
							, builderTester.add(grapefruitTeaFactoryTester.toFactory()
									, grapefruitTeaFactoryTester.defaultWeight
									, teaTester.priceAfterTax(grapefruitTeaTester.price, grapefruitTeaFactoryTester.defaultWeight)
									)
							)
					, cartTester.operatePrintOrderedItems(
							builderTester.add(premiumMocCauTeaFactoryTester.toFactory()
									, premiumMocCauTeaFactoryTester.defaultWeight)
							)
					, cartTester.operatePrintOrderedItems(
							builderTester.add(premiumLotusTeaFactoryTester.toFactory(), 0.25)
							, builderTester.add(premiumLotusTeaFactoryTester.toFactory(), 0.25)
							)
					, cartTester.operatePrintOrderedItems(
							builderTester.add(premiumMocCauTeaFactoryTester.toFactory(), 1)
							, builderTester.add(premiumMocCauTeaFactoryTester.toFactory(), 0.5)
							, builderTester.add(specialDinhTeaFactoryTester.toFactory(), 0.1)
							, builderTester.add(premiumLotusTeaFactoryTester.toFactory(), 0.25)
							)
					);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	private List<TestCase> testCaseOfTeaCategoryT() {
//		return List.of();
//	}
//
//	private List<TestCase> testCaseOfTax() {
//		return List.of();
//	}
//	
//	private List<TestCase> testCaseOfTea() {
//		return List.of();
//	}
//	
//	private List<TestCase> testCaseOfOrderedItem() {
//		return List.of();
//	}
//	
//	private List<TestCase> testCaseOfCartBuilder() {
//		return List.of();
//	}
//	
//	private List<TestCase> testCaseOfCart() {
//		return List.of();
//	}
//	
//	private List<TestCase> testCaseOfPremiumMocCauTea() {
//		return List.of();
//	}
//	
//	private List<TestCase> testCaseOfPremiumMocCauTeaFactory() {
//		return List.of();
//	}
}
