package model;

import model.component.testSuite.TestSuite;

public interface TestKit {
	public String getProblemName();
	public TestSuite getTestSuite();
	public double getWeight();
}
