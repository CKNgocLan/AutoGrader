package student.testSuite.finalExam.final253;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.ITestCase;
import student.solution.final253.section1.Color;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class ColorTester extends BaseTester {
	/*
	 * instantiate ***************
	 */

	public ColorTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.COLOR;
		super.getCorrespondingClass();
		super.solutionClass = Color.class;
	}
	
	/*
	 * declaration
	 */
	public ITestCase declare() {
		return super.declareAsEnum(defaultPoints);
	}
	
	/*
	 * field ***************
	 */
	
	public ITestCase declareFields() {
		try {
			return super.fieldTester.declareInEnum(defaultPoints, className, super.getSolutionFields());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}
}
