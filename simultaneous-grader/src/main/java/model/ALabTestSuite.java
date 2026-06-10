package model;

import java.util.List;

public abstract class ALabTestSuite {
	protected int defaultPoints = 5;

	public abstract List<ITestCase> getAllTests(String question);
}
