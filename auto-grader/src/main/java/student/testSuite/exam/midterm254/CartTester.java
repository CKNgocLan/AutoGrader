package student.testSuite.exam.midterm254;

import java.lang.reflect.Constructor;
import java.util.List;

import student.constant.ClassName;
import student.constant.Feedback;
import student.constant.FieldName;
import student.constant.MethodName;
import student.constant.TestcaseType;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.model.TestingField;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class CartTester extends BaseTester {
	private OrderedItemTester orderedItemTester;

	public CartTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.CART;
		super.getCorrespondingClass();
	}

	public CartTester orderedItemTester(OrderedItemTester orderedItemTester) {
		this.orderedItemTester = orderedItemTester;
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
				new TestingField(List.class, FieldName.ORDERED_ITEMS)
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
			return super.checkConstructor(defaultPoints);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	public TestCase haveOnlyOneConstructor() {
		return super.classTester.haveOnlyOneConstructor(defaultPoints, className);
	}

	/**
	 * add(OrderedItem)
	 * @throws TesterGotNoClassNameException 
	 * @throws ClassNotFoundException 
	 */

	private TestingMethod addOrderedItem() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingMethod(void.class, MethodName.ADD, new TestingParameter(orderedItemTester.getCorrespondingClass()));
	}

	public TestCase declareAddOrderedItem() {
		try {
			return super.methodTester.declare(defaultPoints, className, addOrderedItem());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	private TestingMethod printOrderedItems() {
		return new TestingMethod(void.class, MethodName.PRINT_ORDERED_ITEMS);
	}

	public TestCase declarePrintOrderedItems() {
		try {
			return super.methodTester.declare(defaultPoints, className, printOrderedItems());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	private TestingMethod getTotalPriceAfterTax() {
		return new TestingMethod(double.class, MethodName.GET_TOTAL_PRICE_AFTER_TAX);
	}

	public TestCase declareGetTotalPriceAfterTax() {
		try {
			return super.methodTester.declare(defaultPoints, className, getTotalPriceAfterTax());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}
}
