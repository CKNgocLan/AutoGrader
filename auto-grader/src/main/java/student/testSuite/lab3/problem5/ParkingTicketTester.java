package student.testSuite.lab3.problem5;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
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
	
	public ParameterTesting[] constructorArgs(Object car, Object officer, double fineAmount) throws ClassNotFoundException, TesterGotNoClassNameException {
		return new ParameterTesting[] {
			new ParameterTesting(parkedCarTester.getCorrespondingClass(), FieldName.CAR, car)	
			, new ParameterTesting(policeOfficerTester.getCorrespondingClass(), FieldName.OFFICER, officer)	
			, new ParameterTesting(double.class, FieldName.FINE_AMOUNT, fineAmount)	
		};
	}
	
	/*
	 * instantiate
	 */
	
	public Object instantiate(double fineAmount)
			throws InstantiationException
			, IllegalAccessException
			, IllegalArgumentException
			, InvocationTargetException
			, NoSuchMethodException
			, SecurityException
			, ClassNotFoundException
			, TesterGotNoClassNameException {
		return super.instantiateWithArgs(constructorArgs(null, null, fineAmount));
	}
	
	public Object instantiate(Object car, Object officer, double fineAmount)
			throws InstantiationException
			, IllegalAccessException
			, IllegalArgumentException
			, InvocationTargetException
			, NoSuchMethodException
			, SecurityException
			, ClassNotFoundException
			, TesterGotNoClassNameException {
		return super.instantiateWithArgs(constructorArgs(car, officer, fineAmount));
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

	public ITestCase operateConstructor(int points, Object car, Object officer, double fineAmount) {
		try {
			return super.classTester.checkPartialArgsConstructorOperationViaGetter(points, className, constructorArgs(car, officer, fineAmount));
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}
}
