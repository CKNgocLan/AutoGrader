import java.lang.reflect.*;
import java.nio.file.Paths;
import java.util.*;

/**
 * Test suite for the Employee class.
 * Tests constructors, getters, and setters as per the uploaded Employee.java
 */
public class MethodTestSuite {

    public static List<TestCase> getAllTests() {
        List<TestCase> tests = new ArrayList<>();

        // Test 1: Class exists (10 pts)
        tests.add(createClassExistsTest(10));

//        // Test 2: No-argument constructor (15 pts)
//        tests.add(createNoArgConstructorTest(15));
//
//        // Test 3: Constructor with name, id, department, position (20 pts)
//        tests.add(createFullConstructorTest(20));
//
//        // Test 4: Constructor with name and id only (15 pts)
//        tests.add(createPartialConstructorTest(15));
//
//        // Test 5: getName() and setName() (10 pts)
//        tests.add(createNameGetterSetterTest(10));
//
//        // Test 6: getIdNumber() and setIdNumber() (10 pts)
//        tests.add(createIdGetterSetterTest(10));
//
//        // Test 7: getDepartment() and setDepartment() (10 pts)
//        tests.add(createDepartmentGetterSetterTest(10));
//
//        // Test 8: getPosition() and setPosition() (10 pts)
//        tests.add(createPositionGetterSetterTest(10));

        return tests;
    }

    // ===================================================================
    // Test 1: Class exists
    // ===================================================================
    private static TestCase createClassExistsTest(int points) {
        return new TestCase() {
			@Override
			public String getName() {
				return "Class Employee exists";
			}

			@Override
			public int getPoints() {
				return points;
			}

			@Override
			public boolean runTest() {
				try {
					Class.forName("Employee");
					return true;
				} catch (ClassNotFoundException e) {
					return false;
				}
			}

			@Override
			public String getFeedback() {
				return "Class 'Employee' was not found. Make sure Employee.java is in the submission folder.";
			}
        };
    }

    // ===================================================================
    // Test 2: No-argument constructor
    // ===================================================================
    private static TestCase createNoArgConstructorTest(int points) {
        return new TestCase() {
            public String getName() { return "No-argument constructor Employee()"; }
            public int getPoints() { return points; }
            public boolean runTest() {
                try {
                    Class<?> clazz = Class.forName("Employee");
                    Constructor<?> ctor = clazz.getDeclaredConstructor();
                    Object emp = ctor.newInstance();

                    // Check default values using getters
                    Method getName = clazz.getMethod("getName");
                    Method getId = clazz.getMethod("getIdNumber");
                    Method getDept = clazz.getMethod("getDepartment");
                    Method getPos = clazz.getMethod("getPosition");

                    return "".equals(getName.invoke(emp)) &&
                           (int) getId.invoke(emp) == 0 &&
                           "".equals(getDept.invoke(emp)) &&
                           "".equals(getPos.invoke(emp));
                } catch (Exception e) {
                    return false;
                }
            }
            public String getFeedback() {
                return "No-argument constructor Employee() is missing or does not initialize fields to default values (empty strings and 0).";
            }
        };
    }

    // ===================================================================
    // Test 3: Full constructor (name, id, department, position)
    // ===================================================================
    private static TestCase createFullConstructorTest(int points) {
        return new TestCase() {
            public String getName() { return "Full constructor Employee(String, int, String, String)"; }
            public int getPoints() { return points; }
            public boolean runTest() {
                try {
                    Class<?> clazz = Class.forName("Employee");
                    Constructor<?> ctor = clazz.getDeclaredConstructor(String.class, int.class, String.class, String.class);
                    Object emp = ctor.newInstance("Alice Smith", 12345, "IT", "Developer");

                    Method getName = clazz.getMethod("getName");
                    Method getId = clazz.getMethod("getIdNumber");
                    Method getDept = clazz.getMethod("getDepartment");
                    Method getPos = clazz.getMethod("getPosition");

                    return "Alice Smith".equals(getName.invoke(emp)) &&
                           (int) getId.invoke(emp) == 12345 &&
                           "IT".equals(getDept.invoke(emp)) &&
                           "Developer".equals(getPos.invoke(emp));
                } catch (Exception e) {
                    return false;
                }
            }
            public String getFeedback() {
                return "Full constructor Employee(String name, int id, String department, String position) is missing or does not set fields correctly.";
            }
        };
    }

    // ===================================================================
    // Test 4: Partial constructor (name, id only)
    // ===================================================================
    private static TestCase createPartialConstructorTest(int points) {
        return new TestCase() {
            public String getName() { return "Partial constructor Employee(String, int)"; }
            public int getPoints() { return points; }
            public boolean runTest() {
                try {
                    Class<?> clazz = Class.forName("Employee");
                    Constructor<?> ctor = clazz.getDeclaredConstructor(String.class, int.class);
                    Object emp = ctor.newInstance("Bob Wilson", 98765);

                    Method getName = clazz.getMethod("getName");
                    Method getId = clazz.getMethod("getIdNumber");
                    Method getDept = clazz.getMethod("getDepartment");
                    Method getPos = clazz.getMethod("getPosition");

                    return "Bob Wilson".equals(getName.invoke(emp)) &&
                           (int) getId.invoke(emp) == 98765 &&
                           "".equals(getDept.invoke(emp)) &&
                           "".equals(getPos.invoke(emp));
                } catch (Exception e) {
                    return false;
                }
            }
            public String getFeedback() {
                return "Constructor Employee(String name, int idNumber) is missing or does not initialize department and position to empty strings.";
            }
        };
    }

    // ===================================================================
    // Test 5-8: Getters and Setters
    // ===================================================================
    private static TestCase createNameGetterSetterTest(int points) {
        return createGetterSetterTest("Name", "getName", "setName", String.class, "Test Employee Name", points);
    }

    private static TestCase createIdGetterSetterTest(int points) {
        return createGetterSetterTest("IdNumber", "getIdNumber", "setIdNumber", int.class, 99999, points);
    }

    private static TestCase createDepartmentGetterSetterTest(int points) {
        return createGetterSetterTest("Department", "getDepartment", "setDepartment", String.class, "HR Department", points);
    }

    private static TestCase createPositionGetterSetterTest(int points) {
        return createGetterSetterTest("Position", "getPosition", "setPosition", String.class, "Senior Manager", points);
    }

    // Generic helper for getter + setter pair
    private static TestCase createGetterSetterTest(String field, String getterName, String setterName,
                                                   Class<?> type, Object testValue, int points) {
        return new TestCase() {
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