package student.testSuite.lab1;

import java.lang.reflect.*;
import java.util.*;

import student.model.ITestCase;

public class EmployeeTests {

	public static List<ITestCase> getAllTests() {
		List<ITestCase> tests = new ArrayList<>();

		// Test 1: Class exists and can be loaded (10 pts)
		tests.add(createTest("Test 1: Employee class exists", 10, () -> {
			try {
				Class.forName("Employee");
				return true;
			} catch (ClassNotFoundException e) {
				return false;
			}
		}, "Employee class must be declared and compiled successfully."));

		// Test 2: Default constructor exists (15 pts)
		tests.add(createTest("Test 2: Default constructor exists", 15, () -> {
			try {
				Class<?> clazz = Class.forName("Employee");
				Constructor<?> defaultCtor = clazz.getDeclaredConstructor();
				return defaultCtor != null;
			} catch (Exception e) {
				return false;
			}
		}, "Employee must have a public no-argument (default) constructor."));

		// Test 3: Parameterized constructor exists (20 pts)
		tests.add(createTest("Test 3: Parameterized constructor exists", 20, () -> {
			try {
				Class<?> clazz = Class.forName("Employee");
				Constructor<?> paramCtor = clazz.getDeclaredConstructor(String.class, int.class, String.class,
						String.class);
				return paramCtor != null;
			} catch (Exception e) {
				return false;
			}
		}, "Employee must have a constructor with parameters: (String name, int idnumber, String department, String position)"));

		// Test 4: All setter methods exist (20 pts)
		tests.add(createTest("Test 4: All setter methods exist", 20, () -> {
			try {
				Class<?> clazz = Class.forName("Employee");
				clazz.getDeclaredMethod("setName", String.class);
				clazz.getDeclaredMethod("setIDNumber", int.class);
				clazz.getDeclaredMethod("setDepartment", String.class);
				clazz.getDeclaredMethod("setPosition", String.class);
				return true;
			} catch (Exception e) {
				return false;
			}
		}, "Must have setName(String), setIDNumber(int), setDepartment(String), setPosition(String)"));

		// Test 5: All getter methods exist and have correct return types (20 pts)
		tests.add(createTest("Test 5: All getter methods exist with correct types", 20, () -> {
			try {
				Class<?> clazz = Class.forName("Employee");

				Method getName = clazz.getDeclaredMethod("getName");
				Method getID = clazz.getDeclaredMethod("getIDNumber");
				Method getDept = clazz.getDeclaredMethod("getDepartment");
				Method getPos = clazz.getDeclaredMethod("getPosition");

				return getName.getReturnType() == String.class && getID.getReturnType() == int.class
						&& getDept.getReturnType() == String.class && getPos.getReturnType() == String.class;
			} catch (Exception e) {
				return false;
			}
		}, "Must have getName()→String, getIDNumber()→int, getDepartment()→String, getPosition()→String"));

		// Test 6: Functional test - Setters + Getters work correctly (15 pts)
		tests.add(createTest("Test 6: Setters and Getters work correctly", 15, () -> {
			try {
				Class<?> clazz = Class.forName("Employee");
				Object emp = clazz.getDeclaredConstructor().newInstance();

				// Use reflection to call setters
				Method setName = clazz.getDeclaredMethod("setName", String.class);
				Method setID = clazz.getDeclaredMethod("setIDNumber", int.class);
				Method setDept = clazz.getDeclaredMethod("setDepartment", String.class);
				Method setPos = clazz.getDeclaredMethod("setPosition", String.class);

				setName.invoke(emp, "Nguyen Van A");
				setID.invoke(emp, 12345);
				setDept.invoke(emp, "IT Department");
				setPos.invoke(emp, "Software Engineer");

				// Check with getters
				Method getName = clazz.getDeclaredMethod("getName");
				Method getID = clazz.getDeclaredMethod("getIDNumber");
				Method getDept = clazz.getDeclaredMethod("getDepartment");
				Method getPos = clazz.getDeclaredMethod("getPosition");

				return "Nguyen Van A".equals(getName.invoke(emp)) && 12345 == (int) getID.invoke(emp)
						&& "IT Department".equals(getDept.invoke(emp))
						&& "Software Engineer".equals(getPos.invoke(emp));
			} catch (Exception e) {
				return false;
			}
		}, "Setters must correctly update values and getters must return the updated values."));

		return tests;
	}

	// Helper to create TestCase
	private static ITestCase createTest(String name, int points, java.util.function.BooleanSupplier testLogic,
			String feedback) {
		return new ITestCase() {
			@Override
			public String getName() {
				return name;
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					return testLogic.getAsBoolean();
				} catch (Exception e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return feedback;
			}
		};
	}
}