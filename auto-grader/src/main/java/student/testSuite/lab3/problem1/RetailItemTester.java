package student.testSuite.lab3.problem1;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.testSuite.BaseTester;

public class RetailItemTester extends BaseTester {
	public RetailItemTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.RETAIL_ITEM;
		super.getCorrespondingClass();
	}
	
	/*
	 * instance ***************
	 */

	@Override
	public BaseTester getInstance() throws ClassNotFoundException, TesterGotNoClassNameException {
		if (instance == null) {
			instance = new RetailItemTester();
		}

		return instance;
	}
}
