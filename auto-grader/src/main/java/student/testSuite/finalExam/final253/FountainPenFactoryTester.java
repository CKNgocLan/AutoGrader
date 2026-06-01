package student.testSuite.finalExam.final253;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.solution.final253.section1.FountainPenFactory;
import student.testSuite.BaseTester;

public class FountainPenFactoryTester extends BaseTester {
	private PenFactoryTester penFactoryTester;

	public FountainPenFactoryTester(PenFactoryTester penFactoryTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.FOUNTAIN_PEN_FACTORY;
		super.getCorrespondingClass();
		super.solutionClass = FountainPenFactory.class;
		this.penFactoryTester = penFactoryTester;
	}
}
