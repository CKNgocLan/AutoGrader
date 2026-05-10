package student.testSuite.midterm253;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testSuite.BaseTester;

public class PenTypeTester extends BaseTester {

	/*
	 * instantiate ***************
	 */

	public PenTypeTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.PEN_TYPE;
		super.getCorrespondingClass();
	}
	
	/*
	 * 
	 */
	public FieldTesting[] fields() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new FieldTesting[] {
				new FieldTesting(super.getCorrespondingClass(), FieldName.UPPERCASE_BALLPOINT)
				, new FieldTesting(super.getCorrespondingClass(), FieldName.UPPERCASE_FOUNTAIN)
		};
	}
	
	/*
	 * declaration
	 */
	public ITestCase declare(int points) {
		return super.declareAsEnum(points);
	}
	
	/*
	 * field ***************
	 */
	
	public ITestCase declareFields(int points) throws ClassNotFoundException, TesterGotNoClassNameException {
		return super.fieldTester.checkDeclarationsAsPublicStaticFinal(points, className, fields());
	}
}
