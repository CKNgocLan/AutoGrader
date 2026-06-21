package model.resultReport;

import model.component.Student;

public record ProblemResultDetails(String name, Student student, int passedPercent, double weight) {
}
