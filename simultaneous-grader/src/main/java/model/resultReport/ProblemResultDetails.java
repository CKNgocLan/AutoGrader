package model.resultReport;

import common.constant.Constants;
import common.message.GradingMessage;
import model.component.Student;

public class ProblemResultDetails {
	private String name;
	private Student student;
	private double passedPercent;
	private double weight;
	private String note;

	public ProblemResultDetails(String name, Student student, double passedPercent, double weight) {
		this.name = name;
		this.student = student;
		this.passedPercent = passedPercent;
		this.weight = weight;
		this.note = Constants.EMPTY_STRING;
	}

	public ProblemResultDetails(String name, Student student, double passedPercent, double weight, String note) {
		this.name = name;
		this.student = student;
		this.passedPercent = passedPercent;
		this.weight = weight;
		this.note = note;
	}

	public String getName() {
		return name;
	}

	public Student getStudent() {
		return student;
	}

	public double getPassedPercent() {
		return passedPercent;
	}

	public double getWeight() {
		return weight;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setPassedPercent(double passedPercent) {
		this.passedPercent = passedPercent;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static ProblemResultDetails compilationError(String name, Student student) {
		return new ProblemResultDetails(name, student, 0, 0, GradingMessage.COMPILATION_ERROR.getContent());
	}

	public static ProblemResultDetails exception(String name, Student student, Exception exception) {
		return new ProblemResultDetails(name, student, 0, 0, GradingMessage.UNEXPECTED_ERROR_WITH_MESSAGE.getContent(exception.getMessage()));
	}
}
