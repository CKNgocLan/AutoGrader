package model.component.tester.exam.final253.section1;

import common.constant.ClassName;
import common.message.ExceptionMessage;
import model.component.TestCase;
import model.component.tester.Tester;
import model.component.tester.exam.final253.section1.solution.BallpointPenFactory;
import model.exception.TesterGotNoClassNameException;

public class BallpointPenFactoryTester extends Tester {
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

	public TestCase declare() {
		return super.declare(defaultPoints);
	}
	
	public TestCase implementInterface() {
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

	public TestCase operateCreatePen(Object brand, String model, Object color, double price) {
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
