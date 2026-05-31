package student.testSuite.labTestSuite;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import student.constant.BrandName;
import student.constant.ColorEnum;
import student.constant.ModelName;
import student.exception.TesterGotNoClassNameException;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.testSuite.finalExam.final253.BallpointPenTester;
import student.testSuite.finalExam.final253.BrandTester;
import student.testSuite.finalExam.final253.ColorTester;
import student.testSuite.finalExam.final253.FountainPenTester;
import student.testSuite.finalExam.final253.PenFactoryTester;
import student.testSuite.finalExam.final253.PenTester;

public class ExamFinalTestSuite253 extends ALabTestSuite {
	@Override
	public List<ITestCase> getAllTests(String question) {
		try {
			PenFactoryTester penFactoryTester = new PenFactoryTester();
			ColorTester colorTester = new ColorTester();
			BrandTester brandTester = new BrandTester();
			
			PenTester penTester = new PenTester();
			BallpointPenTester ballpointPenTester = new BallpointPenTester(penTester)
					.brandTester(brandTester)
					.colorTester(colorTester);
			FountainPenTester fountainPenTester = new FountainPenTester(penTester)
					.brandTester(brandTester)
					.colorTester(colorTester);

			Object brandInstance = brandTester.instantiate(BrandName.CONCOPENS);
			Object redEnum = colorTester.valueFrom(ColorEnum.RED);

			return Arrays.asList(
					// color
					colorTester.declare()
					, colorTester.declareFields()

					// brand
					, brandTester.declare()
					, brandTester.declareFields()

					// penFactory
					, penFactoryTester.declare()
					, penFactoryTester.declareCreatePen()

					// ballpointPen
					, ballpointPenTester.declare()
					, ballpointPenTester.declareSuper()
					, ballpointPenTester.declareConstructor()
					, ballpointPenTester.operateConstructor(brandInstance, ModelName.GOLDEN_LOTUS, redEnum, 61.5)
					, ballpointPenTester.declareGetDescription()
			);
		} catch (ClassNotFoundException | TesterGotNoClassNameException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
}
