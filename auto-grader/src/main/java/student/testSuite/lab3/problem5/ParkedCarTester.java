package student.testSuite.lab3.problem5;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.TestingField;
import student.model.ITestCase;
import student.model.TestingParameter;
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
	
	public TestingParameter[] constructorArgs(String make, String model, String color, String licenseNumber, int parkedMinutes) throws ClassNotFoundException, TesterGotNoClassNameException {
		return new TestingParameter[] {
			new TestingParameter(String.class, FieldName.MAKE, make)
			, new TestingParameter(String.class, FieldName.MODEL, model)
			, new TestingParameter(String.class, FieldName.COLOR, color)
			, new TestingParameter(String.class, FieldName.LICENSE_NUMBER, licenseNumber)
			, new TestingParameter(int.class, FieldName.PARKED_MINUTES, parkedMinutes)
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
				, new TestingField(String.class, FieldName.MAKE)
				, new TestingField(String.class, FieldName.MODEL)
				, new TestingField(String.class, FieldName.COLOR)
				, new TestingField(String.class, FieldName.LICENSE_NUMBER)
				, new TestingField(int.class, FieldName.PARKED_MINUTES)
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
