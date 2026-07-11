package student.testSuite.labTestSuite.exam.midterm254;

import java.util.Arrays;
import java.util.List;

import student.model.ALabTestSuite;
import student.model.TestCase;
import student.testSuite.exam.midterm254.TeaCategoryTester;
import student.testSuite.exam.midterm254.TeaTester;

public class ExamMidtermTestSuite254 extends ALabTestSuite {
	@Override
	public List<TestCase> getAllTests(String section) {
		try {
			TeaCategoryTester teaCategoryTester = new TeaCategoryTester();
			TeaTester teaTester = new TeaTester().teaCategoryTester(teaCategoryTester);

			return Arrays.asList(
					/*** Tea Category ***/
					teaCategoryTester.declare()
					, teaCategoryTester.declareFields()

					/*** TeaTester ***/
					, teaTester.declare()
					, teaTester.declareGetName()
					, teaTester.declareGetFlavor()
					, teaTester.declareGetPrice()
					, teaTester.declareGetCategory()
					, teaTester.declareEquals()
			);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
