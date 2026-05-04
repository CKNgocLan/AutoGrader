package student.testSuite.lab2.problem6;

import java.lang.reflect.InvocationTargetException;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.model.ClassLoader;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.MethodTesting;
import student.model.Parameter;
import student.model.ParameterTesting;
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

	public ITestCase checkExistence(int points) {
		return classTester.checkExistence(points, className);
	}

	/*
	 * constructor ***************
	 */

	public ITestCase checkFullArgsConstructorDeclaration(int points, ParameterTesting... params) {
		return classTester.checkFullArgsConstructorDeclaration(points, className, params);
	}

	/*
	 * fields ***************
	 */
	
	public ITestCase checkFields(int points) {
		return fieldTester.checkDeclarations(points, className
				, new FieldTesting(String.class, FieldName.ISBN)
				, new FieldTesting(String.class, FieldName.TITLE)
		);
	}

    /*
     * getter ***************
     */
	
    public ITestCase checkGetterDeclaration(int points) {
        return methodTester.checkGetterDeclaration(points, className);
    }

    /*
     * setter ***************
     */
    
    public ITestCase checkSetterDeclaration(int points) {
        return methodTester.checkSetterDeclaration(points, className);
    }

    /*
     * equals ***************
     */

	public ITestCase checkEqualsDeclaration(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className,
				MethodUtils.createMethodEquals(FieldName.BOOK, BookTester.getCorrespondingClass()));
	}
}
