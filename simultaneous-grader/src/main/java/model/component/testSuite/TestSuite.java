package model.component.testSuite;

import java.util.List;

import common.message.ExceptionMessage;
import model.component.TestCase;

public abstract class TestSuite {
	public abstract List<TestCase> getTestCases();
}
