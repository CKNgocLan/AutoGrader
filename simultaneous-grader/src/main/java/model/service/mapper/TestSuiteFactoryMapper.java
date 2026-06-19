package model.service.mapper;

import java.util.HashMap;
import java.util.Map;

import common.constant.ProblemName;
import common.constant.TopicName;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.exam.final253.Section1TestSuiteFactory;
import model.component.testSuite.exam.final253.Section2TestSuiteFactory;
import model.component.testSuite.lab3.Problem1TestSuiteFactory;
import model.component.testSuite.lab3.Problem2TestSuiteFactory;
import model.component.testSuite.lab3.Problem3TestSuiteFactory;
import model.component.testSuite.lab3.Problem4TestSuiteFactory;
import model.component.testSuite.lab3.Problem5TestSuiteFactory;

public class TestSuiteFactoryMapper {
	private static HashMap<String, TestSuiteFactory> labMapper3 = new HashMap<>(Map.of(
			ProblemName.P1, new Problem1TestSuiteFactory()
			, ProblemName.P2, new Problem2TestSuiteFactory()
			, ProblemName.P3, new Problem3TestSuiteFactory()
			, ProblemName.P4, new Problem4TestSuiteFactory()
			, ProblemName.P5, new Problem5TestSuiteFactory()
			));

	private static HashMap<String, TestSuiteFactory> finalExamMapper253 = new HashMap<>(Map.of(
			ProblemName.SECTION_1, new Section1TestSuiteFactory()
			, ProblemName.SECTION_2, new Section2TestSuiteFactory()
			));


	private static HashMap<String, TestSuiteFactory> factoryMapper;

	public static HashMap<String, TestSuiteFactory> getFactoryMapper(String topic) {
		switch (topic) {
			case TopicName.L3:
				factoryMapper = labMapper3;
				break;
			case TopicName.FINAL_253:
				factoryMapper = finalExamMapper253;
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
