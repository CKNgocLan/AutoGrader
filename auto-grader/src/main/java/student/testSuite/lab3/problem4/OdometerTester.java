package student.testSuite.lab3.problem4;

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

public class OdometerTester extends BaseTester {
	private FuelGaugeTester fuelGaugeTester;

	public OdometerTester(FuelGaugeTester fuelGaugeTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.ODOMETER;
		super.getCorrespondingClass();
		
		this.fuelGaugeTester = fuelGaugeTester;
	}
	
	/*
	 * argument
	 */
	
	public ParameterTesting[] constructorArgs(Object fuelGauge) throws ClassNotFoundException, TesterGotNoClassNameException {
		return new ParameterTesting[] {
			new ParameterTesting(fuelGaugeTester.getCorrespondingClass(), FieldName.FUEL_GAUGE, fuelGauge)	
		};
	}
	
	/*
	 * instantiate
	 */
	
	public Object instantiate(Object fuelGauge)
			throws InstantiationException
			, IllegalAccessException
			, IllegalArgumentException
			, InvocationTargetException
			, NoSuchMethodException
			, SecurityException
			, ClassNotFoundException
			, TesterGotNoClassNameException {
		return super.instantiateWithArgs(constructorArgs(fuelGauge));
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
	
	public ITestCase fields(int points) {
		try {
			return fieldTester.checkDeclarations(points, className
					, new FieldTesting(int.class, FieldName.MILEAGE)
					, new FieldTesting(fuelGaugeTester.getCorrespondingClass(), FieldName.FUEL_GAUGE)
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
			return super.classTester.checkPartialArgsConstructorDeclaration(points, className,
					fuelGaugeTester.getCorrespondingClass());
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}

	public ITestCase operateConstructor(int points, Object fuelGauge) {
		try {
			return super.classTester.checkPartialArgsConstructorOperation(points, className, constructorArgs(fuelGauge));
		} catch (ClassNotFoundException | TesterGotNoClassNameException e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}
	
	/*
	 * incrementMileage
	 */
	
	private MethodTesting incrementMileageMethod() {
		return new MethodTesting(void.class, MethodName.INCREMENT_MILEAGE);
	}
	
	public ITestCase declareIncrementMileage(int points) {
		return super.methodTester.checkExistence(points, className, incrementMileageMethod());
	}
	
	public ITestCase operateIncrementMileage(int points, int expected) {
		try {
			return super.methodTester.operationAsVoidAndCompareIntField(points,
					incrementMileageMethod()
							.config(getCorrespondingClass(), instantiate(fuelGaugeTester.instantiate(1)))
							.expectedValue(expected),
					FieldName.MILEAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}
	
	/*
	 * getMileage
	 */
	
	private MethodTesting getMileageMethod() {
		return new MethodTesting(int.class, MethodName.GET_MILEAGE);
	}
	
	public ITestCase declareGetMileage(int points) {
		return super.methodTester.checkExistence(points, className, getMileageMethod());
	}
	
	public ITestCase operateGetMileage(int points, int expected) {
		try {
			return super.methodTester.checkOperationAsNumberic(points, getMileageMethod()
					.config(getCorrespondingClass(), instantiate(fuelGaugeTester.instantiate(1)))
					.expectedValue(expected));
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}
}
