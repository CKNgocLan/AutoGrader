package student.testSuite.lab4.problem4;

import java.util.Arrays;
import java.util.List;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class CountryTester extends BaseTester {
	public List<ITestCase> getAllTestcases() {
		return Arrays.asList(
				declare(defaultPoints)
				, declareFields(defaultPoints)
				);
	}

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
				new FieldTesting(String.class, FieldName.UPPERCASE_JAPAN)
				, new FieldTesting(String.class, FieldName.UPPERCASE_VIETNAM)
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
