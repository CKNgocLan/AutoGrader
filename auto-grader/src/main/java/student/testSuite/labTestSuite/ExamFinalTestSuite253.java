package student.testSuite.labTestSuite;

import java.util.Arrays;
import java.util.List;

import student.exception.TesterGotNoClassNameException;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.testSuite.finalExam.final253.BrandTester;
import student.testSuite.finalExam.final253.ColorTester;
import student.testSuite.finalExam.final253.PenFactoryTester;

public class ExamFinalTestSuite253 extends ALabTestSuite {
	@Override
	public List<ITestCase> getAllTests(String question) {
		try {
			PenFactoryTester penFactoryTester = new PenFactoryTester();
			ColorTester colorTester = new ColorTester();
			BrandTester brandTester = new BrandTester();

			return Arrays.asList(
					// color
					colorTester.declare()
					, colorTester.declareFields()

					// brand
					, brandTester.declare()
					, brandTester.declareFields()

					// penFactory
					, penFactoryTester.declare()
					, penFactoryTester.declareCreatePen()

			);
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			return null;
		}
	}
}
