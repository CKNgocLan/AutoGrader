package student.testSuite.exam.midterm254;

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
//	private MocCauTeaTester mocCauTeaTester;

	public CartTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.CART;
		super.getCorrespondingClass();
	}

	public CartTester orderedItemTester(OrderedItemTester orderedItemTester) {
		this.orderedItemTester = orderedItemTester;
		return this;
	}

//	public CartTester mocCauTeaTester(MocCauTeaTester mocCauTeaTester) {
//		this.mocCauTeaTester = mocCauTeaTester;
//		return this;
//	}

	/*
	 * declare
	 */

	public TestCase declare() {
		return super.declare(defaultPoints);
	}

	/*
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

	public TestCase operateAddOrderedItem() {
		try {
			return super.methodTester.addNull(defaultPoints
					, addOrderedItem().config(getCorrespondingClass(), instantiate())
					, new TestingParameter(orderedItemTester.getCorrespondingClass(), FieldName.ORDERED_ITEMS, null));
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	public TestCase operateAddOrderedItem(Object teaInstance, double weight) {
		try {
			return super.methodTester.addElement(defaultPoints
					, addOrderedItem().config(getCorrespondingClass(), instantiate())
					, new TestingParameter(orderedItemTester.getCorrespondingClass(), FieldName.ORDERED_ITEMS,
							orderedItemTester.instantiateItem(teaInstance, weight)));
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}
	
	/*
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
			Object cartInstance = instantiate();
			TestingMethod method = printOrderedItems().config(getCorrespondingClass(), cartInstance);

			return super.methodTester.printListThenReturnVoid(defaultPoints, className, method);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	/*
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
			return super.methodTester.operateAsDouble(defaultPoints, className, getTotalPriceAfterTax().config(getCorrespondingClass(), instantiate()).expectedValue(expected));
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	public TestCase operateGetTotalPriceAfterTax(double expected, Object... orderedItemList) {
		try {
			Class<?> clazz = getCorrespondingClass();
			Object cartInstance = instantiate();

			TestingMethod addMethod = addOrderedItem().config(clazz, cartInstance);

			for (Object item : orderedItemList) {
				addMethod.overrideParameter(new TestingParameter(orderedItemTester.getCorrespondingClass(), FieldName.ORDERED_ITEMS, item));
				addMethod.returnVoid();
			}

			return super.methodTester.operateAsDouble(defaultPoints, className,
					getTotalPriceAfterTax().config(clazz, cartInstance).expectedValue(expected));
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}
}
