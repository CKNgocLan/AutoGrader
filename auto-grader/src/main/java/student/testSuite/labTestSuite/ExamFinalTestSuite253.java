package student.testSuite.labTestSuite;

import java.util.Arrays;
import java.util.List;

import student.exception.TesterGotNoClassNameException;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.testSuite.finalExam.final253.PenFactoryTester;

public class ExamFinalTestSuite253 extends ALabTestSuite {
	@Override
	public List<ITestCase> getAllTests(String question) {
		try {
			PenFactoryTester penFactoryTester = new PenFactoryTester();
			return Arrays.asList(
					penFactoryTester.declare()
					, penFactoryTester.declareCreatePen()
			);
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			return null;
		}
	}
}
