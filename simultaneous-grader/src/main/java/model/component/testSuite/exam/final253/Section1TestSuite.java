package model.component.testSuite.exam.final253;

import java.util.List;

import model.component.TestCase;
import model.component.testSuite.TestSuite;

public class Section1TestSuite extends TestSuite {
	private static Section1TestSuite instance;

	public static Section1TestSuite getInstance() {
		if (instance == null) {
			instance = new Section1TestSuite();
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