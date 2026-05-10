package student.testSuite.midterm253;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testSuite.BaseTester;

public class TaxTester extends BaseTester {

	/*
	 * instantiate ***************
	 */

	public TaxTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.TAX;
		super.getCorrespondingClass();
	}
	
	/*
	 * 
	 */
	public FieldTesting[] fields() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new FieldTesting[] {
				new FieldTesting(double.class, FieldName.UNDERSCORE_8)
				, new FieldTesting(double.class, FieldName.UNDERSCORE_10)
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
