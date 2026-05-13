package student.model;

import student.constant.Lab;
import student.constant.Midterm;
import student.testSuite.labTestSuite.LabTestSuite1;
import student.testSuite.labTestSuite.LabTestSuite2;
import student.testSuite.labTestSuite.LabTestSuite3;
import student.testSuite.midterm.MidtermTestSuite253;

public class LabTestSuiteFactory {
	public ALabTestSuite createSuite(String lab) {

		switch (lab) {
		case Midterm.MIDTERM_253:
			return new MidtermTestSuite253();
		case Lab.L1:
			return new LabTestSuite1();
		case Lab.L2:
			return new LabTestSuite2();
		case Lab.L3:
			return new LabTestSuite3();
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
