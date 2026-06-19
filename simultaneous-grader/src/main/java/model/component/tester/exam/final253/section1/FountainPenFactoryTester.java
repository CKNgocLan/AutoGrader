package model.component.tester.exam.final253.section1;

import common.constant.ClassName;
import model.component.TestCase;
import model.component.tester.Tester;
import model.component.tester.exam.final253.section1.solution.FountainPenFactory;
import model.exception.TesterGotNoClassNameException;

public class FountainPenFactoryTester extends Tester {
	private PenFactoryTester penFactoryTester;

	public FountainPenFactoryTester(PenFactoryTester penFactoryTester) throws ClassNotFoundException, TesterGotNoClassNameException {
		super.className = ClassName.FOUNTAIN_PEN_FACTORY;
		super.getCorrespondingClass();
		super.solutionClass = FountainPenFactory.class;
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
