package student.testSuite.lab3.problem4;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.exception.TesterGotNoClassNameException;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.testSuite.BaseTester;

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
}
