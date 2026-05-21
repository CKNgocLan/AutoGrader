package student.testSuite.lab3.problem5;

import student.constant.ClassName;
import student.constant.Constants;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testSuite.BaseTester;

public class ConstTester extends BaseTester {

	public ConstTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.CONST;
		super.getCorrespondingClass();
	}

	/*
	 * declare
	 */
	public ITestCase declare(int points) {
		return classTester.checkExistence(points, className);
	}

	/*
	 * fields
	 */
	
	public ITestCase fields(int points) {
		return fieldTester.checkDeclarationsAsPublicStaticFinal(points, className
				, new FieldTesting(int.class, FieldName.UPPERCASE_FIRST_HOUR_FINE).setValue(Constants.FIRST_HOUR_FINE)
				, new FieldTesting(int.class, FieldName.UPPERCASE_ADDITIONAL_HOUR_FINE).setValue(Constants.ADDITIONAL_HOUR_FINE)
		);
	}
}
