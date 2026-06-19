package model.component.testSuite.exam.midterm253;

import common.constant.ProblemName;
import common.constant.TopicName;
import model.component.testSuite.TestSuite;
import model.component.testSuite.TestSuiteFactory;

public class Section1TestSuiteFactory implements TestSuiteFactory {

	@Override
	public String getTopic() {
		return TopicName.MIDTERM_253;
	}

	@Override
	public String getProblem() {
		return ProblemName.SECTION_1;
	}

	@Override
	public TestSuite getTestSuite() {
		return Section1TestSuite.getInstance();
	}
}
