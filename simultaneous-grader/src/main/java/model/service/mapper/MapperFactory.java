package model.service.mapper;

import java.util.HashMap;

import model.component.testSuite.TestSuiteFactory;

@Deprecated
public interface MapperFactory {
	public HashMap<String, TestSuiteFactory> getMapper(String topic);
}
