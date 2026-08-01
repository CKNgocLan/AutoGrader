package student.testSuite.exam.final254;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.testSuite.BaseTester;
import student.testSuite.exam.final254.TeaCategoryTester.EnumValue;
import student.util.TestCaseUtils;

public class PremiumMocCauTeaTester extends BaseTester {
	private TeaTester teaTester;
	private TeaCategoryTester teaCategoryTester;

	private final String name = "Premium Moc Cau Tea";
	private final EnumValue category = TeaCategoryTester.EnumValue.GREEN_TEA; 
	private final double price = 450_000;

	public PremiumMocCauTeaTester(TeaTester teaTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.PREMIUM_MOC_CAU_TEA;
		super.getCorrespondingClass();
		this.teaTester = teaTester;
	}
	
	public PremiumMocCauTeaTester teaCategoryTester(TeaCategoryTester teaCategoryTester) {
		this.teaCategoryTester = teaCategoryTester;
		return this;
	}

	/* declare */

	public TestCase declare() {
		return super.declare(defaultPoints);
	}
	
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

	/* instantiate */

	public Object instantiateTea() throws Exception {
		return instantiate();
	}

	/* getName() */

	public TestCase declareGetName() {
		return super.methodTester.declare(defaultPoints, className, teaTester.getName());
	}

	public TestCase operateGetName() {
		try {
			return super.methodTester.operationAsString(defaultPoints, className,
					teaTester.getName()
							.config(super.getCorrespondingClass(), super.instantiate())
							.expectedValue(name)
					);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	/* getPrice() */

	public TestCase declareGetPrice() {
		return super.methodTester.declare(defaultPoints, className, teaTester.getPrice());
	}

	public TestCase operateGetPrice() {
		try {
			return super.methodTester.operateAsDouble(defaultPoints, className,
					teaTester.getPrice()
							.config(super.getCorrespondingClass(), super.instantiate())
							.expectedValue(price)
					);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	public double priceAfterTax(double weight) {
		return this.price * weight * (1 + TaxTester.tax());
	}

	/* getCategory() */

	public TestCase declareGetCategory() {
		try {
			return super.methodTester.declare(defaultPoints, className, teaTester.getCategory());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, name, e);
		}
	}

	public TestCase operateGetCategory() {
		try {
			return super.methodTester.operateAsEnum(defaultPoints
					, teaTester.getCategory()
					.config(getCorrespondingClass(), instantiate())
					.expectedValue(teaCategoryTester.valueFrom(category.name()))
					, teaCategoryTester.getEnumClass());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, name, e);
		}
	}
}
