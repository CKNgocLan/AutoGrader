package model.component.testSuite.factory.exam.midterm253;

import common.constant.ProblemName;
import common.constant.TopicName;
import model.component.testSuite.TestSuite;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.concrete.exam.midterm253.Section1TestSuite;

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
