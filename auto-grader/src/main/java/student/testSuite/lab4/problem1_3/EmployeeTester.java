package student.testSuite.lab4.problem1_3;

import student.testSuite.BaseTester;
import student.util.FieldUtils;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestingField;
import student.solution.lab4.problem1_3.Employee;

public class EmployeeTester extends BaseTester {
	public TestingField[] fields() throws ClassNotFoundException, TesterGotNoClassNameException {
		return FieldUtils.fromSolution(Employee.class);
	}
}
