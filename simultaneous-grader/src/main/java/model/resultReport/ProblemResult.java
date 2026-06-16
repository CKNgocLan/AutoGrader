//package model.resultReport;
//
//import java.util.List;
//
//import common.util.ReportUtils;
//import model.component.Student;
//
//public record ProblemResult(String name, Student student, int passedNumber, float passedPercent, List<TestCaseResult> testCaseResult) {
//	public ProblemResult(String name, Student student, int passedNumber, List<TestCaseResult> testCaseResult) {
//		this(name, student, passedNumber, Float.valueOf(passedNumber)/testCaseResult.size(), testCaseResult);
//	}
//
//	public String toCSVRow() {
//		return null;
//		return ReportUtils.convertToCsvRow(student.idNumber(), student.fullName(), earnedPoints, passedFailed(), feedback);
//	}
//}
