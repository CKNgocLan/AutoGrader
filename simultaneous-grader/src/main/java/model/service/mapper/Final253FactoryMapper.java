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
			, ProblemName.SECTION_2, new Section2TestSuiteFactory()
			));

	public static HashMap<String, TestSuiteFactory> getMapper() {
		return mapper;
	}

}
