package student.testSuite.exam.midterm254;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.model.TestingMethod;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class MocCauTeaTester extends BaseTester {
	private GreenTeaTester parentTester;
	private final String name = "Moc Cau Tea";

	public MocCauTeaTester(GreenTeaTester parentTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.MOC_CAU_TEA;
		super.getCorrespondingClass();
		this.parentTester = parentTester;
	}

	/* declare */

	public TestCase declare() {
		return super.declare(defaultPoints);
	}
	
	public TestCase declareSuper() {
		try {
			return super.declareSuperClass(defaultPoints, parentTester.getCorrespondingClass());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}
	
	/* constructor */

	public TestCase haveOnlyDefaultConstructor() {
		return super.classTester.haveOnlyDefaultConstructor(defaultPoints, className);
	}

	/* getName() */

	public TestCase declareGetName() {
		return super.methodTester.declare(defaultPoints, className, parentTester.parentTester.getName());
	}

	public TestCase operateGetName() {
		try {
			TestingMethod method = parentTester.parentTester.getName()
					.config(getCorrespondingClass(), super.instantiate())
					.expectedValue(name);
			return super.methodTester.operationAsString(defaultPoints, className, method);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	/* getFlavor() */

	public TestCase declareGetFlavor() {
		return super.methodTester.declare(defaultPoints, className, parentTester.parentTester.getFlavor());
	}

	/* getPrice() */

	public TestCase declareGetPrice() {
		return super.methodTester.declare(defaultPoints, className, parentTester.parentTester.getPrice());
	}
}
