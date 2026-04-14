package student;


/**
 * // Interface for your test cases
 */
public interface ITestCase {
	String getName();

	int getPoints(); // points for this test case (e.g., 20)

	boolean runTest(); // return true if passed

	String getFeedback(); // detailed message for student
}