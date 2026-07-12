package student.testSuite.labTestSuite.exam.midterm254;

import java.util.Arrays;
import java.util.List;

import student.model.ALabTestSuite;
import student.model.TestCase;
import student.testSuite.exam.midterm254.CartTester;
import student.testSuite.exam.midterm254.GreenTeaTester;
import student.testSuite.exam.midterm254.OrderedItemTester;
import student.testSuite.exam.midterm254.TeaCategoryTester;
import student.testSuite.exam.midterm254.TeaTaxTester;
import student.testSuite.exam.midterm254.TeaTester;

public class ExamMidtermTestSuite254 extends ALabTestSuite {
	@Override
	public List<TestCase> getAllTests(String section) {
		try {
			TeaCategoryTester teaCategoryTester = new TeaCategoryTester();
			TeaTester teaTester = new TeaTester().teaCategoryTester(teaCategoryTester);
			TeaTaxTester teaTaxTester = new TeaTaxTester();
			OrderedItemTester orderedItemTester = new OrderedItemTester().teaTester(teaTester);
			CartTester cartTester = new CartTester().orderedItemTester(orderedItemTester);
			GreenTeaTester greenTeaTester = new GreenTeaTester(teaTester);

			return Arrays.asList(
					/*** Tea Category ***/
					teaCategoryTester.declare()
					, teaCategoryTester.declareFields()

					/*** Tea Tax ***/
					, teaTaxTester.declare()
					, teaTaxTester.declareFields()

					/*** TeaTester ***/
					, teaTester.declare()
					, teaTester.declareGetName()
					, teaTester.declareGetFlavor()
					, teaTester.declareGetPrice()
					, teaTester.declareGetCategory()
					, teaTester.declareEquals()

					/*** Ordered Item ***/
					, orderedItemTester.declare()
					, orderedItemTester.declareFields()
					, orderedItemTester.declareConstructor()
					, orderedItemTester.checkGetterDeclaration(defaultPoints)
					, orderedItemTester.declareGetPriceAfterTax()
					, orderedItemTester.checkToStringDeclaration(defaultPoints)
					, orderedItemTester.declareEqualsMethod(orderedItemTester.getCorrespondingClass())

					/*** Cart ***/
					, cartTester.declare()
					, cartTester.declareFields()
					, cartTester.declareConstructor()
					, cartTester.declareAddOrderedItem()
					, cartTester.declarePrintOrderedItems()
					, cartTester.declareGetTotalPriceAfterTax()
					, cartTester.haveOnlyOneConstructor()

					/*** Green Tea ***/
					, greenTeaTester.declare()
					, greenTeaTester.haveOnlyDefaultConstructor()
			);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
