package student.testSuite.exam.final254;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.IEnumTester;
import student.model.TestCase;
import student.solution.midterm254.TeaCategory;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class TeaCategoryTester extends BaseTester implements IEnumTester {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object valueFrom(String name) throws ClassNotFoundException, TesterGotNoClassNameException {
		return Enum.valueOf((Class<? extends Enum>) getCorrespondingClass(), name);
	}

	public static enum EnumValue {
		GREEN_TEA,
		SCENTED_TEA
	}

	@SuppressWarnings("unchecked")
	public Class<? extends Enum<?>> getEnumClass() throws ClassNotFoundException, TesterGotNoClassNameException {
		return (Class<? extends Enum<?>>)getCorrespondingClass();
	}

	/**
	 * instantiate
	 */

	public TeaCategoryTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.TEA_CATEGORY;
		super.getCorrespondingClass();
		super.solutionClass = TeaCategory.class;
	}

	public Object instantiate() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, TesterGotNoClassNameException {
		return super.instantiate();
	}
	
	/**
	 * declaration
	 */
	public TestCase declare() {
		return super.declareAsEnum(defaultPoints);
	}
	
	/**
	 * field
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
