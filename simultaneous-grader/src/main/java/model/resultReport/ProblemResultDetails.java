package model.resultReport;

import model.component.Student;

public record ProblemResultDetails(String name, Student student, double passedPercent) {
	public String toCSVRow() {
		return null;
//		return ReportUtils.convertToCsvRow(student.idNumber(), student.fullName(), earnedPoints, passedFailed(), feedback);
	}
}
