package student.testSuite.midterm253;

import java.util.Arrays;
import java.util.List;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class PenTypeTester extends BaseTester {
	public List<ITestCase> getAllTestcases() {
		return Arrays.asList(
				declare(defaultPoints)
				, declareFields(defaultPoints)
				);
	}

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
	
	public ITestCase declareFields(int points) {
		try {
			return super.fieldTester.declareInEnum(points, className, fields());
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}
}
