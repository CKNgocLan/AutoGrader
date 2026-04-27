package student.testSuite.classTestSuite.lab2;

import student.constant.ClassName;
import student.constant.Feedback;
import student.constant.FieldName;
import student.constant.MethodName;
import student.constant.TestcaseType;
import student.creator.ClassTestcaseCreator;
import student.creator.FieldTestcaseCreator;
import student.creator.MethodTestcaseCreator;
import student.exception.InvalidConfigurationException;
import student.model.ITestCase;
import student.model.MethodTesting;
import student.model.Parameter;
import student.model.ParameterTesting;
import student.util.MethodUtils;

public class TemperatureTest {
	private static TemperatureTest instance = null;
	private ClassTestcaseCreator classTest = ClassTestcaseCreator.getInstance();
	private FieldTestcaseCreator fieldTester = FieldTestcaseCreator.getInstance();
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
	 * Field: ftemp ***************************************************************************
	 */
	
	public ITestCase checkField(int points, String fieldName, Class<?> type) {
		return fieldTester.checkDeclaration(className, points, fieldName, type);
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
				return TestcaseType.CHECK_METHOD_OPERATION.getName(className,
						MethodName.SET_FAHRENHEIT + " & " + MethodName.GET_FAHRENHEIT);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);

					MethodTesting setFahrenheit = createSetFahrenheit(fahrenheit);
					if (!MethodUtils.isMethodDeclared(clazz, setFahrenheit)) {
						invalidMethodName = setFahrenheit.getName();
						return false;
					}

					MethodTesting getFahrenheit = new MethodTesting(MethodName.GET_FAHRENHEIT, double.class,
							fahrenheit);
					if (!MethodUtils.isMethodDeclared(clazz, getFahrenheit)) {
						invalidMethodName = getFahrenheit.getName();
						return false;
					}

					getFahrenheit.setClazz(clazz);
					getFahrenheit.setInstance(clazz.getDeclaredConstructor(double.class).newInstance(fahrenheit));

					return getFahrenheit.boxingReturnedType().cast(getFahrenheit.returning()).equals(fahrenheit);
				} catch (InvalidConfigurationException e) {
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

	/*
	 * getCelsius ***************************************************************************
	 */
	public ITestCase checkGetCelsius(int points, double fahrenheit, double celsius) {
		return new ITestCase() {
			MethodTesting testedMethod = new MethodTesting(MethodName.GET_CELSIUS, double.class);

			@Override
			public String getName() {
				return TestcaseType.CHECK_METHOD_OPERATION.getName(className, testedMethod.getName());
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);

					if (!MethodUtils.isMethodDeclared(clazz, testedMethod)) {
						return false;
					}

					testedMethod.setClazz(clazz);
					testedMethod.setInstance(clazz.getDeclaredConstructor(double.class).newInstance(fahrenheit));

					return testedMethod.boxingReturnedType().cast(testedMethod.returning()).equals(celsius);
				} catch (InvalidConfigurationException e) {
					return false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(className, testedMethod.getName());
			}
		};
	}

	/*
	 * getKelvin ***************************************************************************
	 */
	public ITestCase checkGetKelvin(int points, double fahrenheit, double kelvin) {
		return new ITestCase() {
			MethodTesting testedMethod = new MethodTesting(MethodName.GET_KELVIN, double.class);

			@Override
			public String getName() {
				return TestcaseType.CHECK_METHOD_OPERATION.getName(className, testedMethod.getName());
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class<?> clazz = Class.forName(className, true, targetClassesLoader);

					if (!MethodUtils.isMethodDeclared(clazz, testedMethod)) {
						return false;
					}

					testedMethod.setClazz(clazz);
					testedMethod.setInstance(clazz.getDeclaredConstructor(double.class).newInstance(fahrenheit));

					return testedMethod.boxingReturnedType().cast(testedMethod.returning()).equals(kelvin);
				} catch (InvalidConfigurationException e) {
					return false;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.METHOD_OPERATED_NOT_CORRECT.getContent(className, testedMethod.getName());
			}
		};
	}

	private MethodTesting createSetFahrenheit(double fahrenheit) {
		return new MethodTesting(MethodName.SET_FAHRENHEIT, void.class, fahrenheit,
				new Parameter(FieldName.FTEMP, double.class, fahrenheit));
	}
}
