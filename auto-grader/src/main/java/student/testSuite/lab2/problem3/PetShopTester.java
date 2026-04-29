package student.testSuite.lab2.problem3;

import java.util.List;

import student.constant.ClassName;
import student.constant.FieldName;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;

public class PetShopTester {
	private static PetShopTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
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
		return classTester.checkExistence(points, className);
	}


	/*
	 * Field ***************************************************************************
	 */
	
	public ITestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarations(points, className
				, new FieldTesting(List.class, student.model.ClassLoader.retrieveClass(ClassName.CUSTOMER), FieldName.CUSTOMERS)
				, new FieldTesting(List.class, student.model.ClassLoader.retrieveClass(ClassName.PET), FieldName.PETS)
				, new FieldTesting(List.class, student.model.ClassLoader.retrieveClass(ClassName.SERVICE_ESTIMATE), FieldName.SERVICE_ESTIMATES)
		);
	}

	/*
	 * Constructor ***************************************************************************
	 */
	
	public ITestCase checkNoArgsConstructors(int points) throws ClassNotFoundException {
		return classTester.checkNoArgConstructorDeclaration(points, className);
	}
}
