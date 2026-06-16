package model.component.testSuite;

public interface TestSuiteFactory {
	public String getTopic();
	public String getProblem();
	public TestSuite createTestSuite();
}
