package model.service.mapper;

import java.util.HashMap;
import java.util.Map;

import common.constant.ProblemName;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.exam.final253.Section1TestSuiteFactory;

public class Midterm253FactoryMapper {
	private static HashMap<String, TestSuiteFactory> testSuiteFactoryMapper = new HashMap<>(Map.of(
			ProblemName.SECTION_1, new Section1TestSuiteFactory()
			));

	private static HashMap<String, Double> weightMapper = new HashMap<>(Map.of(
			ProblemName.SECTION_1, 1.0
			));

	public static HashMap<String, TestSuiteFactory> getMapper() {
		return testSuiteFactoryMapper;
	}

	public static HashMap<String, Double> getWeight() {
		return weightMapper;
	}
}
