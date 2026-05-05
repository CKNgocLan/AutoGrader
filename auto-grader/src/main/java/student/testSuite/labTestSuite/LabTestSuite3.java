package student.testSuite.labTestSuite;

import java.util.Arrays;
import java.util.List;

import student.constant.FieldName;
import student.constant.Question;
import student.model.ALabTestSuite;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.testSuite.BaseTester;
import student.testSuite.lab3.problem1.CashRegisterTester;
import student.testSuite.lab3.problem1.RetailItemTester;

public class LabTestSuite3 extends ALabTestSuite {

	@Override
	public List<ITestCase> getAllTests(String question) {
		int defaultPoints = 5;
		try {

			switch (question) {
			case Question.Q1:
				BaseTester retailItemTester = new RetailItemTester().getInstance();
				BaseTester cashRegisterTester = new CashRegisterTester().getInstance();
				
				String description = "DESCRIPTION";
				int unitsOnHand = 15;
				double price = 25.9;
				
				ParameterTesting[] retailItemArgs = {
						new ParameterTesting(FieldName.DESCRIPTION, String.class, description)
						, new ParameterTesting(FieldName.UNITS_ON_HAND, int.class, unitsOnHand)
						, new ParameterTesting(FieldName.PRICE, double.class, price)
				};
				
				int quantity = 3;

				return Arrays.asList(
						// retail item
						retailItemTester.checkExistence(defaultPoints)
						, retailItemTester.checkFields(defaultPoints,
								new FieldTesting(String.class, FieldName.DESCRIPTION),
								new FieldTesting(int.class, FieldName.UNITS_ON_HAND),
								new FieldTesting(double.class, FieldName.PRICE)
								)
						, retailItemTester.checkConstructorWithArgs(defaultPoints, String.class, int.class, double.class)
						, retailItemTester.checkConstructorOperation(defaultPoints, retailItemArgs)

						// cash register
						, cashRegisterTester.checkExistence(defaultPoints)
						, cashRegisterTester.checkFieldsAsSpecialModifiers(defaultPoints,
								new FieldTesting(retailItemTester.getCorrespondingClass(), FieldName.RETAIL_ITEM)
								, new FieldTesting(int.class, FieldName.QUANTITY)
								, new FieldTesting(double.class, FieldName.UPPERCASE_TAX_RATE).asStatic().asFinal()
								)
						, cashRegisterTester.checkConstructorWithArgs(defaultPoints,
								retailItemTester.getCorrespondingClass(), int.class
								)
						, cashRegisterTester.checkConstructorOperation(defaultPoints,
								new ParameterTesting(FieldName.RETAIL_ITEM, retailItemTester.getCorrespondingClass(),
										retailItemTester.instantiateWithAgrs(retailItemArgs)) 
								, new ParameterTesting(FieldName.QUANTITY, int.class, quantity)
								)
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