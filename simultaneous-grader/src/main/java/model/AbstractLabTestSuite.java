package model;

import java.util.List;

import model.component.TestCase;

@Deprecated
public abstract class AbstractLabTestSuite {
	protected int defaultPoints = 5;

	public abstract List<TestCase> getAllTests(String question);
}
