package model.resultReport;

import common.constant.Constants;
import model.component.Student;

public class ProblemResultDetails {
	private String name;
	private Student student;
	private int passedPercent;
	private double weight;
	private String note;

	public ProblemResultDetails(String name, Student student, int passedPercent, double weight) {
		this.name = name;
		this.student = student;
		this.passedPercent = passedPercent;
		this.weight = weight;
		this.note = Constants.EMPTY_STRING;
	}

	public ProblemResultDetails(String name, Student student, int passedPercent, double weight, String note) {
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

	public int getPassedPercent() {
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

	public void setPassedPercent(int passedPercent) {
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
}
