package tester;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import common.constant.Feedback;
import common.constant.MethodName;
import common.constant.TestcaseType;
import common.message.ExceptionMessage;
import common.util.FieldUtils;
import common.util.MethodUtils;
import common.util.ParameterUtils;
import common.util.StringUtils;
import common.util.TestCaseUtils;
import helper.ClassLoader;
import helper.testCaseCreator.ClassTestcaseCreator;
import helper.testCaseCreator.FieldTestcaseCreator;
import helper.testCaseCreator.MethodTestcaseCreator;
import model.component.TestCase;
import model.element.TestingField;
import model.element.TestingMethod;
import model.element.TestingParameter;
import model.exception.TesterGotNoClassNameException;

public class Tester {
	protected String className;
	protected ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	protected FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	protected MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private Class<?> clazz;
	protected int defaultPoints = 1;
	protected Class<?> solutionClass;

//	private static Tester instance;
//	public static Tester getInstance() {
//		if (instance == null) {
//			instance = new Tester();
//		}
//
//		return instance;
//	}

	/*
	 * class ***************
	 */

	public Class<?> getCorrespondingClass() throws ClassNotFoundException, TesterGotNoClassNameException, ClassNotFoundException {
		if (StringUtils.isNullOrEmpty(className)) {
			throw new TesterGotNoClassNameException(
					ExceptionMessage.TESTER_GOT_NO_CLASS_NAME.getContent(this.getClass().getSimpleName()));
		}

		if (clazz == null) {
			clazz = Class.forName(className, true, ClassLoader.getInstance());
		}

		return clazz;
	}

	protected String getCorrespondingClassName() {
		return this.className;
	}

	public Class<?> retriveClass(String className) throws IllegalArgumentException, ClassNotFoundException {
		if (StringUtils.isNullOrEmpty(className)) {
			throw new IllegalArgumentException(ExceptionMessage.INVALID_CLASS_NAME.getContent(className));
		}

		return Class.forName(className, true, ClassLoader.getInstance());
	}

	/*
	 * field ***************
	 */

	protected Field getFieldAsAccessible(String fieldName)
			throws NoSuchFieldException, SecurityException, ClassNotFoundException, TesterGotNoClassNameException {
		return getFieldAsAccessible(getCorrespondingClass(), fieldName);
	}

	protected Field getFieldAsAccessible(Class<?> clazz, String fieldName)
			throws NoSuchFieldException, SecurityException {
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);

		return field;
	}

	/*
	 * build instance
	 */

	protected void buildInstance(Class<?> clazz, Object instance, TestingMethod method) throws Exception {
		this.methodTester.getMethodChecker().buildInstance(clazz, instance, method);
	}

	/*
	 * default testcase
	 */

	protected TestCase exceptionTestCase(Exception e) {
		return TestCaseUtils.errorTestcase(defaultPoints, className, e);
	}

	protected TestCase passByDefault() {
		return TestCaseUtils.passByDefault(defaultPoints, className);
	}

	protected TestCase pass(String type, String feedback) {
		return TestCaseUtils.pass(defaultPoints, className, type, feedback);
	}

	protected TestCase fail(String type, String feedback) {
		return TestCaseUtils.fail(defaultPoints, className, type, feedback);
	}

	protected TestCase passMethodOperation(String methodName) {
		return TestCaseUtils.pass(defaultPoints, className,
				TestcaseType.CHECK_METHOD_OPERATION.getName(className, methodName),
				Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(className, methodName));
	}

	protected TestCase failMethodOperation(String methodName) {
		return TestCaseUtils.fail(defaultPoints, className,
				TestcaseType.CHECK_METHOD_OPERATION.getName(className, methodName),
				Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(className, methodName));
	}

	/*
	 * solution
	 */

	protected TestingField[] getSolutionFields() {
		return FieldUtils.fromSolution(solutionClass);
	}

	protected TestingMethod getSolutionMethod(String methodName, Class<?>... parameterTypes)
			throws NoSuchMethodException, SecurityException {
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

	protected TestCase declare(int points) {
		return classTester.checkExistence(points, className);
	}

	protected TestCase declareAsInnerStaticClass(int points) {
		return classTester.declareAsInnerStaticClass(points, className);
	}

	protected TestCase declareAsInterface(int points) {
		return classTester.declareAsInterface(points, className);
	}

	protected TestCase declareAsEnum(int points) {
		return classTester.declareAsEnum(points, className);
	}

	protected TestCase declareAsAbstract(int points) {
		return classTester.declareAsAbstract(points, className);
	}

	protected TestCase declareAsStaticClass(int points) {
		return classTester.declareAsStaticClass(points, className);
	}

	/*
	 * super class
	 */

	protected TestCase declareSuperClass(int points, Class<?> superclass) {
		return classTester.declareSuperclass(points, className, superclass);
	}

	/*
	 * interface
	 */

	protected TestCase implementInterface(int points, Class<?> interfaze) {
		return classTester.implementInterface(points, className, interfaze);
	}

	/*
	 * constructor ***************
	 */

	protected TestCase checkConstructor(int points) throws ClassNotFoundException {
		return classTester.checkNoArgConstructorDeclaration(points, className);
	}

	protected TestCase checkConstructorDeclaration(int points, Class<?>... paramTypes) throws ClassNotFoundException {
		return classTester.checkPartialArgsConstructorDeclaration(points, className, paramTypes);
	}

	protected TestCase checkConstructorOperation(int points, TestingParameter... params)
			throws ClassNotFoundException {
		return classTester.checkPartialArgsConstructorOperationViaGetter(points, className, params);
	}

	protected TestCase operateConstructorViaSuper(int points, TestingParameter... params) {
		return classTester.operateConstructorViaSuper(points, className, params);
	}

	protected TestCase declareConstructorAsPrivate(int points, Class<?>... parammeterTypes) {
		return classTester.declareConstructorAsPrivate(points, className, parammeterTypes);
	}

//	protected void 

	/*
	 * field ***************
	 */

	protected TestCase checkFields(int points, TestingField... fieldTestings) {
		return fieldTester.checkDeclarations(points, className, fieldTestings);
	}

	public TestCase checkFieldsAsSpecialModifiers(int points, TestingField... fieldTestings) {
		return fieldTester.checkDeclarationsAsSpecialModifiers(points, className, fieldTestings);
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
	 * toString ***************
	 */

	public TestCase checkToStringDeclaration(int points) {
		return methodTester.declare(points, className, new TestingMethod(String.class, MethodName.TO_STRING));
	}

	public TestCase checkToStringOperation(int points, TestingParameter... args) throws ClassNotFoundException {
		TestingMethod method = MethodUtils.createMethodToString();

		return new TestCase() {
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
