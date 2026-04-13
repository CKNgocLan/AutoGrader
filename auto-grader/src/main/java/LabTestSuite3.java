import java.lang.reflect.*;
import java.util.*;

/**
 * Test suite for the Employee class. Tests constructors, getters, and setters
 * as per the uploaded Employee.java
 */
public class LabTestSuite3 {

	public static List<ITestCase> getAllTests(String question) {
		switch (question) {
		case Questions.Q1:
			return Arrays.asList(
					createClassExistsTest(10)
					, createNoArgConstructorTest(15)
					, createFullConstructorTest(20)
				);
		case Questions.Q2:
			return null;
		case Questions.Q3:
			return null;
		case Questions.Q4:
			return null;
		case Questions.Q5:
			return null;
		default:
			return null;
		}
	}
	
	public static List<ITestCase> getAllTests() {
		List<ITestCase> tests = new ArrayList<>();

		// Test 1: Class exists (10 pts)
		tests.add(createClassExistsTest(10));

		// Test 2: No-argument constructor (15 pts)
		tests.add(createNoArgConstructorTest(15));

		// Test 3: Constructor with name, id, department, position (20 pts)
		tests.add(createFullConstructorTest(20));

		return tests;
	}

	// ===================================================================
	// Test 1: Class exists
	// ===================================================================
	private static ITestCase createClassExistsTest(int points) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_CLASS_EXISTENCE.getName(ClassName.EMPLOYEE);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class.forName(ClassName.EMPLOYEE);
					return true;
				} catch (ClassNotFoundException e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.CLASS_NOT_FOUND.getContent(ClassName.EMPLOYEE);
			}
		};
	}

	// ===================================================================
	// Test 2: No-argument constructor
	// ===================================================================
	private static ITestCase createNoArgConstructorTest(int points) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_CONSTRUCTOR_NO_ARGS.getName(ClassName.EMPLOYEE);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class.forName(ClassName.EMPLOYEE).getDeclaredConstructor().newInstance();
					return true;
				} catch (Exception e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.CONSTRUCTOR_MISSING_NO_ARGS.getContent(ClassName.EMPLOYEE);
			}
		};
	}

	// ===================================================================
	// Test 3: Full constructor (name, id, department, position)
	// ===================================================================
	private static ITestCase createFullConstructorTest(int points) {
		return new ITestCase() {
			@Override
			public String getName() {
				return TestcaseType.CHECK_CONSTRUCTOR_FULL_ARGS.getName(ClassName.EMPLOYEE);
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class.forName(ClassName.EMPLOYEE)
							.getDeclaredConstructor(String.class, int.class, String.class, String.class)
							.newInstance("Alice Smith", 12345, "IT", "Developer");
					return true;
				} catch (Exception e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return Feedback.CONSTRUCTOR_MISSING_FULL_ARGS.getContent(ClassName.EMPLOYEE);
			}
		};
	}
}