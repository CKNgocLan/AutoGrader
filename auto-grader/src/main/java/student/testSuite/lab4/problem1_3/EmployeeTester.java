package student.testSuite.lab4.problem1_3;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.stream.Stream;

import student.constant.ClassName;
import student.constant.Constants;
import student.constant.ExceptionMessage;
import student.constant.Feedback;
import student.constant.FieldName;
import student.constant.MethodName;
import student.constant.TestcaseType;
import student.exception.TesterGotNoClassNameException;
import student.model.ITestCase;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.solution.lab4.problem1_3.Employee;
import student.testSuite.BaseTester;
import student.util.ParameterUtils;
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
		return super.classTester.checkPartialArgsConstructorDeclaration(points, className, super.uniqueConstructorParameterPrimitiveTypes());
	}

	public ITestCase operateConstructor(int points, String name, String number, LocalDate hireDate) {
		try {
			return super.classTester.checkPartialArgsConstructorOperationViaGetter(points, className, constructorArgs(name, number, hireDate));
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}
	
	public ITestCase invalidNumberInConstructor(int points, String number) {
		TestingMethod method;
		try {
			method = isValidNumberMethod();
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
		
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_OPERATION_OF_CONSTRUCTOR_PARTIAL_ARGS.getName(className);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					assertThrows(InvocationTargetException.class, () -> {
						instantiate(Constants.DEFAULT_NAME, number, LocalDate.now());
					});

					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.PARTIAL_ARGS_CONSTRUCTOR_OPERATION_NOT_CORRECT.getContent(className, String
						.join(Constants.COMMA_WITH_SPACE, Stream.of(method.getParameters()).map(param -> param.getName()).toList()));
			}
		};
	}
}
