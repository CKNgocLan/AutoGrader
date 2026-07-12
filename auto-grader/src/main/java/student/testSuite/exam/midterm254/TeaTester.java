package student.testSuite.exam.midterm254;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.solution.midterm254.Tea;
import student.testSuite.BaseTester;
import student.util.GetterUtils;
import student.util.MethodUtils;
import student.util.TestCaseUtils;

public class TeaTester extends BaseTester {
	protected TeaCategoryTester teaCategoryTester;

	/**
	 * instantiate
	 */
	public TeaTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.TEA;
		super.getCorrespondingClass();
		super.solutionClass = Tea.class;
	}

	public TeaTester teaCategoryTester(TeaCategoryTester teaCategoryTester) {
		this.teaCategoryTester = teaCategoryTester;
		return this;
	}

	/**
	 * declare
	 */

	public TestCase declare() {
		return super.declareAsInterface(defaultPoints);
	}

	/*
	 * getName()
	 */
	protected TestingMethod getName() {
		return new TestingMethod(String.class, GetterUtils.getGetterName(FieldName.NAME));
	}

	public TestCase declareGetName() {
		return super.methodTester.declaredAsPublicAbstract(defaultPoints, className, getName());
	}

	/*
	 * getFlavor()
	 */
	protected TestingMethod getFlavor() {
		return new TestingMethod(String.class, GetterUtils.getGetterName(FieldName.FLAVOR));
	}

	public TestCase declareGetFlavor() {
		return super.methodTester.declaredAsPublicAbstract(defaultPoints, className, getFlavor());
	}

	/*
	 * getPrice()
	 */
	protected TestingMethod getPrice() {
		return new TestingMethod(double.class, GetterUtils.getGetterName(FieldName.PRICE));
	}

	public TestCase declareGetPrice() {
		return super.methodTester.declaredAsPublicAbstract(defaultPoints, className, getPrice());
	}

	/**
	 * getCategory()
	 * @throws TesterGotNoClassNameException 
	 * @throws ClassNotFoundException 
	 */
	protected TestingMethod getCategory() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingMethod(teaCategoryTester.getCorrespondingClass(), GetterUtils.getGetterName(FieldName.CATEGORY));
	}

	public TestCase declareGetCategory() {
		try {
			return super.methodTester.declaredAsPublicAbstract(defaultPoints, className, getCategory());
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	/**
	 * equals(Tea tea)
	 * @throws TesterGotNoClassNameException 
	 * @throws ClassNotFoundException 
	 */
	protected TestingMethod equals() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingMethod(boolean.class, MethodName.EQUALS, new TestingParameter(getCorrespondingClass()));
	}

	public TestCase declareEquals() {
		try {
			return super.methodTester.declaredAsPublicDefault(defaultPoints, className, equals());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}
}
