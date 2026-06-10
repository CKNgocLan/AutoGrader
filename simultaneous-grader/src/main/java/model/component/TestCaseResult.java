package model.component;

public record TestCaseResult(String testName, int maxPoints, int earnedPoints, boolean passed, String feedback) {

}
