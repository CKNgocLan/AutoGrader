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
	protected ClassTestcaseCreator classTester = ClassTestcaseCreator.getInstance();
	protected FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
	protected MethodTestcaseCreator methodTester = MethodTestcaseCreator.getInstance();
	private Class<?> clazz;

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

	public Object instantiateWithArgs(ParameterTesting... args)
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
	 * constructor ***************
	 */

	protected ITestCase checkConstructor(int points) throws ClassNotFoundException {
		return classTester.checkNoArgConstructorDeclaration(points, className);
	}
	
	protected ITestCase checkConstructorDeclaration(int points, Class<?>... paramTypes) throws ClassNotFoundException {
		return classTester.checkPartialArgsConstructorDeclaration(points, className, paramTypes);
	}

	protected ITestCase checkConstructorOperation(int points, ParameterTesting... params) throws ClassNotFoundException {
		return classTester.checkPartialArgsConstructorOperation(points, className, params);
	}

	/*
	 * field ***************
	 */

	protected ITestCase checkFields(int points, FieldTesting... fieldTestings) {
		return fieldTester.checkDeclarations(points, className, fieldTestings);
	}
	
	public ITestCase checkFieldsAsSpecialModifiers(int points, FieldTesting... fieldTestings) {
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
		return methodTester.checkExistence(points, className, new MethodTesting(String.class, MethodName.TO_STRING));
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
					method.setInstance(instantiateWithArgs(args));

					// Prepare test data
					String actual = method.invokeToString();

					// Get captured output then compare
					for (ParameterTesting arg : args) {
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
