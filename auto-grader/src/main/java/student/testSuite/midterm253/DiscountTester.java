package student.testSuite.midterm253;

import java.time.LocalDate;
import java.util.UUID;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testSuite.BaseTester;

public class DiscountTester extends BaseTester {

	/*
	 * instantiate ***************
	 */

	public DiscountTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.DISCOUNT;
		super.getCorrespondingClass();
	}

	/*
	 * 
	 */
	public FieldTesting[] fields() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new FieldTesting[] { new FieldTesting(UUID.class, FieldName.ID),
				new FieldTesting(double.class, FieldName.PERCENT),
				new FieldTesting(LocalDate.class, FieldName.START_DATE),
				new FieldTesting(LocalDate.class, FieldName.END_DATE) };
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
	 * constructor
	 */

	public ITestCase declareConstructor(int points) throws ClassNotFoundException {
		return super.checkConstructorDeclaration(points, double.class, LocalDate.class);
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
