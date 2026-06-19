package model.service.mapper;

import java.util.HashMap;

import common.constant.PropertyName;
import common.constant.TopicName;
import model.component.testSuite.TestSuiteFactory;
import model.exception.InvalidConfigurationException;

public class TestSuiteFactoryMapper {
	private static HashMap<String, TestSuiteFactory> mapper;

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
				mapper = Lab1FactoryMapper.getMapper();
				break;
			case TopicName.L2:
				mapper = Lab2FactoryMapper.getMapper();
				break;
			case TopicName.L3:
				mapper = Lab3FactoryMapper.getMapper();
				break;
			case TopicName.L4:
				mapper = Lab4FactoryMapper.getMapper();
				break;
			case TopicName.L5:
				mapper = Lab5FactoryMapper.getMapper();
				break;
			case TopicName.L6:
				mapper = Lab6FactoryMapper.getMapper();
				break;
			case TopicName.L7:
				mapper = Lab7FactoryMapper.getMapper();
				break;
			case TopicName.MIDTERM_253:
				mapper = Midterm253FactoryMapper.getMapper();
				break;
			case TopicName.FINAL_253:
				mapper = Final253FactoryMapper.getMapper();
				break;
		}

		return mapper;
	}

	public static int getProblemNumber(String topic) throws InvalidConfigurationException {
		if (mapper == null || mapper.isEmpty()) {
			throw new InvalidConfigurationException(PropertyName.TEST_SUITE_FACTORY_MAPPER);
		}

		return mapper.size();
	}
}
