package student.testSuite.finalExam.final253;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.IEnumTester;
import student.model.ITestCase;
import student.solution.final253.section1.Color;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class ColorTester extends BaseTester implements IEnumTester {
	@SuppressWarnings("unchecked")
	@Override
	public Object valueFrom(String name) throws ClassNotFoundException, TesterGotNoClassNameException {
		return Enum.valueOf((Class<? extends Enum>)getCorrespondingClass(), name);
	}

	/*
	 * instantiate ***************
	 */

	public ColorTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.COLOR;
		super.getCorrespondingClass();
		super.solutionClass = Color.class;
	}

	public Object instantiate() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, TesterGotNoClassNameException {
		return super.instantiate();
	}
	
	/*
	 * declaration
	 */
	public ITestCase declare() {
		return super.declareAsEnum(defaultPoints);
	}
	
	/*
	 * field ***************
	 */
	
	public ITestCase declareFields() {
		try {
			return super.fieldTester.declareInEnum(defaultPoints, className, super.getSolutionFields());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}
}
