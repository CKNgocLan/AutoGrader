package student.testSuite.exam.final254;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class PremiumMocCauTeaFactoryTester extends BaseTester {
	private TeaFactoryTester teaFactoryTester;
	public final double defaultWeight = 1;

	public PremiumMocCauTeaFactoryTester(TeaFactoryTester teaFactoryTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.PREMIUM_MOC_CAU_TEA_FACTORY;
		super.getCorrespondingClass();
		this.teaFactoryTester = teaFactoryTester;
	}

	/* declare */

	public TestCase declare() {
		return super.declare(defaultPoints);
	}
	
	public TestCase implementInterface() {
		try {
			return super.declareSuperClass(defaultPoints, teaFactoryTester.getCorrespondingClass());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}
	
	/* constructor */

	public TestCase haveOnlyDefaultConstructor() {
		return super.classTester.haveOnlyDefaultConstructor(defaultPoints, className);
	}

	/* instantiate */

	public Object instantiateTeaFactory() throws Exception {
		return instantiate();
	}

	/**
	 * + createTea(): Tea
	 */
	public TestCase declareCreateTea() {
		try {
			return super.methodTester.declare(defaultPoints, className, teaFactoryTester.createTea());
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	public TestCase operateCreateTea() {
		return TestCaseUtils.failByDefault(defaultPoints, className);
	}
}
