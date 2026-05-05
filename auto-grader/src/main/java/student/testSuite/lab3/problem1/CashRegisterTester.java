package student.testSuite.lab3.problem1;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.testSuite.BaseTester;

public class CashRegisterTester extends BaseTester{
	public CashRegisterTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.CASH_REGISTER;
		super.getCorrespondingClass();
	}

	
	/*
	 * instance ***************
	 */

	@Override
	public BaseTester getInstance() throws ClassNotFoundException, TesterGotNoClassNameException {
		if (instance == null) {
			instance = new CashRegisterTester();
		}

		return instance;
	}
}
