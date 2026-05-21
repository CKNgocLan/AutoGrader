package student.testSuite.lab3.problem5;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class ParkedCarTester extends BaseTester {
	public ParkedCarTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.PARKED_CAR;
		super.getCorrespondingClass();
	}
	
	/*
	 * argument
	 */
	
	public ParameterTesting[] constructorArgs(String make, String model, String color, String licenseNumber, int parkedMinutes) throws ClassNotFoundException, TesterGotNoClassNameException {
		return new ParameterTesting[] {
			new ParameterTesting(String.class, FieldName.MAKE, make)
			, new ParameterTesting(String.class, FieldName.MODEL, model)
			, new ParameterTesting(String.class, FieldName.COLOR, color)
			, new ParameterTesting(String.class, FieldName.LICENSE_NUMBER, licenseNumber)
			, new ParameterTesting(int.class, FieldName.PARKED_MINUTES, parkedMinutes)
		};
	}
	
	/*
	 * instantiate
	 */
	
	public Object instantiate(String make, String model, String color, String licenseNumber, int parkedMinutes)
			throws InstantiationException
			, IllegalAccessException
			, IllegalArgumentException
			, InvocationTargetException
			, NoSuchMethodException
			, SecurityException
			, ClassNotFoundException
			, TesterGotNoClassNameException {
		return super.instantiateWithArgs(constructorArgs(make, model, color, licenseNumber, parkedMinutes));
	}
	
	public Object instantiate(int parkedMinutes)
			throws InstantiationException
			, IllegalAccessException
			, IllegalArgumentException
			, InvocationTargetException
			, NoSuchMethodException
			, SecurityException
			, ClassNotFoundException
			, TesterGotNoClassNameException {
		return instantiate("default make", "default model", "default color", "default license number", parkedMinutes);
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
				, new FieldTesting(String.class, FieldName.MAKE)
				, new FieldTesting(String.class, FieldName.MODEL)
				, new FieldTesting(String.class, FieldName.COLOR)
				, new FieldTesting(String.class, FieldName.LICENSE_NUMBER)
				, new FieldTesting(int.class, FieldName.PARKED_MINUTES)
		);
	}

	/*
	 * constructor
	 */
	
	public ITestCase declareConstructor(int points) {
		return super.classTester.checkPartialArgsConstructorDeclaration(points, className, String.class, String.class,
				String.class, String.class, int.class);
	}

	public ITestCase operateConstructor(int points, String make, String model, String color, String licenseNumber, int parkedMinutes) {
		try {
			return super.classTester.checkPartialArgsConstructorOperationViaGetter(points, className, constructorArgs(make, model, color, licenseNumber, parkedMinutes));
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(points, className, e);
		}
	}
}
