package model;

import java.util.List;

public abstract class AbstractLabTestSuite {
	protected int defaultPoints = 5;

	public abstract List<TestCase> getAllTests(String question);
}
