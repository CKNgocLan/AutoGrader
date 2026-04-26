package student.testSuite.classTestSuite.lab2;

import student.constant.ClassName;
import student.constant.Feedback;
import student.constant.FieldName;
import student.constant.MethodName;
import student.constant.TestcaseType;
import student.exception.InvalidConfigurationException;
import student.model.ITestCase;
import student.model.MethodTesting;
import student.model.Parameter;
import student.model.ParameterTesting;
import student.testSuite.classTestSuite.ClassTest;
import student.testSuite.methodTestSuite.MethodTest;
import student.util.ClassUtils;
import student.util.GetterUtils;
import student.util.MethodUtils;
import student.util.ParameterTestingUtils;

public class TemperatureTest {
	private static TemperatureTest instance = null;
	private ClassTest classTest = ClassTest.getInstance();
	private MethodTest methodTest = MethodTest.getInstance();
	private ClassLoader targetClassesLoader = student.model.ClassLoader.getInstance();
	private String className = ClassName.TEMPERATURE;

	/*
	 * instance ***************************************************************************
	 */

	public static TemperatureTest getInstance() {
		if (instance == null) {
			instance = new TemperatureTest();
		}

		return instance;
	}

	/*
	 * Existence ***************************************************************************
	 */

	public ITestCase checkExistence(int points) {
		return classTest.checkExistence(className, points);
	}

	/*
	 * Constructor ***************************************************************************
	 */

	public ITestCase checkFullArgsConstructorDeclaration(int points, ParameterTesting... params) {
		return classTest.checkFullArgsConstructorDeclaration(className, points, params);
	}

	public ITestCase checkFullArgsConstructorOperation(int points, ParameterTesting... params) {
		return classTest.checkFullArgsConstructorOperation(className, points, params);
	}

	/*
	 * setFahrenheit/ getFahrenheit ***************************************************************************
	 */
	public ITestCase checkSetgetFahrenheit(int points, double fahrenheit) {
		return new ITestCase() {
			String invalidMethodName;

			@Override
			public String getName() {
				return TestcaseType.CHECK_METHOD_OPERATION.getName(className, MethodName.SET_FAHRENHEIT + " & " + MethodName.GET_FAHRENHEIT);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);
					MethodTesting setFahrenheit = new MethodTesting(MethodName.SET_FAHRENHEIT, void.class, fahrenheit);

					setFahrenheit.setParameter(new Parameter(FieldName.FTEMP, double.class, fahrenheit));
					if (!MethodUtils.isMethodDeclared(clazz, setFahrenheit)) {
						invalidMethodName = setFahrenheit.getName();
						return false;
					}

					MethodTesting getFahrenheit = new MethodTesting(MethodName.GET_FAHRENHEIT, double.class, fahrenheit);
					if (!MethodUtils.isMethodDeclared(clazz, getFahrenheit)) {
						invalidMethodName = getFahrenheit.getName();
						return false;
					}
					
					getFahrenheit.setClazz(clazz);
					getFahrenheit.setInstance(clazz.getDeclaredConstructor(double.class).newInstance(fahrenheit));
					
					return getFahrenheit
							.boxingReturnedType()
							.cast(getFahrenheit.returning())
							.equals(fahrenheit)
					;
				} catch (NoSuchMethodException e) {
					return false;
				} catch (InvalidConfigurationException e) {
					return false;
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
					return false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(className, invalidMethodName);
			}
		};
	}
}
