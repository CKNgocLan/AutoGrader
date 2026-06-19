package model.service.mapper;

import java.util.HashMap;
import java.util.Map;

import common.constant.ProblemName;
import common.constant.TopicName;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.factory.lab3.Problem1TestSuiteFactory;
import model.component.testSuite.factory.lab3.Problem2TestSuiteFactory;
import model.component.testSuite.factory.lab3.Problem3TestSuiteFactory;
import model.component.testSuite.factory.lab3.Problem4TestSuiteFactory;
import model.component.testSuite.factory.lab3.Problem5TestSuiteFactory;

public class TestSuiteFactoryMapper {
	private static TestSuiteFactory l3p1 = new Problem1TestSuiteFactory();
	private static TestSuiteFactory l3p2 = new Problem2TestSuiteFactory();
	private static TestSuiteFactory l3p3 = new Problem3TestSuiteFactory();
	private static TestSuiteFactory l3p4 = new Problem4TestSuiteFactory();
	private static TestSuiteFactory l3p5 = new Problem5TestSuiteFactory();
	
	private static HashMap<String, TestSuiteFactory> labMapper3 = new HashMap<>(Map.of(
			ProblemName.P1, l3p1
			, ProblemName.P2, l3p2
			, ProblemName.P3, l3p3
			, ProblemName.P4, l3p4
			, ProblemName.P5, l3p5
			));

	private static HashMap<String, TestSuiteFactory> factoryMapper;

	public static HashMap<String, TestSuiteFactory> getFactoryMapper(String topic) {
		switch (topic) {
			case TopicName.L3:
				factoryMapper = labMapper3;
				break;
		}

		return factoryMapper;
	}

	public static int getProblemNumber(String topic) {
		if (factoryMapper == null || factoryMapper.isEmpty()) {
			getFactoryMapper(topic);
		}

		return factoryMapper.size();
	}
}
