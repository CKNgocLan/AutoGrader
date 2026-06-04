package student.testSuite.finalExam.final253.section2;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.ITestCase;
import student.solution.final253.section2.Pen;
import student.testSuite.BaseTester;

public class PenBuilderTester extends BaseTester {

	/*
	 * instantiate ***************
	 */

	public PenBuilderTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.PEN_BUILDER;
		super.getCorrespondingClass();
		super.solutionClass = Pen.PenBuilder.class;
	}
	
	/*
	 * declare
	 */

	public ITestCase declare() {
		return super.declareAsInnerClass(defaultPoints, ClassName.PEN);
	}

	/*
	 * field
	 */

	public ITestCase declareFields() {
		return super.fieldTester.checkDeclarations(defaultPoints, className, getSolutionFields());
	}
}
