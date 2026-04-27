package student.testSuite.lab;

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
					classTest.checkExistence(ClassName.EMPLOYEE, 5)
					
					// no-agrs constructor
					, classTest.checkNoArgConstructorDeclaration(ClassName.EMPLOYEE, 5)
					, classTest.checkNoArgConstructorOperation(ClassName.EMPLOYEE, 5, new ParameterTesting(FieldName.NAME, String.class, Constants.EMPTY))
					, classTest.checkNoArgConstructorOperation(ClassName.EMPLOYEE, 5, new ParameterTesting(FieldName.DEPARTMENT, String.class, Constants.EMPTY))
					, classTest.checkNoArgConstructorOperation(ClassName.EMPLOYEE, 5, new ParameterTesting(FieldName.POSITION, String.class, Constants.EMPTY))
					, classTest.checkNoArgConstructorOperation(ClassName.EMPLOYEE, 5, new ParameterTesting(FieldName.ID_NUMBER, int.class, (int) 0))
					
					// full-agrs constructor
					, classTest.checkFullArgsConstructorDeclaration(ClassName.EMPLOYEE, 5,
							ParameterTestingUtils.toArray(String.class, int.class, String.class, String.class))
					, classTest.checkFullArgsConstructorOperation(ClassName.EMPLOYEE, 5,
							new ParameterTesting(FieldName.NAME, String.class, "Susan Meyers")
							, new ParameterTesting(FieldName.ID_NUMBER, int.class, 47899)
							, new ParameterTesting(FieldName.DEPARTMENT, String.class, "Accounting")
							, new ParameterTesting(FieldName.POSITION, String.class, "Vice President")
					)
					, classTest.checkFullArgsConstructorOperation(ClassName.EMPLOYEE, 5,
							new ParameterTesting(FieldName.NAME, String.class, "Mark Jones")
							, new ParameterTesting(FieldName.ID_NUMBER, int.class, 39119)
							, new ParameterTesting(FieldName.DEPARTMENT, String.class, "IT")
							, new ParameterTesting(FieldName.POSITION, String.class, "Programmer")
					)
					, classTest.checkFullArgsConstructorOperation(ClassName.EMPLOYEE, 5,
							new ParameterTesting(FieldName.NAME, String.class, "Joy Rogers")
							, new ParameterTesting(FieldName.ID_NUMBER, int.class, 81774)
							, new ParameterTesting(FieldName.DEPARTMENT, String.class, "Manufacturing")
							, new ParameterTesting(FieldName.POSITION, String.class, "Engineer")
					)
					
					// partial-args constructor
					, classTest.checkPartialArgsConstructorDeclaration(ClassName.EMPLOYEE, 5,
							ParameterTestingUtils.toArray(String.class, int.class))
					, classTest.checkPartialArgsConstructorOperation(ClassName.EMPLOYEE, 5,
							new ParameterTesting(FieldName.NAME, String.class, "Susan Meyers")
							, new ParameterTesting(FieldName.ID_NUMBER, int.class, 47899)
					)
					, classTest.checkFullArgsConstructorOperation(ClassName.EMPLOYEE, 5,
							new ParameterTesting(FieldName.NAME, String.class, "Mark Jones")
							, new ParameterTesting(FieldName.ID_NUMBER, int.class, 39119)
					)
					, classTest.checkFullArgsConstructorOperation(ClassName.EMPLOYEE, 5,
							new ParameterTesting(FieldName.NAME, String.class, "Joy Rogers")
							, new ParameterTesting(FieldName.ID_NUMBER, int.class, 81774)
					)
					
					// attribute
					, classTest.checkAttributes(ClassName.EMPLOYEE, 5)
					
					// getter setter
					, methodTest.checkGetterDeclaration(ClassName.EMPLOYEE, 5)
					, methodTest.checkSetterDeclaration(ClassName.EMPLOYEE, 5)
					, methodTest.checkStringGetsetOperation(ClassName.EMPLOYEE, 5, FieldName.NAME, "Susan Meyers")
					, methodTest.checkStringGetsetOperation(ClassName.EMPLOYEE, 5, FieldName.DEPARTMENT, "Accounting")
					, methodTest.checkStringGetsetOperation(ClassName.EMPLOYEE, 0, FieldName.POSITION, "Vice President")
					, methodTest.checkIntGetsetOperation(ClassName.EMPLOYEE, 5, FieldName.ID_NUMBER, 47899)
					);
		default:
			return null;
		}
	}
}