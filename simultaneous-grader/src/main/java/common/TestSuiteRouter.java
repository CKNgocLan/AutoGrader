package common;

import java.util.List;

import model.ITestCase;
import model.LabTestSuiteFactory;


public class TestSuiteRouter {
	public List<ITestCase> invokeAllTests(String lab, String question) {
		try {
			return new LabTestSuiteFactory().createSuite(lab).getAllTests(question);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
