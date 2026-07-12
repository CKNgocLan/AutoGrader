package student.testSuite.labTestSuite.exam.midterm254;

import java.util.Arrays;
import java.util.List;

import student.model.ALabTestSuite;
import student.model.TestCase;
import student.testSuite.exam.midterm254.CartTester;
import student.testSuite.exam.midterm254.DinhNgocTraTeaTester;
import student.testSuite.exam.midterm254.GreenTeaTester;
import student.testSuite.exam.midterm254.JasmineTeaTester;
import student.testSuite.exam.midterm254.LotusTeaTester;
import student.testSuite.exam.midterm254.MocCauTeaTester;
import student.testSuite.exam.midterm254.OrderedItemTester;
import student.testSuite.exam.midterm254.ScentedTeaTester;
import student.testSuite.exam.midterm254.TeaCategoryTester;
import student.testSuite.exam.midterm254.TeaTaxTester;
import student.testSuite.exam.midterm254.TeaTester;

public class ExamMidtermTestSuite254 extends ALabTestSuite {
	@Override
	public List<TestCase> getAllTests(String section) {
		try {
			TeaCategoryTester teaCategoryTester = new TeaCategoryTester();
			TeaTester teaTester = new TeaTester()
					.teaCategoryTester(teaCategoryTester);
			TeaTaxTester teaTaxTester = new TeaTaxTester();

			OrderedItemTester orderedItemTester = new OrderedItemTester()
					.teaTester(teaTester);
			CartTester cartTester = new CartTester()
					.orderedItemTester(orderedItemTester);

			GreenTeaTester greenTeaTester = new GreenTeaTester(teaTester)
					.teaCategoryTester(teaCategoryTester);
			ScentedTeaTester scentedTeaTester = new ScentedTeaTester(teaTester)
					.teaCategoryTester(teaCategoryTester);

			MocCauTeaTester mocCauTeaTester = new MocCauTeaTester(greenTeaTester);
			DinhNgocTraTeaTester dinhNgocTraTeaTester = new DinhNgocTraTeaTester(greenTeaTester);
			LotusTeaTester lotusTeaTester = new LotusTeaTester(scentedTeaTester);
			JasmineTeaTester jasmineTeaTester = new JasmineTeaTester(scentedTeaTester);

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
					, greenTeaTester.implementInterface()
					, greenTeaTester.haveOnlyDefaultConstructor()

					/*** Scented Tea ***/
					, scentedTeaTester.declare()
					, scentedTeaTester.implementInterface()
					, scentedTeaTester.haveOnlyDefaultConstructor()

					/*** Moc Cau Tea ***/
					, mocCauTeaTester.declare()
					, mocCauTeaTester.declareSuper()
					, mocCauTeaTester.declareGetName()
					, mocCauTeaTester.declareGetFlavor()
					, mocCauTeaTester.declareGetPrice()
					, mocCauTeaTester.operateGetName()
					, mocCauTeaTester.operateGetFlavor()
					, mocCauTeaTester.operateGetPrice()
					, mocCauTeaTester.excludeGetCategory()
					, mocCauTeaTester.operateGetCategory()

					/*** Dinh Ngoc Tra Tea ***/
					, dinhNgocTraTeaTester.declare()
					, dinhNgocTraTeaTester.declareSuper()
					, dinhNgocTraTeaTester.declareGetName()
					, dinhNgocTraTeaTester.declareGetFlavor()
					, dinhNgocTraTeaTester.declareGetPrice()
					, dinhNgocTraTeaTester.operateGetName()
					, dinhNgocTraTeaTester.operateGetFlavor()
					, dinhNgocTraTeaTester.operateGetPrice()
					, dinhNgocTraTeaTester.excludeGetCategory()
					, dinhNgocTraTeaTester.operateGetCategory()

					/*** Lotus Tea ***/
					, lotusTeaTester.declare()
					, lotusTeaTester.declareSuper()
					, lotusTeaTester.declareGetName()
					, lotusTeaTester.declareGetFlavor()
					, lotusTeaTester.declareGetPrice()
					, lotusTeaTester.operateGetName()
					, lotusTeaTester.operateGetFlavor()
					, lotusTeaTester.operateGetPrice()
					, lotusTeaTester.excludeGetCategory()
					, lotusTeaTester.operateGetCategory()

					/*** Jasmine Tea ***/
					, jasmineTeaTester.declare()
					, jasmineTeaTester.declareSuper()
					, jasmineTeaTester.declareGetName()
					, jasmineTeaTester.declareGetFlavor()
					, jasmineTeaTester.declareGetPrice()
					, jasmineTeaTester.operateGetName()
					, jasmineTeaTester.operateGetFlavor()
					, jasmineTeaTester.operateGetPrice()
					, jasmineTeaTester.excludeGetCategory()
					, jasmineTeaTester.operateGetCategory()
			);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
