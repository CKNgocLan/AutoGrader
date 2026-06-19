package model.component.testSuite.concrete.exam.final253;

import java.util.List;

import model.component.TestCase;
import model.component.testSuite.TestSuite;

public class Section2TestSuite extends TestSuite {
	private static Section2TestSuite instance;

	public static Section2TestSuite getInstance() {
		if (instance == null) {
			instance = new Section2TestSuite();
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