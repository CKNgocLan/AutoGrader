package student.testSuite.finalExam.final253.section1;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.ITestCase;
import student.model.TestingField;
import student.model.TestingParameter;
import student.solution.final253.section1.Brand;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class BrandTester extends BaseTester {
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
	public ITestCase declare() {
		return super.declare(defaultPoints);
	}

	/*
	 * field ***************
	 */

	public ITestCase declareFields() {
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

	public ITestCase declareConstructor() {
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
	public ITestCase declareGetters(int points) {
		return super.methodTester.checkGetterDeclaration(points, className);
	}

	/*
	 * setter
	 */
	public ITestCase declareSetters(int points) {
		return super.methodTester.checkSetterDeclaration(points, className);
	}

	/*
	 * toString
	 */
	public ITestCase declareToString(int points) {
		return super.checkToStringDeclaration(points);
	}
}
