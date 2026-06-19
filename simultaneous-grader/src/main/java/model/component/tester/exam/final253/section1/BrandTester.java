package model.component.tester.exam.final253.section1;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import common.constant.ClassName;
import common.constant.FieldName;
import common.util.TestCaseUtils;
import model.component.TestCase;
import model.component.tester.Tester;
import model.component.tester.exam.final253.section1.solution.Brand;
import model.element.TestingField;
import model.element.TestingParameter;
import model.exception.TesterGotNoClassNameException;

public class BrandTester extends Tester {
	/*
	 * instantiate ***************
	 */

	public BrandTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.BRAND;
		super.getCorrespondingClass();
		super.solutionClass = Brand.class;
	}
	
	public Object instantiate(String name) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, TesterGotNoClassNameException {
		return super.instantiateWithArgs(argument(name));
	}
	
	/*
	 * argument
	 */
	
	private TestingParameter[] argument(String name) throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingParameter[] { new TestingParameter(String.class, FieldName.NAME, name) };
	}

	/*
	 * field declared
	 */
	
	public TestingField[] fields() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingField[] { new TestingField(UUID.class, FieldName.ID),
				new TestingField(String.class, FieldName.NAME), new TestingField(String.class, FieldName.COUNTRY) };
	}

	/*
	 * declaration
	 */
	public TestCase declare() {
		return super.declare(defaultPoints);
	}

	/*
	 * field ***************
	 */

	public TestCase declareFields() {
		try {
			return super.fieldTester.checkDeclarations(defaultPoints, className, super.getSolutionFields());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	/*
	 * constructor
	 */

	public TestCase declareConstructor() {
		try {
			return super.checkConstructorDeclaration(defaultPoints, String.class, String.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	/*
	 * getter
	 */
	public TestCase declareGetters(int points) {
		return super.methodTester.checkGetterDeclaration(points, className);
	}

	/*
	 * setter
	 */
	public TestCase declareSetters(int points) {
		return super.methodTester.checkSetterDeclaration(points, className);
	}

	/*
	 * toString
	 */
	public TestCase declareToString(int points) {
		return super.checkToStringDeclaration(points);
	}
}
