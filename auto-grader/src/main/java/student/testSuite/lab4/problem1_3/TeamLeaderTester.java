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

public class TeamLeaderTester extends BaseTester {
	private ProductionWorkerTester productionWorkerTester;

	public TeamLeaderTester(ProductionWorkerTester productionWorkerTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.TEAM_LEADER;
		super.getCorrespondingClass();
		this.productionWorkerTester = productionWorkerTester;
	}

	/* declare */

	public TestCase declare() {
		return super.declare(defaultPoints);
	}

	public TestCase declareSuper() {
		try {
			return super.declareSuperClass(defaultPoints, productionWorkerTester.getCorrespondingClass());
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
				new TestingField(double.class, FieldName.MONTHLY_BONUS)
				, new TestingField(double.class, FieldName.REQUIRED_TRAINING_HOURS)
				, new TestingField(double.class, FieldName.ATTENDED_TRAINING_HOURS)
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
				, double.class // pay rate
				, double.class // monthly bonus
				, double.class // required training hours
				, double.class // attended training hours
		);
	}

	/* calculateTotalSalary() */
	private TestingMethod calculateTotalSalary() {
		return new TestingMethod(double.class, MethodName.CALCULATE_TOTAL_SALARY, new TestingParameter(double.class, FieldName.MONTHLY_HOURS));
	}

	public TestCase declareCalculateTotalSalary() {
		return super.methodTester.declare(defaultPoints, className, calculateTotalSalary());
	}
}
