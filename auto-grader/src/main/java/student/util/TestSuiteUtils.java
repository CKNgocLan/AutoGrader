package student.util;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import student.constant.Labs;
import student.model.ITestCase;
import student.testSuite.LabTestSuite1;
import student.testSuite.LabTestSuite2;
import student.testSuite.LabTestSuite3;
import student.testSuite.LabTestSuite4;
import student.testSuite.LabTestSuite5;
import student.testSuite.LabTestSuite6;
import student.testSuite.LabTestSuite7;

public class TestSuiteUtils {

	public static List<ITestCase> invokeAllTests(String lab, String question)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		switch (lab) {
		case Labs.L1:
			return LabTestSuite1.getAllTests(question);
		case Labs.L2:
			return LabTestSuite2.getAllTests(question);
		case Labs.L3:
			return LabTestSuite3.getAllTests(question);
		case Labs.L4:
			return LabTestSuite4.getAllTests(question);
		case Labs.L5:
			return LabTestSuite5.getAllTests(question);
		case Labs.L6:
			return LabTestSuite6.getAllTests(question);
		case Labs.L7:
			return LabTestSuite7.getAllTests(question);
		default:
			return List.of();
		}
	}
}
