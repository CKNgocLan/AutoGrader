package model.component.testSuite.concrete;

import java.util.Arrays;
import java.util.List;

import model.component.TestCase;
import model.component.testSuite.TestSuite;
import model.element.TestingParameter;
import tester.lab3.problem1.CashRegisterTester;
import tester.lab3.problem1.RetailItemTester;

public class Lab3Problem2TestSuite extends TestSuite {

	@Override
	public List<TestCase> getTestCases() {
		try {
			return List.of();
		} catch (Exception e) {
			e.printStackTrace();
			return List.of();
		}
	}

}
