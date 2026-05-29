package student.testSuite.lab4.problem1_3;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.ITestCase;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.solution.lab4.problem1_3.Employee;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class EmployeeTester extends BaseTester {
	public EmployeeTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.EMPLOYEE;
		super.solutionClass = Employee.class;
		super.getCorrespondingClass();
	}

	public TestingMethod isValidNumberMethod() throws NoSuchMethodException, SecurityException {
		return getSolutionMethod(MethodName.IS_VALID_NUMBER, String.class);
	}

	/*
	 * argument
	 */

	private TestingParameter[] constructorArgs(String name, String number, LocalDate hireDate) {
		return new TestingParameter[] { new TestingParameter(String.class, FieldName.NAME, name),
				new TestingParameter(String.class, FieldName.NUMBER, number),
				new TestingParameter(LocalDate.class, FieldName.HIRE_DATE, hireDate) };
	}

	/*
	 * instantiate
	 */

	private Object instantiate(String name, String number, LocalDate hireDate)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, TesterGotNoClassNameException {
		return super.instantiateWithArgs(constructorArgs(name, number, hireDate));
	}

	/*
	 * declare
	 */

	public ITestCase declare(int points) {
		return classTester.checkExistence(points, className);
	}

	/*
	 * fields
	 */

	public ITestCase declareFields(int points) {
		return fieldTester.checkDeclarations(points, className, super.getSolutionFields());
	}

	/*
	 * constructor
	 */
	
	public ITestCase declareConstructor(int points) {
		return super.classTester.checkPartialArgsConstructorDeclaration(points, className, super.getSolutionFirstConstructor().getParameterTypes());
	}

	public ITestCase operateConstructor(int points, String name, String number, LocalDate hireDate) {
		try {
			return super.classTester.checkPartialArgsConstructorOperationViaGetter(points, className, constructorArgs(name, number, hireDate));
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}
}
