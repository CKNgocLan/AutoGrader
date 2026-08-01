package student.testSuite.labTestSuite.exam.final254;

import java.util.Arrays;
import java.util.List;

import student.model.ALabTestSuite;
import student.model.TestCase;
import student.testSuite.exam.final254.CartBuilderTester;
import student.testSuite.exam.final254.CartTester;
import student.testSuite.exam.final254.OrderedItemTester;
import student.testSuite.exam.final254.PremiumMocCauTeaFactoryTester;
import student.testSuite.exam.final254.PremiumMocCauTeaTester;
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
			PremiumMocCauTeaFactoryTester premiumMocCauTeaFactoryTester = new PremiumMocCauTeaFactoryTester(teaFactoryTester);
			Object premiumMocCauTeaFactoryInstance = premiumMocCauTeaFactoryTester.instantiateTeaFactory();
			
			return Arrays.asList(
					/* category */
					teaCategoryTester.declare()
//					, teaCategoryTester.declareFields()
//					
//					/* tax */
//					, taxTester.declare()
//					, taxTester.declareFields()
//
//					/* tea */
//					, teaTester.declare()
//					, teaTester.declareGetName()
//					, teaTester.declareGetPrice()
//					, teaTester.declareGetCategory()
//					, teaTester.declareEquals()
//					
//					/* ordered item */
//					, orderedItemTester.declare()
//					, orderedItemTester.declareFields()
//					, orderedItemTester.declareConstructor()
//					, orderedItemTester.checkGetterDeclaration(defaultPoints)
//					, orderedItemTester.declareAddWeight()
//					, orderedItemTester.declareGetPriceAfterTax()
//					, orderedItemTester.checkToStringDeclaration(defaultPoints)
//					, orderedItemTester.declareEqualsMethod(Object.class)
//					, orderedItemTester.declareEqualsMethod(orderedItemTester.getCorrespondingClass())
//					
//					/* cart builder */
//					, builderTester.declare()
//					, builderTester.declareFields()
//					, builderTester.declareConstructor()
//					, builderTester.declareFields()
//					, builderTester.declareAddOrderedItem()
//					, builderTester.operateAddOrderedItem(premiumMocCauTeaFactoryInstance, 1)
//					, builderTester.operateAddOrderedItem(premiumMocCauTeaFactoryInstance, 2)
//					, builderTester.operateAddNulldOrderedItem()
//					, builderTester.operateAddOrderedItemWithoutWeight(premiumMocCauTeaFactoryInstance)
//					, builderTester.operateAddOrderedItemDuplicated(premiumMocCauTeaFactoryInstance, 1, 2, 3)
//					, builderTester.declareBuild()
					, builderTester.operateBuild(builderTester.add(premiumMocCauTeaFactoryInstance, premiumMocCauTeaFactoryTester.defaultWeight))
					
//					/* cart */
//					, cartTester.declare()
//					, cartTester.declareFields()
//					, cartTester.declareConstructor()
//					, cartTester.haveOnlyOneConstructor()
//					, cartTester.declareValidate()
//					, cartTester.operateValidate()
//					, cartTester.declarePrintOrderedItems()
//					, cartTester.operatePrintOrderedItems()
//					, cartTester.declareGetTotalPriceAfterTax()
//					, cartTester.operateGetTotalPriceAfterTax(0)
//					
//					/* premium moc cau tea */
//					, premiumMocCauTeaTester.declare()
//					, premiumMocCauTeaTester.implementInterface()
//					, premiumMocCauTeaTester.haveOnlyDefaultConstructor()
//					, premiumMocCauTeaTester.declareGetName()
//					, premiumMocCauTeaTester.operateGetName()
//					, premiumMocCauTeaTester.declareGetPrice()
//					, premiumMocCauTeaTester.operateGetPrice()
//					, premiumMocCauTeaTester.declareGetCategory()
//					, premiumMocCauTeaTester.operateGetCategory()
//
//					/* premium moc cau tea factory */
//					, premiumMocCauTeaFactoryTester.declare()
//					, premiumMocCauTeaFactoryTester.implementInterface()
//					, premiumMocCauTeaFactoryTester.haveOnlyDefaultConstructor()
//					, premiumMocCauTeaFactoryTester.declareCreateTea()
//					, premiumMocCauTeaFactoryTester.operateCreateTea()
					);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<TestCase> testCaseOfTeaCategoryT() {
		return List.of();
	}

	private List<TestCase> testCaseOfTax() {
		return List.of();
	}
	
	private List<TestCase> testCaseOfTea() {
		return List.of();
	}
	
	private List<TestCase> testCaseOfOrderedItem() {
		return List.of();
	}
	
	private List<TestCase> testCaseOfCartBuilder() {
		return List.of();
	}
	
	private List<TestCase> testCaseOfCart() {
		return List.of();
	}
	
	private List<TestCase> testCaseOfPremiumMocCauTea() {
		return List.of();
	}
	
	private List<TestCase> testCaseOfPremiumMocCauTeaFactory() {
		return List.of();
	}
}
