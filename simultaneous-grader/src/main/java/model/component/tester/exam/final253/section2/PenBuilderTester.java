package model.component.tester.exam.final253.section2;

import java.util.ArrayList;

import common.constant.ClassName;
import common.constant.FieldName;
import common.constant.MethodName;
import common.util.ClassUtils;
import common.util.SetterUtils;
import common.util.StringUtils;
import common.util.ValueUtils;
import model.component.TestCase;
import model.component.tester.Tester;
import model.component.tester.exam.final253.section2.solution.Pen;
import model.element.TestingMethod;
import model.element.TestingParameter;
import model.exception.TesterGotNoClassNameException;

public class PenBuilderTester extends Tester {
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
	
	/*
	 * declare
	 */

	public TestCase declare() {
		return super.declareAsInnerStaticClass(defaultPoints);
	}

	/*
	 * field
	 */

	public TestCase declareFields() {
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

	public TestCase declareSetBrand() {
		try {
			return super.methodTester.declare(defaultPoints, className, createSetBrandMethod());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	public TestCase operateSetBrand(String brand) {
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

	public TestCase declareSetModel() {
		try {
			return super.methodTester.declare(defaultPoints, className, createSetModelMethod());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	public TestCase operateSetModel(String model) {
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

	public TestCase declareSetPrice() {
		try {
			return super.methodTester.declare(defaultPoints, className, createSetPriceMethod());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	public TestCase operateSetPrice(double price) {
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

	public TestingMethod createBuildMethod() throws Exception {
		return new TestingMethod(retriveClass(containingClassName), MethodName.BUILD);
	}

	public TestCase declareBuild() {
		try {
			return super.methodTester.declare(defaultPoints, className, createBuildMethod());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	public TestCase operateBuild(String brand, String model, double price) {
		try {
			TestingMethod method = createBuildMethod();
			Object penInstance = buildPen(brand, model, price, method);

			Class<?> penClass = retriveClass(containingClassName);
			try {
				penClass.cast(penInstance);
			} catch (ClassCastException e) {
				return failMethodOperation(method.getName());
			}

			return (!brand.equals(StringUtils.toString(getFieldAsAccessible(penClass, FieldName.BRAND).get(penInstance)))
					|| !model.equals(StringUtils.toString(getFieldAsAccessible(penClass, FieldName.MODEL).get(penInstance)))
					|| price != ValueUtils.toDoublePrimitive(getFieldAsAccessible(penClass, FieldName.PRICE).get(penInstance))) ?
				failMethodOperation(method.getName()) :
				passMethodOperation(method.getName());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	public Object buildPen(String brand, String model, double price, TestingMethod buildMethod) throws Exception {
		Class<?> clazz = getCorrespondingClass();
		Object builderInstance = instantiate();

		// brand
		super.buildInstance(clazz, builderInstance, createSetBrandMethod(brand));

		// model
		super.buildInstance(clazz, builderInstance, createSetModelMethod(model));

		// price
		super.buildInstance(clazz, builderInstance, createSetPriceMethod(price));

		return buildMethod.config(clazz, builderInstance).returning();
	}
}
