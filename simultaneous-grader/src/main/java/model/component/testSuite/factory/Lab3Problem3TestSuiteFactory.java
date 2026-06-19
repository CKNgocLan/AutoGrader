package model.component.testSuite.factory;

import common.constant.ProblemName;
import common.constant.TopicName;
import model.component.testSuite.TestSuite;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.concrete.Lab3Problem3TestSuite;

public class Lab3Problem3TestSuiteFactory implements TestSuiteFactory {
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
