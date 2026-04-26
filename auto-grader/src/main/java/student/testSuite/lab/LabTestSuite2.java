package student.testSuite.lab;

import java.util.*;

import student.constant.ClassName;
import student.constant.Feedback;
import student.constant.FieldName;
import student.constant.Question;
import student.constant.TestcaseType;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.model.ParameterTest;
import student.testSuite.classTestSuite.ClassTest;
import student.testSuite.methodTestSuite.MethodTest;
import student.util.ParameterTestUtils;

/**
 * Test suite for the Employee class. Tests constructors, getters, and setters
 * as per the uploaded Employee.java
 */
public class LabTestSuite2 extends ALabTestSuite {
	private ClassTest classTest = ClassTest.getInstance();
	private MethodTest methodTest = MethodTest.getInstance();
	
	@Override
	public List<ITestCase> getAllTests(String question) {
		switch (question) {
		case Question.Q1:
			return Arrays.asList(
					classTest.checkExistence(ClassName.CAR, 5)
					
					// partial-args constructor
					, classTest.checkPartialArgsConstructorDeclaration(ClassName.CAR, 5,
							ParameterTestUtils.toArray(int.class, String.class, int.class))
					, classTest.checkPartialArgsConstructorOperation(ClassName.CAR, 5,
							new ParameterTest(FieldName.YEAR_MODEL, int.class, 2025)
							, new ParameterTest(FieldName.MAKE, String.class, "Mazda"))
					
					// getter setter
					, methodTest.checkGetterDeclaration(ClassName.CAR, 5)
					, methodTest.checkStringGetsetOperation(ClassName.EMPLOYEE, 5, FieldName.NAME, "Susan Meyers")
					, methodTest.checkStringGetsetOperation(ClassName.EMPLOYEE, 5, FieldName.DEPARTMENT, "Accounting")
					, methodTest.checkStringGetsetOperation(ClassName.EMPLOYEE, 0, FieldName.POSITION, "Vice President")
					, methodTest.checkIntGetsetOperation(ClassName.EMPLOYEE, 5, FieldName.ID_NUMBER, 47899)
			);
		case Question.Q2:
			return Arrays.asList(
					classTest.checkExistence(ClassName.TEMPERATURE, 5)
			);
		case Question.Q3:
			return null;
		case Question.Q4:
			return null;
		case Question.Q5:
			return null;
		case Question.Q6:
			return null;
		default:
			return null;
		}
	}
}