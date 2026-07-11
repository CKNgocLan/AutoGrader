package student.testSuite.lab2.problem6;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.model.ClassLoader;
import student.model.TestingField;
import student.model.TestCase;
import student.model.TestingParameter;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;
import student.util.MethodUtils;

public class BookTester {
	private static BookTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
    private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private static String className = ClassName.BOOK;
	private static Class<?> clazz;

	/*
	 * Instance ***************************************************************************
	 */

	public static BookTester getInstance() {
		if (instance == null) {
			instance = new BookTester();
		}

		return instance;
	}

	/*
	 * class ***************
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
	
	public static Object initObject(String isbn, String title) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return getCorrespondingClass().getDeclaredConstructor(String.class, String.class).newInstance(isbn, title);
	}

	/*
	 * existence ***************
	 */

	public TestCase checkExistence(int points) {
		return classTester.checkExistence(points, className);
	}

	/*
	 * constructor ***************
	 */

	public TestCase checkFullArgsConstructorDeclaration(int points, TestingParameter... params) {
		return classTester.checkFullArgsConstructorDeclaration(points, className, params);
	}

	/*
	 * fields ***************
	 */
	
	public TestCase checkFields(int points) {
		return fieldTester.checkDeclarations(points, className
				, new TestingField(String.class, FieldName.ISBN)
				, new TestingField(String.class, FieldName.TITLE)
		);
	}

    /*
     * getter ***************
     */
	
    public TestCase checkGetterDeclaration(int points) {
        return methodTester.checkGetterDeclaration(points, className);
    }

    /*
     * setter ***************
     */
    
    public TestCase checkSetterDeclaration(int points) {
        return methodTester.checkSetterDeclaration(points, className);
    }

    /*
     * equals ***************
     */

	public TestCase checkEqualsDeclaration(int points) throws ClassNotFoundException {
		return methodTester.declare(points, className,
				MethodUtils.createMethodEquals(FieldName.BOOK, BookTester.getCorrespondingClass()));
	}
}
