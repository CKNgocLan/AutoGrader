package student.testSuite.lab4.problem1_3;

import java.time.LocalDate;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.model.TestingField;
import student.model.TestingMethod;
import student.testSuite.BaseTester;
import student.util.GetterUtils;
import student.util.TestCaseUtils;

public class ProductionWorkerTester extends BaseTester {
	private EmployeeTester employeeTester;

	public ProductionWorkerTester(EmployeeTester employeeTester)
			throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.PRODUCTION_WORKER;
		super.getCorrespondingClass();
		this.employeeTester = employeeTester;
	}

	/* declare */

	public TestCase declare() {
		return super.declare(defaultPoints);
	}

	public TestCase declareSuper() {
		try {
			return super.declareSuperClass(defaultPoints, employeeTester.getCorrespondingClass());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	/*
	 * field
	 */

	private TestingField[] fields() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingField[] {
				new TestingField(int.class, FieldName.SHIFT)
				, new TestingField(double.class, FieldName.PAY_RATE)
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
				, String.class
				, String.class
				, LocalDate.class
				, int.class
				, double.class
		);
	}

	/* getShiftName */
	private TestingMethod getShiftName() {
		return new TestingMethod(String.class, GetterUtils.getGetterName(FieldName.SHIFT_NAME));
	}

	public TestCase declareGetShiftName() {
		return super.methodTester.declare(defaultPoints, className, getShiftName());
	}
}
