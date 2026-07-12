package student.testSuite.exam.midterm254;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class LotusTeaTester extends BaseTester {
	private ScentedTeaTester scentedTeaTester;
	private final String name = "Lotus Tea";
	private final String flavor = "Delicate lotus fragrance, mild & calming sweet";
	private final double price = 1500000;
	public final double weight = 2.0;

	public LotusTeaTester(ScentedTeaTester parentTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.LOTUS_TEA;
		super.getCorrespondingClass();
		this.scentedTeaTester = parentTester;
	}

	/* declare */

	public TestCase declare() {
		return super.declare(defaultPoints);
	}
	
	public TestCase declareSuper() {
		try {
			return super.declareSuperClass(defaultPoints, scentedTeaTester.getCorrespondingClass());
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
		return super.methodTester.declare(defaultPoints, className, scentedTeaTester.teaTester.getName());
	}

	public TestCase operateGetName() {
		try {
			return super.methodTester.operationAsString(defaultPoints, className,
					scentedTeaTester.teaTester.getName()
							.config(super.getCorrespondingClass(), super.instantiate())
							.expectedValue(name)
					);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	/* getFlavor() */

	public TestCase declareGetFlavor() {
		return super.methodTester.declare(defaultPoints, className, scentedTeaTester.teaTester.getFlavor());
	}

	public TestCase operateGetFlavor() {
		try {
			return super.methodTester.operationAsString(defaultPoints, className,
					scentedTeaTester.teaTester.getFlavor()
							.config(super.getCorrespondingClass(), super.instantiate())
							.expectedValue(flavor)
					);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	/* getPrice() */

	public TestCase declareGetPrice() {
		return super.methodTester.declare(defaultPoints, className, scentedTeaTester.teaTester.getPrice());
	}

	public TestCase operateGetPrice() {
		try {
			return super.methodTester.operateAsDouble(defaultPoints, className,
					scentedTeaTester.teaTester.getPrice()
							.config(super.getCorrespondingClass(), super.instantiate())
							.expectedValue(price)
					);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	/* getCategory() */

	public TestCase excludeGetCategory() {
		try {
			return super.methodTester.excludes(defaultPoints, className, scentedTeaTester.teaTester.getCategory());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, name, e);
		}
	}

	public TestCase operateGetCategory() {
		try {
			return super.methodTester.operateAsEnum(defaultPoints
					, scentedTeaTester.getCategory().config(scentedTeaTester.getCorrespondingClass(), instantiate())
					, scentedTeaTester.teaCategoryTester.getEnumClass());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, name, e);
		}
	}
}
