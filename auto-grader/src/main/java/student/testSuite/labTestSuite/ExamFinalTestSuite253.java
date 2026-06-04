package student.testSuite.labTestSuite;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import student.constant.BrandName;
import student.constant.ColorEnum;
import student.constant.ModelName;
import student.constant.Question;
import student.exception.TesterGotNoClassNameException;
import student.model.ALabTestSuite;
import student.model.ITestCase;
import student.testSuite.finalExam.final253.section1.BallpointPenFactoryTester;
import student.testSuite.finalExam.final253.section1.BallpointPenTester;
import student.testSuite.finalExam.final253.section1.BrandTester;
import student.testSuite.finalExam.final253.section1.ColorTester;
import student.testSuite.finalExam.final253.section1.FountainPenFactoryTester;
import student.testSuite.finalExam.final253.section1.FountainPenTester;
import student.testSuite.finalExam.final253.section1.PenFactoryTester;
import student.testSuite.finalExam.final253.section1.PenTester;
import student.testSuite.finalExam.final253.section2.PenBuilderTester;

public class ExamFinalTestSuite253 extends ALabTestSuite {
	@Override
	public List<ITestCase> getAllTests(String question) {
		try {
			switch (question) {
				case Question.SECTION_1:
				{
					ColorTester colorTester = new ColorTester();
					BrandTester brandTester = new BrandTester();
					
					student.testSuite.finalExam.final253.section1.PenTester penTester = new student.testSuite.finalExam.final253.section1.PenTester()
							.brandTester(brandTester)
							.colorTester(colorTester);
					BallpointPenTester ballpointPenTester = new BallpointPenTester(penTester);
					FountainPenTester fountainPenTester = new FountainPenTester(penTester);

					Object brandInstance = brandTester.instantiate(BrandName.CONCOPENS);
					Object redEnum = colorTester.valueFrom(ColorEnum.RED);
					Object greyEnum = colorTester.valueFrom(ColorEnum.GREY);

					PenFactoryTester penFactoryTester = new PenFactoryTester(penTester.getCorrespondingClass()).brandTester(brandTester).colorTester(colorTester);
					BallpointPenFactoryTester ballpointPenFactoryTester = new BallpointPenFactoryTester(penFactoryTester);
					FountainPenFactoryTester fountainPenFactoryTester = new FountainPenFactoryTester(penFactoryTester);

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
							, ballpointPenTester.operateGetDescription(brandInstance, ModelName.GOLDEN_LOTUS, redEnum, 61.5)

							// fountainPen
							, fountainPenTester.declare()
							, fountainPenTester.declareSuper()
							, fountainPenTester.declareConstructor()
							, fountainPenTester.operateConstructor(brandInstance, ModelName.GOLDEN_LOTUS, greyEnum, 61.5)
							, fountainPenTester.declareGetDescription()
							, fountainPenTester.operateGetDescription(brandInstance, ModelName.GOLDEN_LOTUS, greyEnum, 61.5)

							// ballpointPenFactoryTester
							, ballpointPenFactoryTester.declare()
							, ballpointPenFactoryTester.implementInterface()
							, ballpointPenFactoryTester.operateCreatePen(brandInstance, ModelName.GOLDEN_LOTUS, redEnum, 61.5)

							// fountainPenFactoryTester
							, fountainPenFactoryTester.declare()
							, fountainPenFactoryTester.implementInterface()
							, fountainPenFactoryTester.operateCreatePen(brandInstance, ModelName.TUZU_FORGE, greyEnum, 65)
					);
				}
				case Question.SECTION_2:
				{
					PenBuilderTester builderTester = new PenBuilderTester();
					student.testSuite.finalExam.final253.section2.PenTester penTester = new student.testSuite.finalExam.final253.section2.PenTester(builderTester);
					return Arrays.asList(
							penTester.declare()
//							, penTester.declareFields()

							// builder
							, builderTester.declare()
//							, builderTester.declareFields()
					);
				}
				default:
					return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
