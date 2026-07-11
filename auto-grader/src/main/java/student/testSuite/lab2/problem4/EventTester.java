package student.testSuite.lab2.problem4;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.model.ClassLoader;
import student.model.TestingField;
import student.model.TestCase;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;

public class EventTester {
	private static EventTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
    private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private static String className = ClassName.EVENT;
	private static Class<?> clazz;

	/*
	 * Instance ***************************************************************************
	 */

	public static EventTester getInstance() {
		if (instance == null) {
			instance = new EventTester();
		}

		return instance;
	}

	/*
	 * Class ***************
	 */
	
	public static Class<?> getCorrespondingClass() throws ClassNotFoundException {
		if (clazz == null) {
			clazz = Class.forName(className, true, ClassLoader.getInstance());
		}

		return clazz;
	}
	
	/*
	 * initialize object ***************
	 */
	
	public static Object initObject(int idx) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return getCorrespondingClass().getEnumConstants()[idx];
	}

	/*
	 * Existence ***************************************************************************
	 */

	public TestCase checkDeclaration(int points) {
		return classTester.checkExistenceAsEnum(points, className);
	}

	/*
	 * Fields ***************************************************************************
	 */
	
	public TestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarationsAsPublicStaticFinal(points, className
				, new TestingField(student.model.ClassLoader.retrieveClass(ClassName.EVENT), FieldName.UPPERCASE_WEDDING)
				, new TestingField(student.model.ClassLoader.retrieveClass(ClassName.EVENT), FieldName.UPPERCASE_BIRTHDAY)
		);
	}

    /*
     * Getter ***************************************************************************
     */
    
    public TestCase checkGetterDeclaration(int points) {
        return methodTester.checkGetterDeclaration(points, className);
    }

    /*
     * Setter ***************************************************************************
     */
    
    public TestCase checkSetterDeclaration(int points) {
        return methodTester.checkSetterDeclaration(points, className);
    }
}
