package student.testSuite.lab4.problem1_3;

import java.time.LocalDate;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.model.TestingField;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class ShiftSupervisorTester extends BaseTester {
	private EmployeeTester employeeTester;
	public ShiftSupervisorTester(EmployeeTester employeeTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.SHIFT_SUPERVISOR;
		super.getCorrespondingClass();
		this.employeeTester = employeeTester;
	}

	/*
	 * field
	 */

	private TestingField[] fields() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingField[] {
				new TestingField(double.class, FieldName.ANNUAL_SALARY)
				, new TestingField(double.class, FieldName.ANNUAL_PRODUCTION_BONUS)
		};
	}
	
	public TestCase declareFields() {
		try {
			return super.fieldTester.checkDeclarations(defaultPoints, className, fields());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}

	/* constructor */

	public TestCase declareConstructor() {
		return super.classTester.checkPartialArgsConstructorDeclaration(defaultPoints, className
				, String.class // name
				, String.class // number
				, LocalDate.class // hireDate
				, double.class // annualSalary
				, double.class // annualProductionBonus
		);
	}

	/* calculateTotalSalary() */
	private TestingMethod calculateTotalSalary() {
		return new TestingMethod(double.class, MethodName.CALCULATE_TOTAL_SALARY);
	}

	public TestCase declareCalculateTotalSalary() {
		return super.methodTester.declare(defaultPoints, className, calculateTotalSalary());
	}
}
