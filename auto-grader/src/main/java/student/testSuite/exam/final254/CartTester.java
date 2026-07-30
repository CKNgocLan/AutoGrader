package student.testSuite.exam.final254;

import java.lang.reflect.Modifier;
import java.util.List;

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

public class CartTester extends BaseTester {
	private OrderedItemTester orderedItemTester;
	private CartBuilderTester builderTester;

	public CartTester(CartBuilderTester builderTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.CART;
		super.getCorrespondingClass();
		this.builderTester = builderTester;
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
		return new TestingField[] { new TestingField(List.class, FieldName.ORDERED_ITEMS) };
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
			return super.declareConstructorAsPrivate(defaultPoints, builderTester.getCorrespondingClass());
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	public TestCase haveOnlyOneConstructor() {
		try {
			return super.classTester.haveOnlyOneConstructor(defaultPoints, className, new TestingParameter(builderTester.getCorrespondingClass()));
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	/**
	 * - validate(orderedItem: OrderedItem): void
	 * @throws TesterGotNoClassNameException 
	 * @throws ClassNotFoundException 
	 */
	private TestingMethod validate() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingMethod(Modifier.PRIVATE, boolean.class, MethodName.VALIDATE,
				new TestingParameter(orderedItemTester.getCorrespondingClass(), FieldName.ORDERED_ITEM)
		).asStatic();
	}

	public TestCase declareValidate() {
		try {
			return super.methodTester.declaredAsPrivateSpecialModifers(defaultPoints, className, validate());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	public TestCase operateValidate() {
		try {
			return TestCaseUtils.failByDefault(defaultPoints, className);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}
	
	/**
	 * printOrderedItems()
	 */

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

	public TestCase operatePrintOrderedItems(Object... orderedItems) {
		try {
			return TestCaseUtils.failByDefault(defaultPoints, className);
//			Object cartInstance = instantiate();
//			TestingMethod method = printOrderedItems().config(getCorrespondingClass(), cartInstance);
//
//			return super.methodTester.printListThenReturnVoid(defaultPoints, className, method);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	/**
	 * getTotalPriceAfterTax()
	 */

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

	public TestCase operateGetTotalPriceAfterTax(double expected) {
		try {
			return TestCaseUtils.failByDefault(defaultPoints, className);
//			return super.methodTester.operateAsDouble(defaultPoints, className, getTotalPriceAfterTax().config(getCorrespondingClass(), instantiate()).expectedValue(expected));
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	public TestCase operateGetTotalPriceAfterTax(double expected, Object... orderedItemList) {
		return TestCaseUtils.failByDefault(defaultPoints, className);
	}
}
