package student.testSuite.exam.final254;

import student.constant.ClassName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.model.TestingMethod;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class TeaFactoryTester extends BaseTester {
	private TeaTester teaTester;
	
	/**
	 * instantiate
	 */
	public TeaFactoryTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.TEA_FACTORY;
		super.getCorrespondingClass();
	}

	public TeaFactoryTester teaTester(TeaTester teaTester) {
		this.teaTester = teaTester;
		return this;
	}
	
	/**
	 * declare
	 */
	public TestCase declare() {
		return super.declareAsInterface(defaultPoints);
	}

	/*
	 * createTea()
	 */
	protected TestingMethod createTea() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingMethod(teaTester.getCorrespondingClass(), MethodName.CREATE_TEA);
	}

	public TestCase declareCreateTea() {
		try {
			return super.methodTester.declaredAsPublicAbstract(defaultPoints, className, createTea());
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}
}
