package model.component.testSuite.factory;

import common.constant.ProblemName;
import common.constant.TopicName;
import model.component.testSuite.TestSuite;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.concrete.Lab3Problem1TestSuite;

public class Lab3Problem1TestSuiteFactory implements TestSuiteFactory {
	@Override
	public String getTopic() {
		return TopicName.L3;
	}

	@Override
	public String getProblem() {
		return ProblemName.P1;
	}

	@Override
	public TestSuite getTestSuite() {
		return Lab3Problem1TestSuite.getInstance();
	}

}
