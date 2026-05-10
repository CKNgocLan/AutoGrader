package student.testSuite.midterm253;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testSuite.BaseTester;

public class CountryTester extends BaseTester {

	/*
	 * instantiate ***************
	 */

	public CountryTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.COUNTRY;
		super.getCorrespondingClass();
	}
	
	/*
	 * 
	 */
	public FieldTesting[] fields() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new FieldTesting[] {
				new FieldTesting(super.getCorrespondingClass(), FieldName.UPPERCASE_JAPAN)
				, new FieldTesting(super.getCorrespondingClass(), FieldName.UPPERCASE_VIETNAM)
		};
	}
	
	/*
	 * declaration
	 */
	public ITestCase declare(int points) {
		return super.declare(points);
	}
	
	/*
	 * field ***************
	 */
	
	public ITestCase declareFields(int points) throws ClassNotFoundException, TesterGotNoClassNameException {
		return super.fieldTester.checkDeclarationsAsPublicStaticFinal(points, className, fields());
	}
}
