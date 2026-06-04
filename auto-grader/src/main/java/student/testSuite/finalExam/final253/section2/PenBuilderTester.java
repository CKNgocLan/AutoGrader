package student.testSuite.finalExam.final253.section2;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.stream.Stream;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.ITestCase;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.solution.final253.section2.Pen;
import student.testSuite.BaseTester;
import student.util.ClassUtils;
import student.util.MethodUtils;
import student.util.SetterUtils;
import student.util.TestCaseUtils;

public class PenBuilderTester extends BaseTester {
	private ArrayList<TestingParameter> args;

	/*
	 * instantiate ***************
	 */

	public PenBuilderTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassUtils.toInnerClassName(ClassName.PEN, ClassName.PEN_BUILDER);
		super.getCorrespondingClass();
		super.solutionClass = Pen.PenBuilder.class;
	}

	/*
	 * argument
	 */

	public ArrayList<TestingParameter> initArgs() {
		this.args = new ArrayList<>();
		return this.args;
	}

	public ArrayList<TestingParameter> addBrand(String brand) {
		this.args.add(new TestingParameter(String.class, FieldName.BRAND, brand));
		return args;
	}
	
	public ArrayList<TestingParameter> addModel(String model) {
		this.args.add(new TestingParameter(String.class, FieldName.MODEL, model));
		return args;
	}
	
	public ArrayList<TestingParameter> addPrice(double price) {
		this.args.add(new TestingParameter(double.class, FieldName.PRICE, price));
		return args;
	}

	public Object build() throws Exception {
		return instantiateWithArgs(Stream.of(this.args).toArray(TestingParameter[]::new));
	}
	
	/*
	 * declare
	 */

	public ITestCase declare() {
		return super.declareAsInnerStaticClass(defaultPoints);
	}

	/*
	 * field
	 */

	public ITestCase declareFields() {
		return super.fieldTester.checkDeclarations(defaultPoints, className, getSolutionFields());
	}

	/*
	 * setBrand
	 */

	private TestingMethod createSetBrandMethod() throws Exception {
		return new TestingMethod(getCorrespondingClass(), SetterUtils.getSetterName(FieldName.BRAND), new TestingParameter(String.class));
	}

	private TestingMethod createSetBrandMethod(String brand) throws Exception {
		return createSetBrandMethod().updateParameter(
				new TestingParameter(String.class, FieldName.BRAND, brand)
		);
	}

	public ITestCase declareSetBrand() {
		try {
			return super.methodTester.declare(defaultPoints, className, createSetBrandMethod());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	public ITestCase operateSetBrand(String brand) {
		try {
			Class<?> clazz = getCorrespondingClass();
			Object instance = instantiate();
			TestingMethod method = createSetBrandMethod(brand);

			super.buildInstance(clazz, instance, method);
//			super.methodTester.getMethodChecker().buildInstance(clazz, instance, method);

			return passTestCase();
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}
}
