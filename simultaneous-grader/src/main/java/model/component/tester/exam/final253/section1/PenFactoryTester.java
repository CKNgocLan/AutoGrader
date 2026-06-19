package model.component.tester.exam.final253.section1;

import common.constant.ClassName;
import common.constant.FieldName;
import common.constant.MethodName;
import common.util.MethodUtils;
import common.util.TestCaseUtils;
import model.component.TestCase;
import model.component.tester.Tester;
import model.component.tester.exam.final253.section1.solution.PenFactory;
import model.element.TestingMethod;
import model.element.TestingParameter;
import model.exception.TesterGotNoClassNameException;

public class PenFactoryTester extends Tester {
	protected BrandTester brandTester;
	protected ColorTester colorTester;
	protected Class<?> penClass;

	public PenFactoryTester(Class<?> penClass) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.PEN_FACTORY;
		super.getCorrespondingClass();
		super.solutionClass = PenFactory.class;
		this.penClass = penClass;
	}

	public PenFactoryTester brandTester(BrandTester brandTester) {
		this.brandTester = brandTester;
		return this;
	}

	public PenFactoryTester colorTester(ColorTester colorTester) {
		this.colorTester = colorTester;
		return this;
	}
	
	/*
	 * declaration
	 */
	public TestCase declare() {
		return super.declareAsInterface(defaultPoints);
	}

	/*
	 * createPen
	 */
	protected TestingMethod createPen(Class<?> subclass, Object brand, String model, Object color, double price) throws ClassNotFoundException, TesterGotNoClassNameException {
		return createPen(subclass).updateParameter(createPenParameters(brand, model, color, price));
	}

	protected TestingMethod createPen(Class<?> subclass) {
		return MethodUtils.fromSolution(subclass, MethodName.CREATE_PEN);
	}

	protected TestingParameter[] createPenParameters(Object brand, String model, Object color, double price) throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingParameter[] {
				new TestingParameter(brandTester.getCorrespondingClass(), FieldName.BRAND, brand)
				, new TestingParameter(String.class, FieldName.MODEL, model)
				, new TestingParameter(colorTester.getCorrespondingClass(), FieldName.COLOR, color)
				, new TestingParameter(double.class, FieldName.PRICE, price)
		};
	}

	public TestCase declareCreatePen() {
		try {
			return declareCreatePen(getCorrespondingClass());
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	public TestCase declareCreatePen(Class<?> clazz) {
		try {
			return super.methodTester.declare(defaultPoints, clazz.getName(), createPen(clazz));
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	protected TestCase operateCreatePen(Class<?> subclass, TestingMethod method) {
		return super.methodTester.operationAsCasting(defaultPoints
				, method
				, penClass);
	}
}
