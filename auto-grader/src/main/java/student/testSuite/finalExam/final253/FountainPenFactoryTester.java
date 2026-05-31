package student.testSuite.finalExam.final253;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.solution.final253.section1.FountainPenFactory;
import student.testSuite.BaseTester;

public class FountainPenFactoryTester extends BaseTester {
	private PenTester penTester;
	private BrandTester brandTester;
	private ColorTester colorTester;

	public FountainPenFactoryTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.FOUNTAIN_PEN_FACTORY;
		super.getCorrespondingClass();
		super.solutionClass = FountainPenFactory.class;
	}

	public FountainPenFactoryTester brandTester(BrandTester brandTester) {
		this.brandTester = brandTester;
		return this;
	}

	public FountainPenFactoryTester colorTester(ColorTester colorTester) {
		this.colorTester = colorTester;
		return this;
	}
}
