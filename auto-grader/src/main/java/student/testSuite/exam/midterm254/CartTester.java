package student.testSuite.exam.midterm254;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.IEnumTester;
import student.model.TestCase;
import student.solution.midterm254.Cart;
import student.testSuite.BaseTester;
import student.util.TestCaseUtils;

public class CartTester extends BaseTester implements IEnumTester {
	@SuppressWarnings("unchecked")
	@Override
	public Object valueFrom(String name) throws ClassNotFoundException, TesterGotNoClassNameException {
		return Enum.valueOf((Class<? extends Enum>) getCorrespondingClass(), name);
	}

	/**
	 * instantiate
	 */

	public CartTester() throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.CART;
		super.getCorrespondingClass();
		super.solutionClass = Cart.class;
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
