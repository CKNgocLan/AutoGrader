package student.model;

import student.constant.Lab;
import student.testSuite.labTestSuite.LabTestSuite1;
import student.testSuite.labTestSuite.LabTestSuite2;
import student.testSuite.labTestSuite.LabTestSuite3;
import student.testSuite.labTestSuite.LabTestSuite4;
import student.testSuite.labTestSuite.LabTestSuite5;
import student.testSuite.labTestSuite.LabTestSuite6;
import student.testSuite.labTestSuite.LabTestSuite7;

public class LabTestSuiteFactory {
	public ALabTestSuite createSuite(String lab) {

		switch (lab) {
		case Lab.L1:
			return new LabTestSuite1();
		case Lab.L2:
			return new LabTestSuite2();
		case Lab.L3:
			return new LabTestSuite3();
		case Lab.L4:
			return new LabTestSuite4();
		case Lab.L5:
			return new LabTestSuite5();
		case Lab.L6:
			return new LabTestSuite6();
		case Lab.L7:
			return new LabTestSuite7();
		}
		
		return null;
	}
}
