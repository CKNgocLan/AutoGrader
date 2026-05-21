package student.testSuite.lab3.problem5;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.Constants;
import student.constant.FieldName;
import student.constant.MethodName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.MethodTesting;
import student.model.ParameterTesting;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class ParkingTicketTester extends BaseTester {
	private ParkedCarTester parkedCarTester;
	private PoliceOfficerTester policeOfficerTester;

	public ParkingTicketTester(ParkedCarTester parkedCarTester, PoliceOfficerTester policeOfficerTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.PARKING_TICKET;
		super.getCorrespondingClass();
		
		this.parkedCarTester = parkedCarTester;
		this.policeOfficerTester = policeOfficerTester;
	}
	
	/*
	 * argument
	 */
	
	public ParameterTesting[] constructorArgs(int gallon) {
		return new ParameterTesting[] {
			new ParameterTesting(int.class, FieldName.GALLON, gallon)	
		};
	}
	
	/*
	 * instantiate
	 */
	
	public Object instantiate(int gallon)
			throws InstantiationException
			, IllegalAccessException
			, IllegalArgumentException
			, InvocationTargetException
			, NoSuchMethodException
			, SecurityException
			, ClassNotFoundException
			, TesterGotNoClassNameException {
		return super.instantiateWithArgs(constructorArgs(gallon));
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
		try {
			return fieldTester.checkDeclarations(points, className
					, new FieldTesting(parkedCarTester.getCorrespondingClass(), FieldName.CAR)
					, new FieldTesting(policeOfficerTester.getCorrespondingClass(), FieldName.OFFICER)
					, new FieldTesting(double.class, FieldName.FINE_AMOUNT)
			);
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}

	/*
	 * constructor
	 */
	
	public ITestCase declareConstructor(int points) {
		try {
			return super.classTester.checkPartialArgsConstructorDeclaration(points, className, parkedCarTester.getCorrespondingClass(), policeOfficerTester.getCorrespondingClass(), double.class);
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}

	public ITestCase operateConstructor(int points, int gallon) {
		return super.classTester.checkPartialArgsConstructorOperationViaGetter(points, className, constructorArgs(gallon));
	}
	
	/*
	 * increaseGallon
	 */
	
	private MethodTesting increaseGallonMethod() {
		return new MethodTesting(void.class, MethodName.INCREASE_GALLON);
	}
	
	public ITestCase operateIncreaseGallon(int points, int gallon) {
		try {
			return super.methodTester.operationAsVoidAndCompareIntField(points
					, increaseGallonMethod()
							.config(getCorrespondingClass(), instantiate(gallon))
							.expectedValue(gallon < Constants.CAR_MAX_GALLON ? gallon + 1 : gallon)
					, FieldName.GALLON);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}
	
	/*
	 * decrementGallon
	 */
	
	private MethodTesting decrementGallonMethod() {
		return new MethodTesting(void.class, MethodName.DECREMENT_GALLON);
	}
	
	public ITestCase operateDecrementGallon(int points, int gallon) {
		try {
			return super.methodTester.operationAsVoidAndCompareIntField(points
					, decrementGallonMethod()
							.config(getCorrespondingClass(), instantiate(gallon))
							.expectedValue(gallon > 0 ? gallon - 1 : gallon)
					, FieldName.GALLON);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}
}
