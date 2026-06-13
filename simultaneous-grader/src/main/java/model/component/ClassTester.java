package model.component;

import java.util.List;

import model.resultReport.TestCaseResult;

public interface ClassTester {
	public List<TestCaseResult> runTestCases();
}
