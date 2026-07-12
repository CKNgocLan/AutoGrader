package student.testSuite.exam.midterm254;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.model.TestingMethod;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class ScentedTeaTester extends BaseTester {
	private TeaTester parentTester;
	private TeaCategoryTester teaCategoryTester;

	public ScentedTeaTester(TeaTester teaTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.SCENTED_TEA;
		super.getCorrespondingClass();
		this.parentTester = teaTester;
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
			return super.implementInterface(defaultPoints, parentTester.getCorrespondingClass());
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
	private TestingMethod getCategory() throws ClassNotFoundException, TesterGotNoClassNameException {
		return parentTester.getCategory().expectedValue(teaCategoryTester.valueFrom(TeaCategoryTester.EnumValue.SCENTED_TEA.name()));
	}

	@SuppressWarnings("unchecked")
	@Deprecated
	public TestCase operateGetCategory() {
		try {
			return super.methodTester.operateAsEnum(defaultPoints, getCategory(), (Class<? extends Enum<?>>) teaCategoryTester.getCorrespondingClass());
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}
}
