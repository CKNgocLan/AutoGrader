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

public class PoliceOfficerTester extends BaseTester {

	public PoliceOfficerTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.POLICE_OFFICER;
		super.getCorrespondingClass();
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
	
}
