package student.model;

import student.exception.TesterGotNoClassNameException;

public interface IEnumTester {
	public Object valueFrom(String name) throws ClassNotFoundException, TesterGotNoClassNameException;
}
