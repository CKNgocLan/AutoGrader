package student.testSuite.lab;
import java.util.*;

import student.constant.ClassName;
import student.constant.Question;
import student.model.ITestCase;
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
					TestCaseUtil.createClassExistsTest(ClassName.EMPLOYEE, 10)
					, TestCaseUtil.createNoArgConstructorTest(ClassName.EMPLOYEE, 15)
					, TestCaseUtil.createFullConstructorTest(ClassName.EMPLOYEE, 20)
			);
		case Question.Q2:
			return Arrays.asList(
					TestCaseUtil.createClassExistsTest(ClassName.EMPLOYEE)
					, TestCaseUtil.createNoArgConstructorTest(ClassName.EMPLOYEE)
					, TestCaseUtil.createFullConstructorTest(ClassName.EMPLOYEE)
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