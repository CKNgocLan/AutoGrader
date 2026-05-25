package student.testSuite.midterm253;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestingField;
import student.model.ITestCase;
import student.model.TestingParameter;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class BrandTester extends BaseTester {
	public List<ITestCase> getAllTestcases() {
		return Arrays.asList(
				declare(defaultPoints)
				, declareFields(defaultPoints)
				, declareConstructor(defaultPoints)
				, declareGetters(defaultPoints)
				, declareSetters(defaultPoints)
				, declareToString(defaultPoints)
				);
	}

	/*
	 * instantiate ***************
	 */

	public BrandTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.BRAND;
		super.getCorrespondingClass();
	}
	
	public Object instantiate(String name, String country) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, TesterGotNoClassNameException {
		return super.instantiateWithArgs(argument(name, country));
	}
	
	/*
	 * argument
	 */
	
	private TestingParameter[] argument(String name, String country) throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingParameter[] {
				new TestingParameter(String.class, FieldName.NAME, name),
				new TestingParameter(String.class, FieldName.COUNTRY, country) };
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
	public ITestCase declare(int points) {
		return super.declare(points);
	}

	/*
	 * field ***************
	 */

	public ITestCase declareFields(int points) {
		try {
			return super.fieldTester.checkDeclarations(points, className, fields());
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}

	/*
	 * constructor
	 */

	public ITestCase declareConstructor(int points) {
		try {
			return super.checkConstructorDeclaration(points, String.class, String.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
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
