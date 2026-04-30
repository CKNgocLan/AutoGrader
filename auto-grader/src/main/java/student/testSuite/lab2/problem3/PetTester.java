package student.testSuite.lab2.problem3;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.model.ClassLoader;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.Method;
import student.model.ParameterTesting;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;

public class PetTester {
	private static PetTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private static String className = ClassName.PET;
	private static Class<?> clazz;

	/*
	 * instance ***************
	 */

	public static PetTester getInstance() {
		if (instance == null) {
			instance = new PetTester();
		}

		return instance;
	}

	/*
	 * Class ***************
	 */
	
	public Class<?> getClazz() throws ClassNotFoundException {
		if (clazz == null) {
			clazz = Class.forName(className, true, ClassLoader.getInstance());
		}

		return clazz;
	}
	
	/*
	 * initialize
	 */
	
	public static Object initializeInstance() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		return clazz.getDeclaredConstructor().newInstance();
	}

	/*
	 * Existence ***************
	 */

	public ITestCase checkExistence(int points) {
		return classTester.checkExistence(points, className);
	}

	/*
	 * Fields ***************
	 */

	public ITestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarations(points, className, new FieldTesting(String.class, FieldName.BREED),
				new FieldTesting(int.class, FieldName.AGE), new FieldTesting(double.class, FieldName.WEIGHT),
				new FieldTesting(ClassLoader.retrieveClass(ClassName.CUSTOMER), FieldName.CUSTOMER));
	}

	/*
	 * Constructor ***************
	 */

	public ITestCase checkPartialArgsConstructors(int points, ParameterTesting... params)
			throws ClassNotFoundException {
		return classTester.checkPartialArgsConstructorDeclaration(points, className, params);
	}

	/*
	 * toString ***************
	 */

	public ITestCase checkToStringExistence(int points) {
		return methodTester.checkExistence(points, className, new Method(String.class, MethodName.TO_STRING));
	}
}
