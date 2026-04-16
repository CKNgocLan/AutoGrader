package student.testSuite.lab;

import java.util.Arrays;
import java.util.List;

import student.constant.ClassName;
import student.constant.Constants;
import student.constant.FieldName;
import student.constant.Question;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.testSuite.classTestSuite.ClassTest;
import student.testSuite.methodTestSuite.MethodTest;

/**
 * Test suite for the Employee class. Tests constructors, getters, and setters
 * as per the uploaded Employee.java
 */
public class LabTestSuite1 extends ALabTestSuite {
	private ClassTest classTest = ClassTest.getInstance();
	private MethodTest methodTest = MethodTest.getInstance();
	
	@Override
	public List<ITestCase> getAllTests(String question) {
		switch (question) {
		case Question.Q0:
			return Arrays.asList(
					classTest.checkExistence(ClassName.EMPLOYEE, 5)
					
					// no-agrs constructor section
					, classTest.checkNoArgConstructorDeclaration(ClassName.EMPLOYEE, 5)
					, classTest.checkNoArgConstructorOperation(ClassName.EMPLOYEE, 5, FieldName.NAME, String.class, Constants.EMPTY)
					, classTest.checkNoArgConstructorOperation(ClassName.EMPLOYEE, 5, FieldName.DEPARTMENT, String.class, Constants.EMPTY)
					, classTest.checkNoArgConstructorOperation(ClassName.EMPLOYEE, 5, FieldName.POSITION, double.class, (double) 0)
					, classTest.checkNoArgConstructorOperation(ClassName.EMPLOYEE, 5, FieldName.ID_NUMBER, int.class, 0)
					
					// full-agrs constructor section
					, classTest.checkFullArgsConstructorDeclaration(ClassName.EMPLOYEE, 15, String.class, int.class, String.class, double.class)
					
					// partial-args constructor section
					, classTest.checkPartialArgsConstructorDeclaration(ClassName.EMPLOYEE, 15, String.class, int.class)
					
					// attribute section
					, classTest.checkAttributes(ClassName.EMPLOYEE, 5)
					
					// getter setter section
					, methodTest.checkGetterDeclaration(ClassName.EMPLOYEE, 5)
					, methodTest.checkSetterDeclaration(ClassName.EMPLOYEE, 5)
					, methodTest.checkStringGetsetOperation(ClassName.EMPLOYEE, 5, FieldName.NAME, "Susan Meyers")
					, methodTest.checkStringGetsetOperation(ClassName.EMPLOYEE, 5, FieldName.DEPARTMENT, "Accounting")
					, methodTest.checkStringGetsetOperation(ClassName.EMPLOYEE, 5, FieldName.POSITION, "Vice President")
					, methodTest.checkIntGetsetOperation(ClassName.EMPLOYEE, 5, FieldName.ID_NUMBER, 47899)
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