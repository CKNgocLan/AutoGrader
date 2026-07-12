package student.testSuite.exam.midterm254;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class DinhNgocTraTeaTester extends BaseTester {
	private GreenTeaTester greenTeaTester;
	private final String name = "Dinh Ngoc Tra Tea";
	private final String flavor = "Pure young bud aroma, gentle bitter";
	private final double price = 2500000;

	public DinhNgocTraTeaTester(GreenTeaTester parentTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.DINH_NGOC_TRA_TEA;
		super.getCorrespondingClass();
		this.greenTeaTester = parentTester;
	}

	/* declare */

	public TestCase declare() {
		return super.declare(defaultPoints);
	}
	
	public TestCase declareSuper() {
		try {
			return super.declareSuperClass(defaultPoints, greenTeaTester.getCorrespondingClass());
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
		return super.methodTester.declare(defaultPoints, className, greenTeaTester.teaTester.getName());
	}

	public TestCase operateGetName() {
		try {
			return super.methodTester.operationAsString(defaultPoints, className,
					greenTeaTester.teaTester.getName()
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
		return super.methodTester.declare(defaultPoints, className, greenTeaTester.teaTester.getFlavor());
	}

	public TestCase operateGetFlavor() {
		try {
			return super.methodTester.operationAsString(defaultPoints, className,
					greenTeaTester.teaTester.getFlavor()
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
		return super.methodTester.declare(defaultPoints, className, greenTeaTester.teaTester.getPrice());
	}

	public TestCase operateGetPrice() {
		try {
			return super.methodTester.operateAsDouble(defaultPoints, className,
					greenTeaTester.teaTester.getPrice()
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
			return super.methodTester.excludes(defaultPoints, className, greenTeaTester.teaTester.getCategory());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, name, e);
		}
	}

	public TestCase operateGetCategory() {
		try {
			return super.methodTester.operateAsEnum(defaultPoints
					, greenTeaTester.getCategory().config(greenTeaTester.getCorrespondingClass(), instantiate())
					, greenTeaTester.teaCategoryTester.getEnumClass());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, name, e);
		}
	}
}
