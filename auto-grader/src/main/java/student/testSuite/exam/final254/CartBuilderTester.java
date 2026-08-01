package student.testSuite.exam.final254;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
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
import student.util.ValueUtils;

public class CartBuilderTester extends BaseTester {
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

	public Object instantiateCart() throws Exception {
		return instantiate();
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

	public TestingMethod add(Object concreteTeaFactoryInstance, double weight) throws Exception {
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

	public TestCase operateAddOrderedItem(Object concreteTeaFactoryInstance, double weight) {
		try {
			Class<?> builderClass = getCorrespondingClass();
			Object builderInstance = instantiate();
			Class<?> orderedItemClass = orderedItemTester.getCorrespondingClass();
			Object orderedItemInstance = orderedItemTester.instantiateItem(concreteTeaFactoryInstance, weight);

			return super.methodTester.addElement(defaultPoints
					, add(concreteTeaFactoryInstance, weight).config(builderClass, builderInstance)
					, new TestingParameter(orderedItemClass, FieldName.ORDERED_ITEMS, orderedItemInstance)
			);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, nestingClassName, e);
		}
	}

	public TestCase operateAddNulldOrderedItem() {
		try {
			Class<?> builderClass = getCorrespondingClass();
			Object builderInstance = instantiate();
			Class<?> orderedItemClass = orderedItemTester.getCorrespondingClass();
			Object orderedItemInstance = null;

			return super.methodTester.invalidToAddElement(defaultPoints
					, add().config(builderClass, builderInstance)
					, new TestingParameter(orderedItemClass, FieldName.ORDERED_ITEMS, orderedItemInstance)
			);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, nestingClassName, e);
		}
	}

	public TestCase operateAddOrderedItemWithoutWeight(Object concreteTeaFactoryInstance) {
		try {
			Class<?> builderClass = getCorrespondingClass();
			Object builderInstance = instantiate();
			Class<?> orderedItemClass = orderedItemTester.getCorrespondingClass();
			Object orderedItemInstance = orderedItemTester.instantiateItem(concreteTeaFactoryInstance, 0);

			return super.methodTester.invalidToAddElement(defaultPoints
					, add().config(builderClass, builderInstance)
					, new TestingParameter(orderedItemClass, FieldName.ORDERED_ITEMS, orderedItemInstance)
			);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, nestingClassName, e);
		}
	}

	public TestCase operateAddOrderedItemDuplicated(Object concreteTeaFactoryInstance, double... weights) {
		try {
			Class<?> builderClass = getCorrespondingClass();
			Object builderInstance = instantiate();
			Class<?> orderedItemClass = orderedItemTester.getCorrespondingClass();
			
			for (double weight : weights) {
				super.buildInstance(builderClass, builderInstance, add(concreteTeaFactoryInstance, weight));
			}
			
			List<?> actualOrderedItemInstances = List.class.cast(super.getFieldAsAccessible(FieldName.ORDERED_ITEMS).get(builderInstance));
			Field weightField = super.getFieldAsAccessible(orderedItemClass, FieldName.WEIGHT);

			double totalActualWeight = 0;
			for (Object itemInstance : actualOrderedItemInstances) {
				totalActualWeight = ValueUtils.toDoublePrimitive(weightField.get(itemInstance));
			}
			
			double totalExpectedTWeight = Arrays.stream(weights).sum();
			
			return totalActualWeight == totalExpectedTWeight
					? passMethodOperation(MethodName.ADD)
					: failMethodOperation(MethodName.ADD);

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
	public TestingMethod buildMethod() throws Exception {
		return new TestingMethod(super.retriveClass(nestingClassName), MethodName.BUILD);
	}

	public TestCase declareBuild() {
		try {
			return super.methodTester.declare(defaultPoints, className, buildMethod());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	public TestCase operateBuild(TestingMethod... buildingMethods) {
		try {
			TestingMethod method = buildMethod();

			Object builderInstance = super.instantiate();
			Class<?> builderClass = super.getCorrespondingClass();

			Class<?> nestingClass = super.retriveClass(nestingClassName);
			Object nestingInstance = super.buildNestingInstance(builderClass, builderInstance, method, buildingMethods);

			try {
				nestingClass.cast(nestingInstance);
				
				// TODO
//				for (TestingMethod buildingMethod : buildingMethods) {
//				}
			} catch (ClassCastException e) {
				return failMethodOperation(method.getName());
			}
			
			return passMethodOperation(method.getName());
		} catch (Exception e) {
			return TestCaseUtils.errorTestcase(defaultPoints, nestingClassName, e);
		}
	}
}
