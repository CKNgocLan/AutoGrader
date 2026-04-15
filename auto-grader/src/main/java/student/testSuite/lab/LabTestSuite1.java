package student.testSuite.lab;

import java.util.Arrays;
import java.util.List;

import student.constant.ClassName;
import student.constant.Question;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.testSuite.classTestSuite.ClassTest;

/**
 * Test suite for the Employee class. Tests constructors, getters, and setters
 * as per the uploaded Employee.java
 */
public class LabTestSuite1 extends ALabTestSuite {
	private ClassTest classTest = ClassTest.getInstance();
	
	@Override
	public List<ITestCase> getAllTests(String question) {
		switch (question) {
		case Question.Q0:
			return Arrays.asList(
					classTest.checkExistence(ClassName.EMPLOYEE, 10)
					, classTest.checkNoArgConstructor(ClassName.EMPLOYEE, 15)
					, classTest.checkFullArgsConstructor(ClassName.EMPLOYEE, 20)
					, classTest.checkAttributes(ClassName.EMPLOYEE, 5)
					, classTest.checkGetterDeclaration(ClassName.EMPLOYEE, 5)
					, classTest.checkSetterDeclaration(ClassName.EMPLOYEE, 5)
					, classTest.checkGetterOperation(ClassName.EMPLOYEE, 5)
					);
		case Question.Q1:
			return null;
//			return Arrays.asList(ClassTest.checkExistence(ClassName.EMPLOYEE, 10),
//					ClassTest.checkNoArgConstructor(ClassName.EMPLOYEE, 15),
//					ClassTest.checkFullArgsConstructor(ClassName.EMPLOYEE, 20));
		case Question.Q2:
			return null;
//			return Arrays.asList(ClassTest.checkExistence(ClassName.EMPLOYEE),
//					ClassTest.checkNoArgConstructor(ClassName.EMPLOYEE),
//					ClassTest.checkFullArgsConstructor(ClassName.EMPLOYEE));
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