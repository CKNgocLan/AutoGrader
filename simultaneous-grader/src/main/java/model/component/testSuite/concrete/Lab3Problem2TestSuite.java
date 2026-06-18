package model.component.testSuite.concrete;

import java.util.List;

import model.component.TestCase;
import model.component.testSuite.TestSuite;

public class Lab3Problem2TestSuite extends TestSuite {
	private static Lab3Problem2TestSuite instance;

	public static Lab3Problem2TestSuite getInstance() {
		if (instance == null) {
			instance = new Lab3Problem2TestSuite();
		}
		return instance;
	}

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
