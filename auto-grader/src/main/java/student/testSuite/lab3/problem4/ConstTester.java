package student.testSuite.lab3.problem4;

import student.constant.ClassName;
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
				, new FieldTesting(int.class, FieldName.UPPERCASE_CAR_MAX_GALLON)
				, new FieldTesting(int.class, FieldName.UPPERCASE_ODOMETER_MAXIMUM_MILEAGE_MILES)
				, new FieldTesting(int.class, FieldName.UPPERCASE_MILES_PER_ONE_GALLON)
		);
	}
}
