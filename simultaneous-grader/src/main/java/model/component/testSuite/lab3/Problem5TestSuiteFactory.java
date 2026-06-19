package model.component.testSuite.lab3;

import common.constant.ProblemName;
import common.constant.TopicName;
import model.component.testSuite.TestSuite;
import model.component.testSuite.TestSuiteFactory;

public class Problem5TestSuiteFactory implements TestSuiteFactory {
	@Override
	public String getTopic() {
		return TopicName.L3;
	}

	@Override
	public String getProblem() {
		return ProblemName.P5;
	}

	@Override
	public TestSuite getTestSuite() {
		return new Problem5TestSuite();
	}

}
