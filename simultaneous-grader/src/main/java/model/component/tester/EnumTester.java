package model.component.tester;

import model.exception.TesterGotNoClassNameException;

public interface EnumTester {
	public Object valueFrom(String name) throws ClassNotFoundException, TesterGotNoClassNameException;
}
