package student.model;

import student.constant.Lab;
import student.testSuite.lab.LabTestSuite1;
import student.testSuite.lab.LabTestSuite2;
import student.testSuite.lab.LabTestSuite3;
import student.testSuite.lab.LabTestSuite4;
import student.testSuite.lab.LabTestSuite5;
import student.testSuite.lab.LabTestSuite6;
import student.testSuite.lab.LabTestSuite7;

public class LabTestSuiteFactory {
	public ALabTestSuite createSuite(String lab) {

		switch (lab) {
		case Lab.L1:
			return new LabTestSuite1();
		case Lab.L2:
			return new LabTestSuite2();
//		case Lab.L3:
//			return new LabTestSuite3();
//		case Lab.L4:
//			return new LabTestSuite4();
//		case Lab.L5:
//			return new LabTestSuite5();
//		case Lab.L6:
//			return new LabTestSuite6();
//		case Lab.L7:
//			return new LabTestSuite7();
		}
		
		return null;
	}
}
