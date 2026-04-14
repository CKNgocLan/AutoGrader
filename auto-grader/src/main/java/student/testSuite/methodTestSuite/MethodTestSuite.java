package student.testSuite.methodTestSuite;
import java.lang.reflect.*;
import java.util.*;

import student.constant.ClassName;
import student.constant.Feedback;
import student.constant.TestcaseType;
import student.model.ITestCase;

/**
 * Test suite for the Employee class.
 * Tests constructors, getters, and setters as per the uploaded Employee.java
 */
public class MethodTestSuite {

    public static List<ITestCase> getAllTests() {
        List<ITestCase> tests = new ArrayList<>();

        return tests;
    }

    // Generic helper for getter + setter pair
    private static ITestCase createGetterSetterTest(String field, String getterName, String setterName,
                                                   Class<?> type, Object testValue, int points) {
        return new ITestCase() {
            public String getName() { return "Getter & Setter: " + field; }
            public int getPoints() { return points; }
            public boolean runTest() {
                try {
                    Class<?> clazz = Class.forName("Employee");
                    Constructor<?> ctor = clazz.getDeclaredConstructor();
                    Object emp = ctor.newInstance();

                    // Test Setter
                    Method setter = clazz.getMethod(setterName, type);
                    setter.invoke(emp, testValue);

                    // Test Getter
                    Method getter = clazz.getMethod(getterName);
                    Object result = getter.invoke(emp);

                    if (type == int.class) {
                        return (int) result == (int) testValue;
                    } else {
                        return result != null && result.equals(testValue);
                    }
                } catch (Exception e) {
                    return false;
                }
            }
            public String getFeedback() {
                return "Getter " + getterName + "() or Setter " + setterName + "() is missing or not working correctly for field '" + field + "'.";
            }
        };
    }
}