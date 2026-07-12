package student.testSuite.exam.midterm254;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.model.TestingMethod;
import student.testSuite.BaseTester;

public class ScentedTeaTester extends BaseTester {
	protected TeaTester teaTester;
	protected TeaCategoryTester teaCategoryTester;

	public ScentedTeaTester(TeaTester teaTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.SCENTED_TEA;
		super.getCorrespondingClass();
		this.teaTester = teaTester;
	}

	public ScentedTeaTester teaCategoryTester(TeaCategoryTester teaCategoryTester) {
		this.teaCategoryTester = teaCategoryTester;
		return this;
	}

	/** declare */
	public TestCase declare() {
		return super.declare(defaultPoints);
	}

	/** declare super */
	public TestCase implementInterface() {
		try {
			return super.implementInterface(defaultPoints, teaTester.getCorrespondingClass());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	/** constructor */

	public TestCase haveOnlyDefaultConstructor() {
		return super.classTester.haveOnlyDefaultConstructor(defaultPoints, className);
	}

	/**
	 * getCategory()
	 * 
	 * @throws TesterGotNoClassNameException
	 * @throws ClassNotFoundException
	 */
	protected TestingMethod getCategory() throws ClassNotFoundException, TesterGotNoClassNameException {
		return teaTester.getCategory().expectedValue(teaCategoryTester.valueFrom(TeaCategoryTester.EnumValue.SCENTED_TEA.name()));
	}
}
