package student.testSuite.exam.final254;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.Tax;
import student.exception.TesterGotNoClassNameException;
import student.model.TestCase;
import student.model.TestingField;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class TaxTester extends BaseTester {
	public TaxTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.TAX;
		super.getCorrespondingClass();
	}
	
	/**
	 * field
	 */
	
	private TestingField[] fields() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingField[] {
				new TestingField(double.class, FieldName.UPPERCASE_TEA).setValue(Tax._10)
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
