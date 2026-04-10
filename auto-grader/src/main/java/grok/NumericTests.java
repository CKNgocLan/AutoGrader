package grok;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import grok.submissions.Main;

/**
 * // Your test suite (add more here)
 */
public class NumericTests {

	private static final long TIMEOUT_MS = 2000; // Prevent infinite loops

	private static String runStudentMain(String input) throws Exception {
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);

		try {
			System.setIn(new ByteArrayInputStream(input.getBytes("UTF-8")));
			System.setOut(ps);

			// Run student's main safely with timeout
			Thread thread = new Thread(() -> {
				try {
					Main.main(new String[0]);
				} catch (Exception ignored) {
				}
			});
			thread.start();
			thread.join(TIMEOUT_MS);

			if (thread.isAlive()) {
				thread.interrupt();
				return "TIMEOUT";
			}

			ps.flush();
			return baos.toString("UTF-8").trim();
		} finally {
			System.setIn(originalIn);
			System.setOut(originalOut);
		}
	}

	public static List<TestCase> getAllTests() {
		List<TestCase> tests = new ArrayList<>();

		// Test 1: Sum (20 pts)
		tests.add(createNumericTest("Test 1: Sum of 5 and 3", 20, "5\n3\n", 8, "Expected: 8 (5 + 3)"));

		// Test 2: Average with floating point tolerance (30 pts)
		tests.add(createNumericDoubleTest("Test 2: Average of 10 20 30", 30, "10\n20\n30\n", 20.0, 0.01,
				"Expected ≈ 20.0 (average)"));

		// Test 3: Maximum (25 pts)
		tests.add(createNumericTest("Test 3: Max of 7 12 5", 25, "7\n12\n5\n", 12, "Expected: 12 (maximum)"));

		// Test 4: Product (25 pts) — easy to add more
		tests.add(createNumericTest("Test 4: Product of 6 and 7", 25, "6\n7\n", 42, "Expected: 42 (6 * 7)"));

		return tests;
	}

	// Helper for integer numeric assertion
	private static TestCase createNumericTest(String name, int points, String input, int expected, String feedback) {
		return new TestCase() {
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
					String output = runStudentMain(input);
					if (output.equals("TIMEOUT")) {
						return false;
					}
					int result = Integer.parseInt(output.trim());
					return result == expected;
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

	// Helper for double numeric assertion (with tolerance)
	private static TestCase createNumericDoubleTest(String name, int points, String input, double expected,
			double tolerance, String feedback) {
		return new TestCase() {
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
					String output = runStudentMain(input);
					if (output.equals("TIMEOUT")) {
						return false;
					}
					double result = Double.parseDouble(output.trim());
					return Math.abs(result - expected) <= tolerance;
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