package student.testSuite.exam.midterm254;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.model.TestingField;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class OrderedItemTester extends BaseTester {
	protected TeaTester teaTester;

	public OrderedItemTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.ORDERED_ITEM;
		super.getCorrespondingClass();
	}

	public OrderedItemTester teaTester(TeaTester teaTester) {
		this.teaTester = teaTester;
		return this;
	}

	/**
	 * declare
	 */

	public TestCase declare() {
		return super.declare(defaultPoints);
	}

	/**
	 * field
	 */

	private TestingField[] fields() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingField[] {
				new TestingField(teaTester.getCorrespondingClass(), FieldName.TEA)
				, new TestingField(double.class, FieldName.WEIGHT)
		};
	}
	
	public TestCase declareFields() {
		try {
			return super.fieldTester.checkDeclarations(defaultPoints, className, fields());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	/**
	 * constructor
	 */

	public TestCase declareConstructor() {
		try {
			return super.checkConstructorDeclaration(defaultPoints
					, teaTester.getCorrespondingClass()
					, double.class);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	public TestCase operateConstructor() {
		try {
			return super.operateConstructor(defaultPoints,
					new TestingParameter(teaTester.getCorrespondingClass(), FieldName.TEA)
					, new TestingParameter(double.class, FieldName.WEIGHT)
					);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	/**
	 * getPriceAfterTax()
	 */

	private TestingMethod getPriceAfterTax() {
		return new TestingMethod(double.class, MethodName.GET_PRICE_AFTER_TAX);
	}

	public TestCase declareGetPriceAfterTax() {
		return super.methodTester.declare(defaultPoints, className, getPriceAfterTax());
	}
}
