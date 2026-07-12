package student.testSuite.exam.midterm254;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.model.TestingMethod;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class GreenTeaTester extends BaseTester {
	protected TeaTester teaTester;
	protected TeaCategoryTester teaCategoryTester;

	public GreenTeaTester(TeaTester teaTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.GREEN_TEA;
		super.getCorrespondingClass();
		this.teaTester = teaTester;
	}

	public GreenTeaTester teaCategoryTester(TeaCategoryTester teaCategoryTester) {
		this.teaCategoryTester = teaCategoryTester;
		return this;
	}

	/* declare */
	public TestCase declare() {
		return super.declare(defaultPoints);
	}

	/* declare parent interface */
	public TestCase implementInterface() {
		try {
			return super.implementInterface(defaultPoints, teaTester.getCorrespondingClass());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	/* constructor */

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
		return teaTester.getCategory().expectedValue(teaCategoryTester.valueFrom(TeaCategoryTester.EnumValue.GREEN_TEA.name()));
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
