package student.testSuite.lab3.problem5;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestingField;
import student.model.ITestCase;
import student.model.TestingParameter;
import student.testSuite.BaseTester;

public class ParkingMeterTester extends BaseTester {

	public ParkingMeterTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.PARKING_METER;
		super.getCorrespondingClass();
	}
	
	/*
	 * argument
	 */
	
	public TestingParameter[] constructorArgs(int purchasedMinutes) {
		return new TestingParameter[] {
			new TestingParameter(int.class, FieldName.PURCHASED_MINUTES, purchasedMinutes)	
		};
	}
	
	/*
	 * instantiate
	 */
	
	public Object instantiate(int purchasedMinutes)
			throws InstantiationException
			, IllegalAccessException
			, IllegalArgumentException
			, InvocationTargetException
			, NoSuchMethodException
			, SecurityException
			, ClassNotFoundException
			, TesterGotNoClassNameException {
		return super.instantiateWithArgs(constructorArgs(purchasedMinutes));
	}

	/*
	 * declare
	 */
	public ITestCase declare(int points) {
		return classTester.checkExistence(points, className);
	}

	/*
	 * fields
	 */
	
	public ITestCase declareFields(int points) {
		return fieldTester.checkDeclarations(points, className
				, new TestingField(int.class, FieldName.PURCHASED_MINUTES)
		);
	}

	/*
	 * constructor
	 */
	
	public ITestCase declareConstructor(int points) {
		return super.classTester.checkPartialArgsConstructorDeclaration(points, className, int.class);
	}

	public ITestCase operateConstructor(int points, int purchasedMinutes) {
		return super.classTester.checkPartialArgsConstructorOperationViaGetter(points, className, constructorArgs(purchasedMinutes));
	}
}
