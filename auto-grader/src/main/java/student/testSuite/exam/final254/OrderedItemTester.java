package student.testSuite.exam.final254;

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
	protected TeaFactoryTester teaFactoryTester;

	public OrderedItemTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.ORDERED_ITEM;
		super.getCorrespondingClass();
	}

	public OrderedItemTester teaTester(TeaTester teaTester) {
		this.teaTester = teaTester;
		return this;
	}

	public OrderedItemTester teaFactoryTester(TeaFactoryTester teaFactoryTester) {
		this.teaFactoryTester = teaFactoryTester;
		return this;
	}

	/*
	 * instantiate
	 */
	private TestingParameter[] constructorParameter() throws ClassNotFoundException, TesterGotNoClassNameException {
		return constructorParameter(null, 0);
	}

	private TestingParameter[] constructorParameter(Object teaFactoryInstance, double weight)
			throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingParameter[] {
				new TestingParameter(teaFactoryTester.getCorrespondingClass(), FieldName.TEA_FACTORY, teaFactoryInstance),
				new TestingParameter(double.class, FieldName.WEIGHT, weight) };
	}

	public Object instantiateItem(Object teaInstance, double weight) throws Exception {
		return instantiateWithArgs(constructorParameter(teaInstance, weight));
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
		return new TestingField[] { new TestingField(teaTester.getCorrespondingClass(), FieldName.TEA),
				new TestingField(double.class, FieldName.WEIGHT) };
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
			return super.checkConstructorDeclaration(defaultPoints, teaFactoryTester.getCorrespondingClass(),
					double.class);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	public TestCase operateConstructor() {
		try {
			return super.operateConstructor(defaultPoints, constructorParameter());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

//	/**
//	 * getTea(): Tea
//	 * @throws TesterGotNoClassNameException 
//	 * @throws ClassNotFoundException 
//	 */
//	protected TestingMethod getTea() throws ClassNotFoundException, TesterGotNoClassNameException {
//		return new TestingMethod(teaTester.getCorrespondingClass(), GetterUtils.getGetterName(FieldName.TEA));
//	}
//
//	public TestCase declareGetTea() {
//		try {
//			return super.methodTester.declare(defaultPoints, className, getTea());
//		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
//			e.printStackTrace();
//			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
//		}
//	}

	/**
	 * addWeight(additionalWeight: double): void
	 */
	protected TestingMethod addWeight() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingMethod(void.class, MethodName.ADD_WEIGHT,
				new TestingParameter(double.class, FieldName.ADDITIONAL_WEIGHT));
	}

	public TestCase declareAddWeight() {
		try {
			return super.methodTester.declare(defaultPoints, className, addWeight());
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
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

	/**
	 * equals(Tea tea)
	 * @throws TesterGotNoClassNameException 
	 * @throws ClassNotFoundException 
	 */
	protected TestingMethod equalsObject() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingMethod(boolean.class, MethodName.EQUALS, new TestingParameter(Object.class, FieldName.ITEM_OBJECT));
	}

	public TestCase declareEqualsObject() {
		try {
			return super.methodTester.declaredAsPublicDefault(defaultPoints, className, equalsObject());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}
	
	/**
	 * equals(Tea tea)
	 * @throws TesterGotNoClassNameException 
	 * @throws ClassNotFoundException 
	 */
	protected TestingMethod equalsOrderedItem() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingMethod(boolean.class, MethodName.EQUALS, new TestingParameter(getCorrespondingClass(), FieldName.ORDERED_ITEM));
	}
	
	public TestCase declareEqualsOrderedItem() {
		try {
			return super.methodTester.declaredAsPublicDefault(defaultPoints, className, equalsOrderedItem());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}
}
