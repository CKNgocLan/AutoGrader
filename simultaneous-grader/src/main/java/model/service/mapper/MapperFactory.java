package model.service.mapper;

import java.util.HashMap;

import model.component.testSuite.TestSuiteFactory;

public interface MapperFactory {
	public HashMap<String, TestSuiteFactory> getFactoryMapper(String topic);
}
