package model.component.testSuite.concrete;

import java.util.List;

import model.component.TestCase;
import model.component.testSuite.TestSuite;
import model.element.TestingParameter;
import tester.lab3.problem1.CashRegisterTester;
import tester.lab3.problem1.RetailItemTester;

public class Lab3Problem1TestSuite extends TestSuite {

	@Override
	public List<TestCase> getTestCases() {
		try {
			RetailItemTester retailItemTester = new RetailItemTester();
			CashRegisterTester cashRegisterTester = new CashRegisterTester(retailItemTester);

			double price = 25.9;
			TestingParameter[] retailItemArgs = retailItemTester.createArgs("DESCRIPTION", 15, price);

			int quantity = 3;
			TestingParameter[] cashRegisterArgs = cashRegisterTester
					.createArgs(retailItemTester.instantiate(retailItemArgs), quantity);

			double tax_rate = 0.06;
			
			double subtotal = price * quantity;
			double totalTax = subtotal * tax_rate;
			double total = subtotal + totalTax;

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return List.of();
		}
	}

}
