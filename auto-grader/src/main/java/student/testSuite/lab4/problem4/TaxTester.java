package student.testSuite.lab4.problem4;

import java.util.Arrays;
import java.util.List;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestingField;
import student.model.ITestCase;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class TaxTester extends BaseTester {
	public List<ITestCase> getAllTestcases() {
		return Arrays.asList(
				declare(defaultPoints)
				, declareFields(defaultPoints)
				);
	}

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
	public TestingField[] fields() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingField[] {
				new TestingField(double.class, FieldName.UNDERSCORE_8)
				, new TestingField(double.class, FieldName.UNDERSCORE_10)
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
	
	public ITestCase declareFields(int points) {
		try {
			return super.fieldTester.checkDeclarationsAsPublicStaticFinal(points, className, fields());
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}
}
