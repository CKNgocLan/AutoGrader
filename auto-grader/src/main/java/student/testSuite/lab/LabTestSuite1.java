package student.testSuite.lab;
import java.util.Arrays;
import java.util.List;

import student.constant.ClassName;
import student.constant.Question;
import student.model.ITestCase;
import student.testSuite.classTestSuite.ClassTest;
import student.util.TestCaseUtil;

/**
 * Test suite for the Employee class. Tests constructors, getters, and setters
 * as per the uploaded Employee.java
 */
public class LabTestSuite1 {

	public static List<ITestCase> getAllTests(String question) {
		switch (question) {
		case Question.Q1:
			return Arrays.asList(
					ClassTest.checkExistence(ClassName.EMPLOYEE, 10)
					, ClassTest.checkNoArgConstructor(ClassName.EMPLOYEE, 15)
					, ClassTest.checkFullArgsConstructor(ClassName.EMPLOYEE, 20)
			);
		case Question.Q2:
			return Arrays.asList(
					ClassTest.checkExistence(ClassName.EMPLOYEE)
					, ClassTest.checkNoArgConstructor(ClassName.EMPLOYEE)
					, ClassTest.checkFullArgsConstructor(ClassName.EMPLOYEE)
			);
		case Question.Q3:
			return null;
		case Question.Q4:
			return null;
		case Question.Q5:
			return null;
		default:
			return null;
		}
	}
}