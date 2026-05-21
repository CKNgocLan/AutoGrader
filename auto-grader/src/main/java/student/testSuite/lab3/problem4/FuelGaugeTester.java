package student.testSuite.lab3.problem4;

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

public class FuelGaugeTester extends BaseTester {

	public FuelGaugeTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.FUEL_GAUGE;
		super.getCorrespondingClass();
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
	
	public ITestCase fields(int points) {
		return fieldTester.checkDeclarations(points, className
				, new FieldTesting(int.class, FieldName.GALLON)
		);
	}

	/*
	 * constructor
	 */
	
	public ITestCase declareConstructor(int points) {
		return super.classTester.checkPartialArgsConstructorDeclaration(points, className, int.class);
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
