package student.testSuite.midterm253;

import java.util.UUID;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testSuite.BaseTester;

public class BrandTester extends BaseTester {

	/*
	 * instantiate ***************
	 */

	public BrandTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.BRAND;
		super.getCorrespondingClass();
	}
	
	/*
	 * 
	 */
	public FieldTesting[] fields() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new FieldTesting[] {
				new FieldTesting(UUID.class, FieldName.ID)
				, new FieldTesting(String.class, FieldName.NAME)
				, new FieldTesting(String.class, FieldName.COUNTRY)
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
		return super.fieldTester.checkDeclarations(points, className, fields());
	}
	
	/*
	 * getter
	 */
	public ITestCase declareGetters(int points) {
		return super.methodTester.checkGetterDeclaration(points, className);
	}
	
	/*
	 * setter
	 */
	public ITestCase declareSetters(int points) {
		return super.methodTester.checkSetterDeclaration(points, className);
	}
	
	/*
	 * toString
	 */
	public ITestCase declareToString(int points) {
		return super.checkToStringDeclaration(points);
	}
}
