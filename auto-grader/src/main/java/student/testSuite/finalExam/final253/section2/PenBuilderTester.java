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

	private TestingMethod createSetBrandMethod() {
		return MethodUtils.fromSolution(solutionClass, SetterUtils.getSetterName(FieldName.BRAND));
	}

	public ITestCase declareSetBrand() {
		return null;
//		return super.methodTester.de
	}
}
