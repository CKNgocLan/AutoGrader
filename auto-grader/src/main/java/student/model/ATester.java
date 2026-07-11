package student.model;

import java.lang.reflect.InvocationTargetException;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;

import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;

public abstract class ATester {
	private static ATester instance = null;
	private ClassTestcaseCreator classTest = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private MethodTestcaseCreator methodTest = MethodTestcaseCreator.getInstance();
	private URLClassLoader targetClassesLoader = student.model.ClassLoader.getInstance();
	private static String className;
	
	/*
	 * getInstance
	 */
	public static ATester getInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if (instance == null) {
			instance = TesterFactory.getInstance(clazz);
		}
		
		className = clazz.getName();
		
		return instance;
	}

	/**
	 * @Check: Field, Constructor(s), Getter/Setter
	 * @param points {existence, fields, getter, setter}
	 */
	public List<TestCase> checkEssentialDeclarations(int[] points) {
		return Arrays.asList(
				checkExistence(5)
				, checkFields(5)
				, checkGetterDeclaration(5)
				, checkSetterDeclaration(5)
		);
	}

	/*
	 * Existence ***************************************************************************
	 */
	public abstract TestCase checkExistence(int points);
	
	/*
	 * Fields ***************************************************************************
	 */
	public abstract TestCase checkFields(int points);

    /*
     * Getter ***************************************************************************
     */
    public abstract TestCase checkGetterDeclaration(int points);

    /*
     * Setter ***************************************************************************
     */
    public abstract TestCase checkSetterDeclaration(int points);

	/*
	 * Constructor ***************************************************************************
	 */
}
