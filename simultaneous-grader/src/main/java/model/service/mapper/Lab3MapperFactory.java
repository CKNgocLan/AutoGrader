package model.service.mapper;

import java.util.HashMap;
import java.util.Map;

import common.constant.ProblemName;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.factory.Lab3Problem1TestSuiteFactory;
import model.component.testSuite.factory.Lab3Problem2TestSuiteFactory;
import model.component.testSuite.factory.Lab3Problem3TestSuiteFactory;
import model.component.testSuite.factory.Lab3Problem4TestSuiteFactory;
import model.component.testSuite.factory.Lab3Problem5TestSuiteFactory;

public class Lab3MapperFactory implements MapperFactory {
	private TestSuiteFactory l3p1 = new Lab3Problem1TestSuiteFactory();
	private TestSuiteFactory l3p2 = new Lab3Problem2TestSuiteFactory();
	private TestSuiteFactory l3p3 = new Lab3Problem3TestSuiteFactory();
	private TestSuiteFactory l3p4 = new Lab3Problem4TestSuiteFactory();
	private TestSuiteFactory l3p5 = new Lab3Problem5TestSuiteFactory();
	
	private HashMap<String, TestSuiteFactory> lab3Mapper = new HashMap<>(Map.of(
			ProblemName.P1, l3p1
			, ProblemName.P2, l3p2
			, ProblemName.P3, l3p3
			, ProblemName.P4, l3p4
			, ProblemName.P5, l3p5
			));

	@Override
	public HashMap<String, TestSuiteFactory> getFactoryMapper(String topic) {
		return lab3Mapper;
	}

}
