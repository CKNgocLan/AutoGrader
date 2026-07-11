package student.testSuite.exam.midterm254;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.Tax;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.model.TestingField;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class TeaTaxTester extends BaseTester {
	public TeaTaxTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.TEA_TAX;
		super.getCorrespondingClass();
	}
	
	/**
	 * field
	 */
	
	private TestingField[] fields() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingField[] {
				new TestingField(double.class, FieldName.UPPERCASE_TEA_TAX).setValue(Tax._8)
		};
	}
	
	public TestCase declareFields() {
		try {
			return super.fieldTester.checkDeclarationsAsPublicStaticFinal(defaultPoints, className, fields());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}
	
	/**
	 * declare
	 */
	
	public TestCase declare() {
		return super.declare(defaultPoints);
	}
}
