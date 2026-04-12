
import java.util.ArrayList;
import java.util.List;

public class SampleTestSuite {
	public static List<TestCase> getAllTests() {
		List<TestCase> tests = new ArrayList<>();

		// Test 1: Sum (20 pts)
		tests.add(createClassExistsTest(50));

		return tests;
	}
	
	// ===================================================================
    // Test 1: Class exists
    // ===================================================================
    private static TestCase createClassExistsTest(int points) {
        return new TestCase() {
            public String getName() { return "Class Employee exists"; }
            public int getPoints() { return points; }
            public boolean runTest() {
                try {
                    Class.forName("Employee");
                    return true;
                } catch (ClassNotFoundException e) {
                    return false;
                }
            }
            public String getFeedback() {
                return "Class 'Employee' was not found. Make sure Employee.java is in the submission folder.";
            }
        };
    }

}
