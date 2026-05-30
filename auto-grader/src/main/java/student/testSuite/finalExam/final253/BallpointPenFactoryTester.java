package student.testSuite.finalExam.final253;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.solution.final253.section1.BallpointPenFactory;
import student.testSuite.BaseTester;

public class BallpointPenFactoryTester extends BaseTester {
	public BallpointPenFactoryTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.BALLPOINT_PEN_FACTORY;
		super.getCorrespondingClass();
		super.solutionClass = BallpointPenFactory.class;
	}
}
