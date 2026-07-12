package student.testSuite.exam.midterm254;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class JasmineTeaTester extends BaseTester {
	private ScentedTeaTester scentedTeaTester;
	private final String name = "Jasmine Tea";
	private final String flavor = "Sweet jasmine floral, light & refreshing";
	private final double price = 600000;
	public final double weight = 1.0;

	public JasmineTeaTester(ScentedTeaTester parentTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.JASMINE_TEA;
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

	/* instantiate */

	public Object instantiateTea() throws Exception {
		return instantiate();
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
