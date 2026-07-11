package student.testSuite.lab2.problem6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.model.ClassLoader;
import student.model.TestingField;
import student.model.TestCase;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;

public class LibraryManagementTester {
	private static LibraryManagementTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
    private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private static String className = ClassName.LIBRARY_MANAGEMENT;
	private static Class<?> clazz;

	/*
	 * instance ***************************************************************************
	 */

	public static LibraryManagementTester getInstance() {
		if (instance == null) {
			instance = new LibraryManagementTester();
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
	
	public static Object initObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return getCorrespondingClass().getDeclaredConstructor().newInstance();
	}

	/*
	 * existence ***************
	 */

	public TestCase checkExistence(int points) {
		return classTester.checkExistence(points, className);
	}

	/*
	 * fields ***************
	 */
	
	public TestCase checkFields(int points) {
		return fieldTester.checkDeclarations(points, className
				, new TestingField(List.class, FieldName.USERS)
				, new TestingField(List.class, FieldName.BOOKS)
				, new TestingField(List.class, FieldName.BORROWING_RECORDS)
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
     * addUser ***************
     */

	public TestCase addUserDeclaration(int points) throws ClassNotFoundException {
		return methodTester.declare(points, className, new TestingMethod(void.class, MethodName.ADD_USER,
				new TestingParameter(UserTester.getCorrespondingClass(), FieldName.USER)));
	}

    /*
     * addBook ***************
     */

	public TestCase addBookDeclaration(int points) throws ClassNotFoundException {
		return methodTester.declare(points, className, new TestingMethod(void.class, MethodName.ADD_BOOK,
				new TestingParameter(BookTester.getCorrespondingClass(), FieldName.BOOK)));
	}
    
    /*
     * addBorrowingRecord ***************
     */

	public TestCase addBorrowingRecordDeclaration(int points) throws ClassNotFoundException {
		return methodTester.declare(points, className, new TestingMethod(void.class, MethodName.ADD_BORROWING_RECORD,
				new TestingParameter(BorrowingRecordTester.getCorrespondingClass(), FieldName.BORROWING_RECORD)));
	}
    
    /*
     * getBorrowingBooks ***************
     */

	public TestCase getBorrowingBooksDeclaration(int points) throws ClassNotFoundException {
		return methodTester.declare(points, className, new TestingMethod(List.class, MethodName.GET_BORROWING_BOOKS,
				new TestingParameter(UserTester.getCorrespondingClass(), FieldName.USER)));
	}
    
    /*
     * isValidUser ***************
     */

	public TestCase isValidUserDeclaration(int points) throws ClassNotFoundException {
		return methodTester.declare(points, className, new TestingMethod(Modifier.PRIVATE, boolean.class, MethodName.IS_VALID_USER,
				new TestingParameter(UserTester.getCorrespondingClass(), FieldName.USER)));
	}
    
    /*
     * isUserEligibleToBorrow ***************
     */

	public TestCase isUserEligibleToBorrowDeclaration(int points) throws ClassNotFoundException {
		return methodTester.declare(points, className, new TestingMethod(Modifier.PRIVATE, boolean.class, MethodName.IS_USER_ELIGIBLE_TO_BORROW,
				new TestingParameter(UserTester.getCorrespondingClass(), FieldName.USER)));
	}
    
    /*
     * showAllUsers ***************
     */

	public TestCase showAllUsersDeclaration(int points) throws ClassNotFoundException {
		return methodTester.declare(points, className, new TestingMethod(void.class, MethodName.SHOW_ALL_USERS));
	}
    
    /*
     * showAllBooks ***************
     */

	public TestCase showAllBooksDeclaration(int points) throws ClassNotFoundException {
		return methodTester.declare(points, className, new TestingMethod(void.class, MethodName.SHOW_ALL_BOOKS));
	}
    
    /*
     * showAllBorrowingRecords ***************
     */

	public TestCase showAllBorrowingRecordsDeclaration(int points) throws ClassNotFoundException {
		return methodTester.declare(points, className, new TestingMethod(void.class, MethodName.SHOW_ALL_BORROWING_RECORDS));
	}
}
