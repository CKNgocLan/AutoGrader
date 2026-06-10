import java.util.List;

import model.TestCase;
import model.LabTestSuiteFactory;


public class TestSuiteRouter {
	public List<TestCase> invokeAllTests(String lab, String question) {
		try {
			return new LabTestSuiteFactory().createSuite(lab).getAllTests(question);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
