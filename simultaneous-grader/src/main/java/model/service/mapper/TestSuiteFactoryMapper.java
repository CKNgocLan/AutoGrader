package model.service.mapper;

import java.util.HashMap;

import common.constant.TopicName;
import model.component.testSuite.TestSuiteFactory;

public class TestSuiteFactoryMapper {
	private static HashMap<String, TestSuiteFactory> factoryMapper;

	public static HashMap<String, TestSuiteFactory> getFactoryMapper(String topic) {
		switch (topic) {
			case TopicName.L1:
				factoryMapper = Lab1FactoryMapper.getMapper();
				break;
			case TopicName.L2:
				factoryMapper = Lab2FactoryMapper.getMapper();
				break;
			case TopicName.L3:
				factoryMapper = Lab3FactoryMapper.getMapper();
				break;
			case TopicName.L4:
				factoryMapper = Lab4FactoryMapper.getMapper();
				break;
			case TopicName.L5:
				factoryMapper = Lab5FactoryMapper.getMapper();
				break;
			case TopicName.L6:
				factoryMapper = Lab6FactoryMapper.getMapper();
				break;
			case TopicName.L7:
				factoryMapper = Lab7FactoryMapper.getMapper();
				break;
			case TopicName.MIDTERM_253:
				factoryMapper = Midterm253FactoryMapper.getMapper();
				break;
			case TopicName.FINAL_253:
				factoryMapper = Final253FactoryMapper.getMapper();
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
