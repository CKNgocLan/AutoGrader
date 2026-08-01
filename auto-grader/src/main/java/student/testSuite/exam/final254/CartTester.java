package student.testSuite.exam.final254;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Stream;

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
import student.util.ValueUtils;

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

	public TestCase operatePrintOrderedItems(TestingMethod... buildingMethods) {
		try {
			return super.methodTester.printListThenReturnVoid(defaultPoints, className,
					printOrderedItems().config(getCorrespondingClass(), toCartInstance(buildingMethods)));
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

	public TestCase operateGetTotalPriceAfterTax(TestingMethod... buildingMethods) {
		try {
			double expected = buildingMethods == null || buildingMethods.length == 0 ? 0 :
				Stream.of(buildingMethods)
					.mapToDouble(method -> ValueUtils.toDoublePrimitive(method.getExpectedValue())).sum();
			
			return super.methodTester.operateAsDouble(defaultPoints, className,
					getTotalPriceAfterTax()
							.config(super.getCorrespondingClass(), toCartInstance(buildingMethods))
							.expectedValue(expected)
			);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	private Object toCartInstance(TestingMethod... buildingMethods) throws Exception {
		return super.buildNestingInstance(builderTester.getCorrespondingClass()
				, builderTester.instantiateCart()
				, builderTester.buildMethod()
				, buildingMethods);
	}
}
