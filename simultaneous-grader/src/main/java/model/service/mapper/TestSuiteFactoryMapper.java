package model.service.mapper;

import java.util.HashMap;

import common.constant.PropertyName;
import common.constant.TopicName;
import model.component.testSuite.TestSuiteFactory;
import model.exception.InvalidConfigurationException;

public class TestSuiteFactoryMapper {
	private static HashMap<String, TestSuiteFactory> testSuiteFactoryMapper;
	private static HashMap<String, Double> weightMapper;

//	private static TestSuiteFactoryMapper instance;
//
//	private TestSuiteFactoryMapper(FactoryMapper factoryMapper) {
//		mapper = factoryMapper.getMapper();
//	}

//	public static TestSuiteFactoryMapper getInstance(FactoryMapper factoryMapper) {
//		if (instance == null) {
//			instance = new TestSuiteFactoryMapper(factoryMapper);
//		}
//
//		return instance;
//	}

	public static HashMap<String, TestSuiteFactory> getFactoryMapper(String topic) {
		switch (topic) {
			case TopicName.L1:
				testSuiteFactoryMapper = Lab1FactoryMapper.getMapper();
				break;
			case TopicName.L2:
				testSuiteFactoryMapper = Lab2FactoryMapper.getMapper();
				break;
			case TopicName.L3:
				testSuiteFactoryMapper = Lab3FactoryMapper.getMapper();
				break;
			case TopicName.L4:
				testSuiteFactoryMapper = Lab4FactoryMapper.getMapper();
				break;
			case TopicName.L5:
				testSuiteFactoryMapper = Lab5FactoryMapper.getMapper();
				break;
			case TopicName.L6:
				testSuiteFactoryMapper = Lab6FactoryMapper.getMapper();
				break;
			case TopicName.L7:
				testSuiteFactoryMapper = Lab7FactoryMapper.getMapper();
				break;
			case TopicName.MIDTERM_253:
				testSuiteFactoryMapper = Midterm253FactoryMapper.getMapper();
				break;
			case TopicName.FINAL_253:
				testSuiteFactoryMapper = Final253FactoryMapper.getMapper();
				break;
		}

		return testSuiteFactoryMapper;
	}

	public static HashMap<String, Double> getWeights(String topic) {
		switch (topic) {
			case TopicName.L1:
			case TopicName.L2:
			case TopicName.L3:
			case TopicName.L4:
			case TopicName.L5:
			case TopicName.L6:
			case TopicName.L7:
			case TopicName.MIDTERM_253:
			case TopicName.FINAL_253:
				weightMapper = Final253FactoryMapper.getWeightMapper();
				break;
		}
	
		return weightMapper;
	}

	public static int getProblemNumber(String topic) throws InvalidConfigurationException {
		if (testSuiteFactoryMapper == null || testSuiteFactoryMapper.isEmpty()) {
			throw new InvalidConfigurationException(PropertyName.TEST_SUITE_FACTORY_MAPPER);
		}

		return testSuiteFactoryMapper.size();
	}
}
