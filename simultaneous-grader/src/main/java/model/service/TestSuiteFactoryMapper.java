package model.service;

import java.util.HashMap;

import common.constant.ProblemName;
import common.constant.TopicName;
import model.component.testSuite.TestSuiteFactory;
import model.component.testSuite.factory.Lab3Problem1TestSuiteFactory;
import model.component.testSuite.factory.Lab3Problem2TestSuiteFactory;
import model.component.testSuite.factory.Lab3Problem3TestSuiteFactory;
import model.component.testSuite.factory.Lab3Problem4TestSuiteFactory;
import model.component.testSuite.factory.Lab3Problem5TestSuiteFactory;

public class TestSuiteFactoryMapper {
	private static TestSuiteFactory l3p1 = new Lab3Problem1TestSuiteFactory();
	private static TestSuiteFactory l3p2 = new Lab3Problem2TestSuiteFactory();
	private static TestSuiteFactory l3p3 = new Lab3Problem3TestSuiteFactory();
	private static TestSuiteFactory l3p4 = new Lab3Problem4TestSuiteFactory();
	private static TestSuiteFactory l3p5 = new Lab3Problem5TestSuiteFactory();

	public static HashMap<String, TestSuiteFactory> getFactoryMapper(String topic) {
		HashMap<String, TestSuiteFactory> factoryMapper = new HashMap<String, TestSuiteFactory>();
		switch (topic) {
		case TopicName.L3:
			factoryMapper.put(ProblemName.P1, l3p1);
			factoryMapper.put(ProblemName.P2, l3p2);
			factoryMapper.put(ProblemName.P3, l3p3);
			factoryMapper.put(ProblemName.P4, l3p4);
			factoryMapper.put(ProblemName.P5, l3p5);
			break;
		}
		return factoryMapper;
	}
}
