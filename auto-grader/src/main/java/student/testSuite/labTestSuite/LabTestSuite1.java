package student.testSuite.labTestSuite;

import java.util.Arrays;
import java.util.List;

import student.constant.ClassName;
import student.constant.Constants;
import student.constant.FieldName;
import student.constant.Question;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;
import student.util.ParameterTestingUtils;

/**
 * Test suite for the Employee class. Tests constructors, getters, and setters
 * as per the uploaded Employee.java
 */
public class LabTestSuite1 extends ALabTestSuite {
	private ClassTestcaseCreator classTest = ClassTestcaseCreator.getInstance();
	private MethodTestcaseCreator methodTest = MethodTestcaseCreator.getInstance();
	
	@Override
	public List<ITestCase> getAllTests(String question) {
		switch (question) {
		case Question.Q0:
			return Arrays.asList(
					classTest.checkExistence(5, ClassName.EMPLOYEE)
					
					// no-agrs constructor
					, classTest.checkNoArgConstructorDeclaration(5, ClassName.EMPLOYEE)
					, classTest.checkNoArgConstructorOperation(5, ClassName.EMPLOYEE, new ParameterTesting(FieldName.NAME, String.class, Constants.EMPTY_STRING))
					, classTest.checkNoArgConstructorOperation(5, ClassName.EMPLOYEE, new ParameterTesting(FieldName.DEPARTMENT, String.class, Constants.EMPTY_STRING))
					, classTest.checkNoArgConstructorOperation(5, ClassName.EMPLOYEE, new ParameterTesting(FieldName.POSITION, String.class, Constants.EMPTY_STRING))
					, classTest.checkNoArgConstructorOperation(5, ClassName.EMPLOYEE, new ParameterTesting(FieldName.ID_NUMBER, int.class, (int) 0))
					
					// full-agrs constructor
					, classTest.checkFullArgsConstructorDeclaration(5, ClassName.EMPLOYEE,
							ParameterTestingUtils.mapFromTypes(String.class, int.class, String.class, String.class))
					, classTest.checkFullArgsConstructorOperation(5, ClassName.EMPLOYEE
							, new ParameterTesting(FieldName.NAME, String.class, "Susan Meyers")
							, new ParameterTesting(FieldName.ID_NUMBER, int.class, 47899)
							, new ParameterTesting(FieldName.DEPARTMENT, String.class, "Accounting")
							, new ParameterTesting(FieldName.POSITION, String.class, "Vice President")
					)
					, classTest.checkFullArgsConstructorOperation(5, ClassName.EMPLOYEE
							, new ParameterTesting(FieldName.NAME, String.class, "Mark Jones")
							, new ParameterTesting(FieldName.ID_NUMBER, int.class, 39119)
							, new ParameterTesting(FieldName.DEPARTMENT, String.class, "IT")
							, new ParameterTesting(FieldName.POSITION, String.class, "Programmer")
					)
					, classTest.checkFullArgsConstructorOperation(5, ClassName.EMPLOYEE
							, new ParameterTesting(FieldName.NAME, String.class, "Joy Rogers")
							, new ParameterTesting(FieldName.ID_NUMBER, int.class, 81774)
							, new ParameterTesting(FieldName.DEPARTMENT, String.class, "Manufacturing")
							, new ParameterTesting(FieldName.POSITION, String.class, "Engineer")
					)
					
					// partial-args constructor
					, classTest.checkPartialArgsConstructorDeclaration(5, ClassName.EMPLOYEE
							, ParameterTestingUtils.mapFromTypes(String.class, int.class))
					, classTest.checkPartialArgsConstructorOperation(5, ClassName.EMPLOYEE
							, new ParameterTesting(FieldName.NAME, String.class, "Susan Meyers")
							, new ParameterTesting(FieldName.ID_NUMBER, int.class, 47899)
					)
					, classTest.checkFullArgsConstructorOperation(5, ClassName.EMPLOYEE
							, new ParameterTesting(FieldName.NAME, String.class, "Mark Jones")
							, new ParameterTesting(FieldName.ID_NUMBER, int.class, 39119)
					)
					, classTest.checkFullArgsConstructorOperation(5, ClassName.EMPLOYEE
							, new ParameterTesting(FieldName.NAME, String.class, "Joy Rogers")
							, new ParameterTesting(FieldName.ID_NUMBER, int.class, 81774)
					)
					
					// attribute
					, classTest.checkAttributes(5, ClassName.EMPLOYEE)
					
					// getter setter
					, methodTest.checkGetterDeclaration(5, ClassName.EMPLOYEE)
					, methodTest.checkSetterDeclaration(5, ClassName.EMPLOYEE)
					, methodTest.checkStringGetsetOperation(5, ClassName.EMPLOYEE, FieldName.NAME, "Susan Meyers")
					, methodTest.checkStringGetsetOperation(5, ClassName.EMPLOYEE, FieldName.DEPARTMENT, "Accounting")
					, methodTest.checkStringGetsetOperation(5, ClassName.EMPLOYEE, FieldName.POSITION, "Vice President")
					, methodTest.checkIntGetsetOperation(5, ClassName.EMPLOYEE, FieldName.ID_NUMBER, 47899)
					);
		default:
			return null;
		}
	}
}