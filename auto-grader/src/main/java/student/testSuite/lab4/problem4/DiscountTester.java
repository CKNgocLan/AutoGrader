package student.testSuite.lab4.problem4;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
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

public class DiscountTester extends BaseTester {
	public List<ITestCase> getAllTestcases() {
		return Arrays.asList(
				declare(defaultPoints)
				, declareFields(defaultPoints)
				, declareConstructor(defaultPoints)
//				, operateConstructor(int points, double percent, LocalDate endDate)
				, declareGetters(defaultPoints)
				, declareSetters(defaultPoints)
				, declareToString(defaultPoints)
				);
	}

	/*
	 * instantiate ***************
	 */

	public DiscountTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.DISCOUNT;
		super.getCorrespondingClass();
	}
	
	public Object instantiate(double percent, LocalDate endDate) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, TesterGotNoClassNameException {
		return super.instantiateWithArgs(argument(percent, endDate));
	}
	
	/*
	 * argument
	 */
	
	private TestingParameter[] argument(double percent, LocalDate endDate) throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingParameter[] {
				new TestingParameter(double.class, FieldName.PERCENT, percent),
				new TestingParameter(LocalDate.class, FieldName.END_DATE, endDate) };
	}

	/*
	 * field declared
	 */
	
	public TestingField[] fields() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingField[] { new TestingField(UUID.class, FieldName.ID),
				new TestingField(double.class, FieldName.PERCENT),
				new TestingField(LocalDate.class, FieldName.START_DATE),
				new TestingField(LocalDate.class, FieldName.END_DATE) };
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
			return super.checkConstructorDeclaration(points, double.class, LocalDate.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}

	public ITestCase operateConstructor(int points, double percent, LocalDate endDate)
			throws ClassNotFoundException, TesterGotNoClassNameException {
		return super.checkConstructorOperation(points, argument(percent, endDate));
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
