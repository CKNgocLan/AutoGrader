package student.testSuite.lab4.problem1_3;

import student.testSuite.BaseTester;
import student.util.FieldUtils;
import student.util.MethodUtils;
import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestingField;
import student.model.TestingMethod;
import student.solution.lab4.problem1_3.Employee;

public class EmployeeTester extends BaseTester {
	public EmployeeTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.EMPLOYEE;
		super.solutionClass = Employee.class;
		super.getCorrespondingClass();
	}
	
	public TestingMethod isValidNumberMethod() throws NoSuchMethodException, SecurityException {
		return getSolutionMethod(MethodName.IS_VALID_NUMBER, String.class);
	}
}
