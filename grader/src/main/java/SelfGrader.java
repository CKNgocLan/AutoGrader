

import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Main application (student runs this)
 * <br>
 * The student-friendly main app
 */
public class SelfGrader {
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("     LAB SELF-GRADER (Offline)");
        System.out.println("     Total possible score: 100 points");
        System.out.println("===========================================\n");

        System.out.println("Make sure your code is saved in Main.java");
        System.out.println("Press Enter to start grading...");

        new Scanner(System.in).nextLine(); // Wait for student to press Enter

        gradeSubmission();

        System.out.println("\nYou can run this again anytime to re-grade.");
        System.out.println("Good luck with your lab!");
    }

    private static void gradeSubmission() {
        try {
            // Step 1: Compile student's Main.java
            System.out.println("Compiling Main.java ...");
            String mainPath = Paths.get(System.getProperty("user.dir").toString(), "src", SelfGrader.class.getPackageName(), "Main.java").toString();
//            Process compile = new ProcessBuilder("javac", "Main.java").start();
            Process compile = new ProcessBuilder("javac", mainPath).start();
            int compileCode = compile.waitFor();

            if (compileCode != 0) {
                System.out.println("❌ Compilation failed! Check for syntax errors.");
                printError(compile.getErrorStream());
                System.out.println("Final Score: 0 / 100");
                return;
            }
            System.out.println("✅ Compilation successful.\n");

            // Step 2: Run tests
            List<TestCase> tests = NumericTests.getAllTests();
            int totalScore = 0;
            int totalPossible = 0;

            System.out.println("Running your tests:\n");

            for (TestCase test : tests) {
                totalPossible += test.getPoints();
                System.out.println("→ " + test.getName() + " (" + test.getPoints() + " pts)");

                boolean passed = test.runTest();
                if (passed) {
                    System.out.println("   ✅ PASSED (+ " + test.getPoints() + " pts)");
                    totalScore += test.getPoints();
                } else {
                    System.out.println("   ❌ FAILED");
                    System.out.println("      " + test.getFeedback());
                }
            }

            // Final result
            System.out.println("\n" + "=".repeat(50));
            System.out.printf("YOUR SCORE: %d / %d%n", totalScore, totalPossible);
            if (totalScore == totalPossible) {
                System.out.println("🎉 Excellent! All tests passed.");
            } else if (totalScore >= 80) {
                System.out.println("👍 Good work! Keep improving.");
            } else {
                System.out.println("💡 Review the failed tests and try again.");
            }
            System.out.println("=".repeat(50));

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printError(java.io.InputStream stream) throws Exception {
        try (Scanner sc = new Scanner(stream)) {
            while (sc.hasNextLine()) {
                System.out.println("   " + sc.nextLine());
            }
        }
    }
}