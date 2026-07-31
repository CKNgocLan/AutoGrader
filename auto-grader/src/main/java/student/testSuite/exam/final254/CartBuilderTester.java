package student.testSuite.exam.final254;

import java.lang.reflect.Field;
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
	 * + add(orderedItem: OrderedItem): CartBuilder
	 * 
	 * @throws TesterGotNoClassNameException
	 * @throws ClassNotFoundException
	 */
	private TestingMethod add() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingMethod(getCorrespondingClass(), MethodName.ADD,
				new TestingParameter(orderedItemTester.getCorrespondingClass(), FieldName.ORDERED_ITEM));
	}

	private TestingMethod addMethod(Object concreteTeaFactoryInstance, double weight) throws Exception {
		return new TestingMethod(getCorrespondingClass(), MethodName.ADD,
				new TestingParameter(orderedItemTester.getCorrespondingClass(),
						FieldName.ORDERED_ITEM,
						orderedItemTester.instantiateItem(concreteTeaFactoryInstance, weight)));
	}

	public TestCase declareAddOrderedItem() {
		try {
			return super.methodTester.declare(defaultPoints, className, add());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}


	public TestCase operateAddOrderedItem(Object concreteTeaFactoryTester, double weight) {
		try {
			Class<?> builderClass = getCorrespondingClass();
			Object builderInstance = instantiate();
			Class<?> orderedItemClass = orderedItemTester.getCorrespondingClass();
			Object orderedItemInstance = orderedItemTester.instantiateItem(concreteTeaFactoryTester, weight);

			return super.methodTester.addElement(defaultPoints
					, addMethod(concreteTeaFactoryTester, weight).config(builderClass, builderInstance)
					, new TestingParameter(orderedItemClass, FieldName.ORDERED_ITEMS, orderedItemInstance)
			);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, nestingClassName, e);
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

	public TestCase operateBuild() {
		return TestCaseUtils.failByDefault(defaultPoints, className);
	}
}
