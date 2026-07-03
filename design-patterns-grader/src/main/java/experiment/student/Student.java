package experiment.student;

public class Student {
	private String number;
	private String fullName;

	public Student(String number, String fullName) {
		this.number = number;
		this.fullName = fullName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "%s - %s".formatted(number, fullName);
	}
}
