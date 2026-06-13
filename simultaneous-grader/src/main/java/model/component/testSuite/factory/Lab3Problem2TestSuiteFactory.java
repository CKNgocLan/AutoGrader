package model.component.testSuite.factory;

import model.component.testSuite.TestSuite;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.concrete.Lab3Problem2TestSuite;

public class Lab3Problem2TestSuiteFactory implements TestSuiteFactory {

	@Override
	public TestSuite createTestSuite() {
		return new Lab3Problem2TestSuite();
	}

}
