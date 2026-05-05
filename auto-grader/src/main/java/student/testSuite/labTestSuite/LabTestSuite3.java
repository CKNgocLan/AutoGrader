package student.testSuite.labTestSuite;

import java.util.Arrays;
import java.util.List;

import student.constant.FieldName;
import student.constant.Question;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.testSuite.lab3.problem1.CashRegisterTester;
import student.testSuite.lab3.problem1.RetailItemTester;

public class LabTestSuite3 extends ALabTestSuite {

	@Override
	public List<ITestCase> getAllTests(String question) {
		int defaultPoints = 5;
		try {

			switch (question) {
			case Question.Q1:
				RetailItemTester retailItemTester = new RetailItemTester();
				CashRegisterTester cashRegisterTester = new CashRegisterTester(retailItemTester);
				
				String description = "DESCRIPTION";
				int unitsOnHand = 15;
				double price = 25.9;
				
				ParameterTesting[] retailItemArgs = {
						new ParameterTesting(FieldName.DESCRIPTION, String.class, description)
						, new ParameterTesting(FieldName.UNITS_ON_HAND, int.class, unitsOnHand)
						, new ParameterTesting(FieldName.PRICE, double.class, price)
				};
				
				int quantity = 3;
				
				ParameterTesting[] cashRegisterArgs = {
						new ParameterTesting(FieldName.RETAIL_ITEM, retailItemTester.getCorrespondingClass(),
								retailItemTester.instantiateWithAgrs(retailItemArgs)),
						new ParameterTesting(FieldName.QUANTITY, int.class, quantity)
				};

				return Arrays.asList(
						// retail item
						retailItemTester.checkExistence(defaultPoints)
						, retailItemTester.checkFields(defaultPoints)
						, retailItemTester.checkConstructorDeclaration(defaultPoints)
						, retailItemTester.checkConstructorOperation(defaultPoints, retailItemArgs)
						, retailItemTester.checkGetterDeclaration(defaultPoints)
						, retailItemTester.checkSetterDeclaration(defaultPoints)

						// cash register
						, cashRegisterTester.checkExistence(defaultPoints)
						, cashRegisterTester.checkFields(defaultPoints)
						, cashRegisterTester.checkConstructorDeclaration(defaultPoints)
						, cashRegisterTester.checkConstructorOperation(defaultPoints, cashRegisterArgs)
						, cashRegisterTester.checkGetterDeclaration(defaultPoints)
						, cashRegisterTester.checkSetterDeclaration(defaultPoints)
						);
			case Question.Q2:
				return Arrays.asList();
			case Question.Q3:

				return Arrays.asList();
			case Question.Q4:
				return Arrays.asList();
			case Question.Q5:

				return Arrays.asList();
			case Question.Q6:
				return Arrays.asList();
			default:
				return null;
			}
		} catch (Exception e) {
			System.out.println("Class Not Found: %s".formatted(e.getMessage()));
			for (StackTraceElement st : e.getStackTrace()) {
				System.out.println(st);
			}

			return null;
		}
	}
}