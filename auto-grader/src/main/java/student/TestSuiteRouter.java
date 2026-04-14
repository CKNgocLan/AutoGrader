package student;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import student.constant.Lab;
import student.model.ITestCase;
import student.testSuite.lab.LabTestSuite1;
import student.testSuite.lab.LabTestSuite2;
import student.testSuite.lab.LabTestSuite3;
import student.testSuite.lab.LabTestSuite4;
import student.testSuite.lab.LabTestSuite5;
import student.testSuite.lab.LabTestSuite6;
import student.testSuite.lab.LabTestSuite7;

public class TestSuiteRouter {

	public static List<ITestCase> invokeAllTests(String lab, String question)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		switch (lab) {
		case Lab.L1:
			return LabTestSuite1.getAllTests(question);
		case Lab.L2:
			return LabTestSuite2.getAllTests(question);
		case Lab.L3:
			return LabTestSuite3.getAllTests(question);
		case Lab.L4:
			return LabTestSuite4.getAllTests(question);
		case Lab.L5:
			return LabTestSuite5.getAllTests(question);
		case Lab.L6:
			return LabTestSuite6.getAllTests(question);
		case Lab.L7:
			return LabTestSuite7.getAllTests(question);
		default:
			return List.of();
		}
	}
}
