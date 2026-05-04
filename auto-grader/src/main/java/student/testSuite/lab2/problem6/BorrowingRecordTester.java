package student.testSuite.lab2.problem6;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

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

public class BorrowingRecordTester {
	private static BorrowingRecordTester instance = null;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
    private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private static String className = ClassName.BORROWING_RECORD;
	private static Class<?> clazz;

	/*
	 * instance ***************************************************************************
	 */

	public static BorrowingRecordTester getInstance() {
		if (instance == null) {
			instance = new BorrowingRecordTester();
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
	
	public static Object initObject(Object user, Object book, LocalDate dueDate) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return getCorrespondingClass()
				.getDeclaredConstructor(UserTester.getCorrespondingClass(), BookTester.getCorrespondingClass(), LocalDate.class)
				.newInstance(user, book, dueDate);
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

	public ITestCase checkPartialArgsConstructorDeclaration(int points, ParameterTesting... params) {
		return classTester.checkPartialArgsConstructorDeclaration(points, className, params);
	}

	/*
	 * fields ***************
	 */
	
	public ITestCase checkFields(int points) throws ClassNotFoundException {
		return fieldTester.checkDeclarations(points, className
				, new FieldTesting(UserTester.getCorrespondingClass(), FieldName.USER)
				, new FieldTesting(BookTester.getCorrespondingClass(), FieldName.BOOK)
				, new FieldTesting(LocalDate.class, FieldName.BORROWING_DATE)
				, new FieldTesting(LocalDate.class, FieldName.DUE_DATE)
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
		return methodTester.checkExistence(points, className, MethodUtils.createMethodEquals(FieldName.BORROWING_RECORD,
				BorrowingRecordTester.getCorrespondingClass()));
	}
}
