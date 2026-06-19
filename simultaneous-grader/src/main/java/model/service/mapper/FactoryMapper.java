package model.service.mapper;

import java.util.HashMap;

import model.component.testSuite.TestSuiteFactory;

public abstract class FactoryMapper {
	public abstract HashMap<String, TestSuiteFactory> getMapper();
}
