package student.testSuite.lab3.problem5;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.Constants;
import student.constant.Feedback;
import student.constant.FieldName;
import student.constant.MethodName;
import student.constant.TestcaseType;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.MethodTesting;
import student.model.ParameterTesting;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;
import student.util.ValueUtils;

public class PoliceOfficerTester extends BaseTester {
	private ParkedCarTester parkedCarTester;
	private ParkingMeterTester parkingMeterTester;
	private ParkingTicketTester parkingTicketTester;

	public PoliceOfficerTester(ParkedCarTester parkedCarTester, ParkingMeterTester parkingMeterTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.POLICE_OFFICER;
		super.getCorrespondingClass();

		this.parkedCarTester = parkedCarTester;
		this.parkingMeterTester = parkingMeterTester;
	}
	
	public PoliceOfficerTester setParkingTicketTester(ParkingTicketTester parkingTicketTester) {
		this.parkingTicketTester = parkingTicketTester;
		return this;
	}
	
	/*
	 * argument
	 */
	
	public ParameterTesting[] constructorArgs(String name, String badgeNumber) {
		return new ParameterTesting[] {
			new ParameterTesting(String.class, FieldName.NAME, name)	
			, new ParameterTesting(String.class, FieldName.BADGE_NUMBER, badgeNumber)	
		};
	}
	
	/*
	 * instantiate
	 */
	
	public Object instantiate(String name, String badgeNumber)
			throws InstantiationException
			, IllegalAccessException
			, IllegalArgumentException
			, InvocationTargetException
			, NoSuchMethodException
			, SecurityException
			, ClassNotFoundException
			, TesterGotNoClassNameException {
		return super.instantiateWithArgs(constructorArgs(name, badgeNumber));
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
				, new FieldTesting(String.class, FieldName.NAME)
				, new FieldTesting(String.class, FieldName.BADGE_NUMBER)
		);
	}

	/*
	 * constructor
	 */
	
	public ITestCase declareConstructor(int points) {
		return super.classTester.checkPartialArgsConstructorDeclaration(points, className, String.class, String.class);
	}

	public ITestCase operateConstructor(int points, String name, String badgeNumber) {
		return super.classTester.checkPartialArgsConstructorOperationViaGetter(points, className, constructorArgs(name, badgeNumber));
	}

	/*
	 * examineCar
	 */
	
	private MethodTesting examineCarMethod() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new MethodTesting(boolean.class, MethodName.EXAMINE_CAR
				, new ParameterTesting(parkedCarTester.getCorrespondingClass())
				, new ParameterTesting(parkingMeterTester.getCorrespondingClass())
		);
	}
	
	private MethodTesting examineCarMethod(int parkedMinutes, int purchasedMinutes) throws ClassNotFoundException, TesterGotNoClassNameException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return new MethodTesting(boolean.class, MethodName.EXAMINE_CAR
				, new ParameterTesting(parkedCarTester.getCorrespondingClass(), FieldName.PARKED_CAR, parkedCarTester.instantiate(parkedMinutes))
				, new ParameterTesting(parkingMeterTester.getCorrespondingClass(), FieldName.PARKING_METER, parkingMeterTester.instantiate(purchasedMinutes))
		);
	}
	
	public ITestCase declareExamineCar(int points) {
		try {
			return super.methodTester.checkExistence(points, className, examineCarMethod());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}
	
	public ITestCase operateExamineCar(int points, int parkedMinutes, int purchasedMinutes) {
		return operateExamineCar(points, Constants.DEFAULT_NAME, Constants.DEFAULT_BADGE_NUMBER, parkedMinutes, purchasedMinutes);
	}
	
	public ITestCase operateExamineCar(int points, String name, String badgeNumber, int parkedMinutes, int purchasedMinutes) {
		try {
			return super.methodTester.returnBoolean(points, examineCarMethod(parkedMinutes, purchasedMinutes)
						.config(getCorrespondingClass(), instantiate(name, badgeNumber))
						.expectedValue(parkedMinutes > purchasedMinutes)
				);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}
	
	/*
	 * issueTicket
	 */
	
	private MethodTesting issueTicketMethod() throws ClassNotFoundException, TesterGotNoClassNameException {
		return new MethodTesting(this.parkingTicketTester.getCorrespondingClass()
				, MethodName.ISSUE_TICKET
				, new ParameterTesting(parkedCarTester.getCorrespondingClass())
				, new ParameterTesting(parkingMeterTester.getCorrespondingClass())
		);
	}
	
	private MethodTesting issueTicketMethod(int parkedMinutes, int purchasedMinutes) throws ClassNotFoundException, TesterGotNoClassNameException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return new MethodTesting(this.parkingTicketTester.getCorrespondingClass()
				, MethodName.ISSUE_TICKET
				, new ParameterTesting(parkedCarTester.getCorrespondingClass(), FieldName.PARKED_CAR, parkedCarTester.instantiate(parkedMinutes))
				, new ParameterTesting(parkingMeterTester.getCorrespondingClass(), FieldName.PARKING_METER, parkingMeterTester.instantiate(purchasedMinutes))
		);
	}
	
	private Double calcualteFine(int parkedMinutes, int purchasedMinutes) {
		if (parkedMinutes <= purchasedMinutes) {
			return null;
		}

		int overtimeMinutes = parkedMinutes - purchasedMinutes;
        double fine = Constants.FIRST_HOUR_FINE;

        if (overtimeMinutes > 60) {
            fine += (overtimeMinutes - 60 + 59) / 60 * Constants.ADDITIONAL_HOUR_FINE;
        }
        
        return fine;
	}
	
	public ITestCase declareIssueTicket(int points) {
		try {
			return super.methodTester.checkExistence(points, className, issueTicketMethod());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}
	
	public ITestCase operateIssueTicket(int points, int parkedMinutes, int purchasedMinutes) {
		return operateIssueTicket(points, Constants.DEFAULT_NAME, Constants.DEFAULT_BADGE_NUMBER, parkedMinutes, purchasedMinutes);
	}
	
	public ITestCase operateIssueTicket(int points, String name, String badgeNumber, int parkedMinutes, int purchasedMinutes) {
		MethodTesting method;
		try {
			method = issueTicketMethod(parkedMinutes, purchasedMinutes);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}

		return new ITestCase() {

			@Override
			public String getName() {
				return TestcaseType.CHECK_METHOD_OPERATION.getName(className, method.getName());
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Object actualParkingTicketInstance = getCorrespondingClass().getDeclaredMethod(method.getName(), method.getParameterTypes())
							.invoke(instantiate(name, badgeNumber), method.getParameterValues());
					Double fine = calcualteFine(parkedMinutes, purchasedMinutes);
					
					if (fine != null) {
						if (actualParkingTicketInstance == null) {
							return false;
						}
						
						double actualFine = ValueUtils.toDoublePrimitive(getFieldAsAccessible(parkingTicketTester.getCorrespondingClass(), FieldName.FINE_AMOUNT)
								.get(actualParkingTicketInstance));
						if (actualFine != fine.doubleValue()) {
							return false;
						}
					} else if (actualParkingTicketInstance != null) {
						return false;
					}
					
					return true;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(className, method.getName());
			}
		};
	}
	
}
