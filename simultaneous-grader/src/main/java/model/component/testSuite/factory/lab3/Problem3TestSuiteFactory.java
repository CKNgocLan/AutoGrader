package model.component.testSuite.factory.lab3;

import common.constant.ProblemName;
import common.constant.TopicName;
import model.component.testSuite.TestSuite;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.concrete.lab3.Lab3Problem3TestSuite;

public class Problem3TestSuiteFactory implements TestSuiteFactory {
	@Override
	public String getTopic() {
		return TopicName.L3;
	}

	@Override
	public String getProblem() {
		return ProblemName.P3;
	}

	@Override
	public TestSuite getTestSuite() {
		return new Lab3Problem3TestSuite();
	}

}
