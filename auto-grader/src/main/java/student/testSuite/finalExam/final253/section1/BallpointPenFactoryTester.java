package student.testSuite.finalExam.final253.section1;

import student.constant.ClassName;
import student.constant.ExceptionMessage;
import student.exception.TesterGotNoClassNameException;
import student.model.ITestCase;
import student.solution.final253.section1.BallpointPenFactory;
import student.testSuite.BaseTester;

public class BallpointPenFactoryTester extends BaseTester {
	private PenFactoryTester penFactoryTester;

	public BallpointPenFactoryTester(PenFactoryTester penFactoryTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		if (penFactoryTester == null) {
			throw new IllegalArgumentException(ExceptionMessage.IS_NULL_IN_CLASS.getContent(PenFactoryTester.class.getName(), this.getClass().getName()));
		}
		super.className = ClassName.BALLPOINT_PEN_FACTORY;
		super.getCorrespondingClass();
		super.solutionClass = BallpointPenFactory.class;

		this.penFactoryTester = penFactoryTester;
	}

	/*
	 * declare
	 */

	public ITestCase declare() {
		return super.declare(defaultPoints);
	}
	
	public ITestCase implementInterface() {
		try {
			return super.implementInterface(defaultPoints, penFactoryTester.getCorrespondingClass());
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}

	/*
	 * createPen
	 */

	public ITestCase operateCreatePen(Object brand, String model, Object color, double price) {
		try {
			Class<?> subclass = getCorrespondingClass();
			return penFactoryTester.operateCreatePen(subclass, penFactoryTester.createPen(subclass, brand, model, color, price)
					.config(subclass, instantiate()));
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionTestCase(e);
		}
	}
}
