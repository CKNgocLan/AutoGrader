package model.service.mapper;

import java.util.HashMap;
import java.util.Map;

import common.constant.ProblemName;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.exam.final253.Section1TestSuiteFactory;
import model.component.testSuite.exam.final253.Section2TestSuiteFactory;

public class Final253FactoryMapper {

	private static HashMap<String, TestSuiteFactory> mapper = new HashMap<>(Map.of(
			ProblemName.SECTION_1, new Section1TestSuiteFactory()
			, ProblemName.SECTION_2, new Section2TestSuiteFactory()));

	private static HashMap<String, Double> weightMapper = new HashMap<>(Map.of(ProblemName.SECTION_1, 0.8, ProblemName.SECTION_2, 0.2));

	public static HashMap<String, TestSuiteFactory> getMapper() {
		return mapper;
	}

	public static HashMap<String, Double> getWeightMapper() {
		return weightMapper;
	}
}
