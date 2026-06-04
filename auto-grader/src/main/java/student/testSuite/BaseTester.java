package student.testSuite;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import student.constant.ExceptionMessage;
import student.constant.Feedback;
import student.constant.MethodName;
import student.constant.TestcaseType;
import student.exception.NoSolutionClassException;
import student.exception.TesterGotNoClassNameException;
import student.model.ClassLoader;
import student.model.TestingField;
import student.model.ITestCase;
import student.model.TestingMethod;
import student.model.TestingParameter;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;
import student.util.FieldUtils;
import student.util.MethodUtils;
import student.util.ParameterUtils;
import student.util.StringUtils;
import student.util.TestCaseUtils;

public abstract class BaseTester {
	protected String className;
	protected ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	protected FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	protected MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private Class<?> clazz;
	protected int defaultPoints = 1;
	protected Class<?> solutionClass;

	/*
	 * class ***************
	 */

	public Class<?> getCorrespondingClass() throws ClassNotFoundException, TesterGotNoClassNameException {
		try {
			if (StringUtils.isNullOrEmpty(className)) {
				throw new TesterGotNoClassNameException(
						ExceptionMessage.TESTER_GOT_NO_CLASS_NAME.getContent(this.getClass().getSimpleName()));
			}

			if (clazz == null) {
				clazz = Class.forName(className, true, ClassLoader.getInstance());
			}

			return clazz;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	protected String getCorrespondingClassName() {
		return this.className;
	}

	/*
	 * field ***************
	 */
	
	protected Field getFieldAsAccessible(String fieldName) throws NoSuchFieldException, SecurityException, ClassNotFoundException, TesterGotNoClassNameException {
		return getFieldAsAccessible(getCorrespondingClass(), fieldName);
	}
	
	protected Field getFieldAsAccessible(Class<?> clazz, String fieldName) throws NoSuchFieldException, SecurityException {
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		
		return field;
	}

	/*
	 * 
	 */
	protected ITestCase exceptionTestCase(Exception e) {
		return TestCaseUtils.errorTestcase(defaultPoints, className, e);
	}
	/*
	 * solution
	 */
	
	protected TestingField[] getSolutionFields() {
		return FieldUtils.fromSolution(solutionClass);
	}
	
	protected TestingMethod getSolutionMethod(String methodName, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
		return MethodUtils.fromSolution(solutionClass, methodName, parameterTypes);
	}
	
	protected TestingMethod getFirstSolutionMethod(String methodName) {
		return MethodUtils.fromSolution(clazz, methodName);
	}

	protected Class<?>[] uniqueConstructorParameterPrimitiveTypes() {
		return solutionClass.getDeclaredConstructors()[0].getParameterTypes();
	}

	/*
	 * instantiate ***************
	 */

	protected Object instantiate()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, TesterGotNoClassNameException {
		return getCorrespondingClass().getDeclaredConstructor().newInstance();
	}

	protected Object instantiateWithArgs(TestingParameter... args)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, TesterGotNoClassNameException {
		return getCorrespondingClass().getDeclaredConstructor(ParameterUtils.mapToConstructorType(args))
				.newInstance(ParameterUtils.mapToConstructorValue(args));
	}

	/*
	 * declare ***************
	 */

	public ITestCase declare(int points) {
		return classTester.checkExistence(points, className);
	}

	public ITestCase declareAsInnerClass(int points, String superClassName) {
		return classTester.declareAsInnerClass(points, className, superClassName);
	}
	
	public ITestCase declareAsInterface(int points) {
		return classTester.declareAsInterface(points, className);
	}
	
	public ITestCase declareAsEnum(int points) {
		return classTester.declareAsEnum(points, className);
	}
	
	protected ITestCase declareAsAbstract(int points) {
		return classTester.declareAsAbstract(points, className);
	}
	
	/*
	 * super class
	 */
	
	protected ITestCase declareSuperClass(int points, Class<?> superclass) {
		return classTester.declareSuperclass(points, className, superclass);
	}

	/*
	 * interface
	 */

	protected ITestCase implementInterface(int points, Class<?> interfaze) {
		return classTester.implementInterface(points, className, interfaze);
	}

	/*
	 * constructor ***************
	 */

	protected ITestCase checkConstructor(int points) throws ClassNotFoundException {
		return classTester.checkNoArgConstructorDeclaration(points, className);
	}
	
	protected ITestCase checkConstructorDeclaration(int points, Class<?>... paramTypes) throws ClassNotFoundException {
		return classTester.checkPartialArgsConstructorDeclaration(points, className, paramTypes);
	}

	protected ITestCase checkConstructorOperation(int points, TestingParameter... params) throws ClassNotFoundException {
		return classTester.checkPartialArgsConstructorOperationViaGetter(points, className, params);
	}
	
	protected ITestCase operateConstructorViaSuper(int points, TestingParameter... params) {
		return classTester.operateConstructorViaSuper(points, className, params);
	}

	protected ITestCase declareConstructorAsPrivate(int points, Class<?>... parammeterTypes) {
		return classTester.declareConstructorAsPrivate(points, className, parammeterTypes);
	}

	/*
	 * field ***************
	 */

	protected ITestCase checkFields(int points, TestingField... fieldTestings) {
		return fieldTester.checkDeclarations(points, className, fieldTestings);
	}
	
	public ITestCase checkFieldsAsSpecialModifiers(int points, TestingField... fieldTestings) {
		return fieldTester.checkDeclarationsAsSpecialModifiers(points, className, fieldTestings);
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
	 * toString ***************
	 */

	public ITestCase checkToStringDeclaration(int points) {
		return methodTester.checkExistence(points, className, new TestingMethod(String.class, MethodName.TO_STRING));
	}

	public ITestCase checkToStringOperation(int points, TestingParameter... args) throws ClassNotFoundException {
		TestingMethod method = MethodUtils.createMethodToString();

		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_METHOD_OPERATION.getName(className, method.getName());
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					method.setClazz(getCorrespondingClass());
					method.setInstance(instantiateWithArgs(args));

					// Prepare test data
					String actual = method.invokeToString();

					// Get captured output then compare
					for (TestingParameter arg : args) {
						if (!actual.contains(String.valueOf(arg.getValue()))) {
							return false;
						}
					}

					return true;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(className, method.getName());
			}
		};
	}
}
