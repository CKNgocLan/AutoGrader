package student.testSuite.finalExam.final253;

import student.constant.ClassName;
import student.exception.TesterGotNoClassNameException;
import student.model.ITestCase;
import student.solution.final253.section1.FountainPenFactory;
import student.testSuite.BaseTester;

public class FountainPenFactoryTester extends BaseTester {
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
