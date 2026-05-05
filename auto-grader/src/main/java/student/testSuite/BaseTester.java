package student.testSuite;

import java.lang.reflect.InvocationTargetException;

import student.constant.ExceptionMessage;
import student.constant.Feedback;
import student.constant.MethodName;
import student.constant.TestcaseType;
import student.exception.TesterGotNoClassNameException;
import student.model.ClassLoader;
import student.model.FieldTesting;
import student.model.ITestCase;
import student.model.Method;
import student.model.MethodTesting;
import student.model.ParameterTesting;
import student.testcaseCreator.ClassTestcaseCreator;
import student.testcaseCreator.FieldTestcaseCreator;
import student.testcaseCreator.MethodTestcaseCreator;
import student.util.MethodUtils;
import student.util.ParameterTestingUtils;
import student.util.StringUtils;

public abstract class BaseTester {
	protected String className;
	protected BaseTester instance = null;
	private Class<?> clazz;
	private ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	private MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();

	/*
	 * instance ***************
	 */

	public abstract BaseTester getInstance() throws ClassNotFoundException, TesterGotNoClassNameException;

	/*
	 * class ***************
	 */

	public Class<?> getCorrespondingClass() throws ClassNotFoundException, TesterGotNoClassNameException {
		if (StringUtils.isNullOrEmpty(className)) {
			throw new TesterGotNoClassNameException(
					ExceptionMessage.TESTER_GOT_NO_CLASS_NAME.getContent(this.getClass().getSimpleName()));
		}

		if (clazz == null) {
			clazz = Class.forName(className, true, ClassLoader.getInstance());
		}

		return clazz;
	}

	/*
	 * instantiate ***************
	 */

	public Object instantiate()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, TesterGotNoClassNameException {
		return getCorrespondingClass().getDeclaredConstructor().newInstance();
	}

	public Object instantiateWithAgrs(ParameterTesting... args)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, TesterGotNoClassNameException {
		return getCorrespondingClass().getDeclaredConstructor(ParameterTestingUtils.mapToType(args))
				.newInstance(ParameterTestingUtils.mapToTestingValue(args));
	}

	/*
	 * existence ***************
	 */

	public ITestCase checkExistence(int points) {
		return classTester.checkExistence(points, className);
	}

	/*
	 * field ***************
	 */

	public ITestCase checkFields(int points, FieldTesting... fieldTestings) {
		return fieldTester.checkDeclarations(points, className, fieldTestings);
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
		return methodTester.checkExistence(points, className, new Method(String.class, MethodName.TO_STRING));
	}

	public ITestCase checkToStringOperation(int points, ParameterTesting... args) throws ClassNotFoundException {
		MethodTesting method = MethodUtils.createMethodToString();

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
					method.setInstance(instantiate());

					// Prepare test data
					String actual = method.invokeToString();

					// Get captured output then compare
					for (ParameterTesting arg : args) {
						if (!actual.contains(String.valueOf(arg.getTestingValue()))) {
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
