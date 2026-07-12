package student.testSuite.exam.midterm254;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.testSuite.BaseTester;

public class GreenTeaTester extends BaseTester {
	private TeaTester parentTester;

	public GreenTeaTester(TeaTester teaTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.GREEN_TEA;
		super.getCorrespondingClass();
		this.parentTester = teaTester;
	}

	/** declare */
	public TestCase declare() {
		return super.declare(defaultPoints);
	}

	/** declare super */
	public TestCase declareSuper() {
		try {
			return super.declareSuperClass(defaultPoints, parentTester.getCorrespondingClass());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	/** constructor */

//	public TestCase declareConstructor() {
//		try {
//			return super.checkConstructorDeclaration(defaultPoints);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return exceptionTestCase(e);
//		}
//	}

	public TestCase haveOnlyDefaultConstructor() {
		return super.classTester.haveOnlyDefaultConstructor(defaultPoints, className);
	}	
}
