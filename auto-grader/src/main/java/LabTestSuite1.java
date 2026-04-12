import java.util.*;

/**
 * Test suite for the Employee class. Tests constructors, getters, and setters
 * as per the uploaded Employee.java
 */
public class LabTestSuite1 {

	public static List<TestCase> getAllTests(String question) {
		switch (question) {
		case Questions.Q1:
			return Arrays.asList(
					TestCaseUtils.createClassExistsTest(ClassName.EMPLOYEE, 10)
					, TestCaseUtils.createNoArgConstructorTest(ClassName.EMPLOYEE, 15)
					, TestCaseUtils.createFullConstructorTest(ClassName.EMPLOYEE, 20)
			);
		case Questions.Q2:
			return Arrays.asList(
					TestCaseUtils.createClassExistsTest(ClassName.EMPLOYEE)
					, TestCaseUtils.createNoArgConstructorTest(ClassName.EMPLOYEE)
					, TestCaseUtils.createFullConstructorTest(ClassName.EMPLOYEE)
			);
		case Questions.Q3:
			return null;
		case Questions.Q4:
			return null;
		case Questions.Q5:
			return null;
		default:
			return null;
		}
	}
}