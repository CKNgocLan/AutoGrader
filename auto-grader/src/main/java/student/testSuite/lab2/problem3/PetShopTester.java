package student.testSuite.lab2.problem3;

import student.constant.ClassName;
import student.model.ITestCase;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;

public class PetShopTester {
	private static PetShopTester instance = null;
	private ClassTestcaseCreator classTest = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private ClassLoader targetClassesLoader = student.model.ClassLoader.getInstance();
	private String className = ClassName.PET_SHOP;

	/*
	 * instance ***************************************************************************
	 */

	public static PetShopTester getInstance() {
		if (instance == null) {
			instance = new PetShopTester();
		}

		return instance;
	}

	/*
	 * Existence ***************************************************************************
	 */

	public ITestCase checkExistence(int points) {
		return classTest.checkExistence(className, points);
	}

}
