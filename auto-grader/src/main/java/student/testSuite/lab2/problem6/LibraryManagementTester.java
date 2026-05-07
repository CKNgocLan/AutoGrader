package student.testSuite.lab2.problem6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.MethodName;
import student.model.ClassLoader;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.MethodTesting;
import student.model.ParameterTesting;
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

	public ITestCase checkExistence(int points) {
		return classTester.checkExistence(points, className);
	}

	/*
	 * fields ***************
	 */
	
	public ITestCase checkFields(int points) {
		return fieldTester.checkDeclarations(points, className
				, new FieldTesting(List.class, FieldName.USERS)
				, new FieldTesting(List.class, FieldName.BOOKS)
				, new FieldTesting(List.class, FieldName.BORROWING_RECORDS)
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
     * addUser ***************
     */

	public ITestCase addUserDeclaration(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className, new MethodTesting(void.class, MethodName.ADD_USER,
				new ParameterTesting(UserTester.getCorrespondingClass(), FieldName.USER)));
	}

    /*
     * addBook ***************
     */

	public ITestCase addBookDeclaration(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className, new MethodTesting(void.class, MethodName.ADD_BOOK,
				new ParameterTesting(BookTester.getCorrespondingClass(), FieldName.BOOK)));
	}
    
    /*
     * addBorrowingRecord ***************
     */

	public ITestCase addBorrowingRecordDeclaration(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className, new MethodTesting(void.class, MethodName.ADD_BORROWING_RECORD,
				new ParameterTesting(BorrowingRecordTester.getCorrespondingClass(), FieldName.BORROWING_RECORD)));
	}
    
    /*
     * getBorrowingBooks ***************
     */

	public ITestCase getBorrowingBooksDeclaration(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className, new MethodTesting(List.class, MethodName.GET_BORROWING_BOOKS,
				new ParameterTesting(UserTester.getCorrespondingClass(), FieldName.USER)));
	}
    
    /*
     * isValidUser ***************
     */

	public ITestCase isValidUserDeclaration(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className, new MethodTesting(Modifier.PRIVATE, boolean.class, MethodName.IS_VALID_USER,
				new ParameterTesting(UserTester.getCorrespondingClass(), FieldName.USER)));
	}
    
    /*
     * isUserEligibleToBorrow ***************
     */

	public ITestCase isUserEligibleToBorrowDeclaration(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className, new MethodTesting(Modifier.PRIVATE, boolean.class, MethodName.IS_USER_ELIGIBLE_TO_BORROW,
				new ParameterTesting(UserTester.getCorrespondingClass(), FieldName.USER)));
	}
    
    /*
     * showAllUsers ***************
     */

	public ITestCase showAllUsersDeclaration(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className, new MethodTesting(void.class, MethodName.SHOW_ALL_USERS));
	}
    
    /*
     * showAllBooks ***************
     */

	public ITestCase showAllBooksDeclaration(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className, new MethodTesting(void.class, MethodName.SHOW_ALL_BOOKS));
	}
    
    /*
     * showAllBorrowingRecords ***************
     */

	public ITestCase showAllBorrowingRecordsDeclaration(int points) throws ClassNotFoundException {
		return methodTester.checkExistence(points, className, new MethodTesting(void.class, MethodName.SHOW_ALL_BORROWING_RECORDS));
	}
}
