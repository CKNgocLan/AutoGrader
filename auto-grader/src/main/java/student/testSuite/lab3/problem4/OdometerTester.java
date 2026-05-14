package student.testSuite.lab3.problem4;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.testSuite.BaseTester;

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
	
	public ITestCase fields(int points) throws ClassNotFoundException
			, TesterGotNoClassNameException {
		return fieldTester.checkDeclarations(points, className
				, new FieldTesting(int.class, FieldName.MILEAGE)
				, new FieldTesting(fuelGaugeTester.getCorrespondingClass(), FieldName.FUEL_GAUGE)
		);
	}

	/*
	 * constructor
	 */
	
	public ITestCase declareConstructor(int points) throws ClassNotFoundException
			, TesterGotNoClassNameException {
		return super.classTester.checkPartialArgsConstructorDeclaration(points, className,
				fuelGaugeTester.getCorrespondingClass());
	}

	public ITestCase operateConstructor(int points, Object fuelGauge) throws ClassNotFoundException, TesterGotNoClassNameException {
		return super.classTester.checkPartialArgsConstructorOperation(points, className, constructorArgs(fuelGauge));
	}
}
