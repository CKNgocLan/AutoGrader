package model.component.testSuite.exam.final253;

import common.constant.ProblemName;
import common.constant.TopicName;
import model.component.testSuite.TestSuite;
import model.component.testSuite.TestSuiteFactory;

public class Section2TestSuiteFactory implements TestSuiteFactory {
	@Override
	public String getTopic() {
		return TopicName.FINAL_253;
	}

	@Override
	public String getProblem() {
		return ProblemName.SECTION_2;
	}

	@Override
	public TestSuite getTestSuite() {
		return Section2TestSuite.getInstance();
	}

}