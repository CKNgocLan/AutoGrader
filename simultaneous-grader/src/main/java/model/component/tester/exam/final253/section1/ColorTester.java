package model.component.tester.exam.final253.section1;

import java.lang.reflect.InvocationTargetException;

import common.constant.ClassName;
import common.util.TestCaseUtils;
import model.component.TestCase;
import model.component.tester.EnumTester;
import model.component.tester.Tester;
import model.component.tester.exam.final253.section1.solution.Color;
import model.exception.TesterGotNoClassNameException;

public class ColorTester extends Tester implements EnumTester {
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
	public TestCase declare() {
		return super.declareAsEnum(defaultPoints);
	}
	
	/*
	 * field ***************
	 */
	
	public TestCase declareFields() {
		try {
			return super.fieldTester.declareInEnum(defaultPoints, className, super.getSolutionFields());
		} catch (Exception e) {
			e.printStackTrace();
			return TestCaseUtils.errorTestcase(defaultPoints, className, e);
		}
	}
}
