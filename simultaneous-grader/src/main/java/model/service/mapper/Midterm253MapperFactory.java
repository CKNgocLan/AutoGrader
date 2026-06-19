package model.service.mapper;

import java.util.HashMap;
import java.util.Map;

import common.constant.ProblemName;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.exam.final253.Section1TestSuiteFactory;

public class Midterm253MapperFactory {

	private static HashMap<String, TestSuiteFactory> mapper = new HashMap<>(Map.of(
			ProblemName.SECTION_1, new Section1TestSuiteFactory()
			));

	public static HashMap<String, TestSuiteFactory> getMapper() {
		return mapper;
	}

}
