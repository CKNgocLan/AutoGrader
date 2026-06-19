package model.service.mapper;

import java.util.HashMap;
import java.util.Map;

import common.constant.ProblemName;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.lab3.Problem1TestSuiteFactory;
import model.component.testSuite.lab3.Problem2TestSuiteFactory;
import model.component.testSuite.lab3.Problem3TestSuiteFactory;
import model.component.testSuite.lab3.Problem4TestSuiteFactory;
import model.component.testSuite.lab3.Problem5TestSuiteFactory;

public class Lab7MapperFactory {
	private static HashMap<String, TestSuiteFactory> mapper = new HashMap<>(Map.of(
			ProblemName.P1, new Problem1TestSuiteFactory()
			, ProblemName.P2, new Problem2TestSuiteFactory()
			, ProblemName.P3, new Problem3TestSuiteFactory()
			, ProblemName.P4, new Problem4TestSuiteFactory()
			, ProblemName.P5, new Problem5TestSuiteFactory()
			));

	public static HashMap<String, TestSuiteFactory> getMapper() {
		return mapper;
	}

}
