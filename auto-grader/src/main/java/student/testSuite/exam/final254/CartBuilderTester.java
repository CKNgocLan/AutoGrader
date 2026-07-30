package student.testSuite.exam.final254;

import java.util.ArrayList;
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
import student.util.ClassUtils;
import student.util.TestCaseUtils;

public class CartBuilderTester extends BaseTester {
	private ArrayList<TestingParameter> args = new ArrayList<TestingParameter>();
	private String nestingClassName = ClassName.CART;
	private OrderedItemTester orderedItemTester;

	/**
	 * instantiate
	 */

	public CartBuilderTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassUtils.toInnerClassName(ClassName.CART, ClassName.CART_BUILDER);
		super.getCorrespondingClass();
	}

	public CartBuilderTester orderedItemTester(OrderedItemTester orderedItemTester) {
		this.orderedItemTester = orderedItemTester;
		return this;
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
	
	/**
	 * declare
	 */

	public TestCase declare() {
		return super.declareAsInnerStaticClass(defaultPoints);
	}

	/**
	 * field
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws TesterGotNoClassNameException
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
	 * builder
	 * 
	 * @param brand
	 * @return
	 * @throws TesterGotNoClassNameException
	 * @throws ClassNotFoundException
	 */

	public ArrayList<TestingParameter> add(Object orderedItem)
			throws ClassNotFoundException, TesterGotNoClassNameException {
		this.args.add(
				new TestingParameter(orderedItemTester.getCorrespondingClass(), FieldName.ORDERED_ITEM, orderedItem));
		return args;
	}

	/**
	 * + add(orderedItem: OrderedItem): CartBuilder
	 * 
	 * @throws TesterGotNoClassNameException
	 * @throws ClassNotFoundException
	 */
	private TestingMethod addOrderedItem() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingMethod(getCorrespondingClass(), MethodName.ADD,
				new TestingParameter(orderedItemTester.getCorrespondingClass(), FieldName.ORDERED_ITEM));
	}

	public TestCase declareAddOrderedItem() {
		try {
			return super.methodTester.declare(defaultPoints, className, addOrderedItem());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	/**
	 * + build(): Cart
	 * 
	 * @return
	 * @throws Exception
	 */
	public TestingMethod build() throws Exception {
		return new TestingMethod(retriveClass(nestingClassName), MethodName.BUILD);
	}

	public TestCase declareBuild() {
		try {
			return super.methodTester.declare(defaultPoints, className, build());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	public TestCase operateBuild(String brand, String model, double price) {
		return TestCaseUtils.passByDefault(defaultPoints, className);
	}
}
