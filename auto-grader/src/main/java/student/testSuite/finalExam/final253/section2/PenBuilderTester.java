package student.testSuite.finalExam.final253.section2;

import java.util.ArrayList;
import java.util.stream.Stream;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.ITestCase;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.solution.final253.section2.Pen;
import student.testSuite.BaseTester;
import student.util.ClassUtils;
import student.util.SetterUtils;
import student.util.StringUtils;
import student.util.ValueUtils;

public class PenBuilderTester extends BaseTester {
	private ArrayList<TestingParameter> args;
	private String containingClassName = ClassName.PEN;

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
		return createSetBrandMethod().updateParameter(new TestingParameter(String.class, FieldName.BRAND, brand));
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

			return brand.equals(StringUtils.toString(super.getFieldAsAccessible(clazz, FieldName.BRAND).get(instance))) ?
					passMethodOperation(method.getName()) :
					failMethodOperation(method.getName());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	/*
	 * setModel
	 */

	private TestingMethod createSetModelMethod() throws Exception {
		return new TestingMethod(getCorrespondingClass(), SetterUtils.getSetterName(FieldName.MODEL), new TestingParameter(String.class));
	}

	private TestingMethod createSetModelMethod(String model) throws Exception {
		return createSetModelMethod().updateParameter(new TestingParameter(String.class, FieldName.MODEL, model));
	}

	public ITestCase declareSetModel() {
		try {
			return super.methodTester.declare(defaultPoints, className, createSetModelMethod());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	public ITestCase operateSetModel(String model) {
		try {
			Class<?> clazz = getCorrespondingClass();
			Object instance = instantiate();
			TestingMethod method = createSetModelMethod(model);

			super.buildInstance(clazz, instance, method);

			return model.equals(StringUtils.toString(super.getFieldAsAccessible(clazz, FieldName.MODEL).get(instance))) ?
					passMethodOperation(method.getName()) :
					failMethodOperation(method.getName());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	/*
	 * setPrice
	 */

	private TestingMethod createSetPriceMethod() throws Exception {
		return new TestingMethod(getCorrespondingClass(), SetterUtils.getSetterName(FieldName.PRICE), new TestingParameter(double.class));
	}

	private TestingMethod createSetPriceMethod(double price) throws Exception {
		return createSetPriceMethod().updateParameter(new TestingParameter(double.class, FieldName.PRICE, price));
	}

	public ITestCase declareSetPrice() {
		try {
			return super.methodTester.declare(defaultPoints, className, createSetPriceMethod());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	public ITestCase operateSetPrice(double price) {
		try {
			Class<?> clazz = getCorrespondingClass();
			Object instance = instantiate();
			TestingMethod method = createSetPriceMethod(price);

			super.buildInstance(clazz, instance, method);

			return price == ValueUtils.toDoublePrimitive(super.getFieldAsAccessible(clazz, FieldName.PRICE).get(instance)) ?
					passMethodOperation(method.getName()) :
					failMethodOperation(method.getName());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	/*
	 * build
	 */

	private TestingMethod createBuildMethod() throws Exception {
		return new TestingMethod(retriveClass(containingClassName), MethodName.BUILD);
	}

	public ITestCase declareBuild() {
		try {
			return super.methodTester.declare(defaultPoints, className, createBuildMethod());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

}
