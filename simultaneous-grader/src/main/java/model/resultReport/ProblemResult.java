package model.resultReport;

import java.util.List;

import model.component.Student;

public record ProblemResult(Student student, int passedNumber, float passedPercent, List<TestCaseResult> testCaseResult) {

}
