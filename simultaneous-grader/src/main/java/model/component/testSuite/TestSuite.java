package model.component.testSuite;

import java.util.List;

import model.component.TestCase;

public abstract class TestSuite {
	protected int defaultPoints = 5;

	public abstract List<TestCase> getTestCases();
}
