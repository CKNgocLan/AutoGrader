package student.testSuite.finalExam.final253;

import student.constant.ClassName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.ITestCase;
import student.model.TestingMethod;
import student.solution.final253.section1.PenFactory;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class PenFactoryTester extends BaseTester {
//	private BrandTester brandTester;
//	private ColorTester colorTester;

	public PenFactoryTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.PEN_FACTORY;
		super.getCorrespondingClass();
		super.solutionClass = PenFactory.class;
	}
	
//	public PenFactoryTester brandTester(BrandTester brandTester) {
//		this.brandTester = brandTester;
//		return this;
//	}
//	
//	public PenFactoryTester colorTester(ColorTester colorTester) {
//		this.colorTester = colorTester;
//		return this;
//	}
	
	/*
	 * declaration
	 */
	public ITestCase declare() {
		return super.declareAsInterface(defaultPoints);
	}

	/*
	 * createPen
	 */
	private TestingMethod createPen() {
		return super.getFirstSolutionMethod(MethodName.CREATE_PEN);
	}

	public ITestCase declareCreatePen() {
		try {
			return super.methodTester.declaredAsPublicAbstract(defaultPoints, className, createPen());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}
}
