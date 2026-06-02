package student.testSuite.finalExam.final253.section1;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.ITestCase;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.solution.final253.section1.PenFactory;
import student.testSuite.BaseTester;
import student.util.MethodUtils;
import student.util.TestCaseUtils;

public class PenFactoryTester extends BaseTester {
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
	public ITestCase declare() {
		return super.declareAsInterface(defaultPoints);
	}

	/*
	 * createPen
	 */
	protected TestingMethod createPen(Class<?> subclass, Object brand, String model, Object color, double price) throws ClassNotFoundException, TesterGotNoClassNameException {
		return createPen(subclass).addParameter(createPenParameters(brand, model, color, price));
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

	public ITestCase declareCreatePen() {
		try {
			return declareCreatePen(getCorrespondingClass());
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	public ITestCase declareCreatePen(Class<?> clazz) {
		try {
			return super.methodTester.checkExistence(defaultPoints, clazz.getName(), createPen(clazz));
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	protected ITestCase operateCreatePen(Class<?> subclass, TestingMethod method) {
		return super.methodTester.operationAsCasting(defaultPoints
				, method
				, penClass);
	}
}
