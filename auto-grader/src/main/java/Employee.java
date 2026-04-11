

/**
 * The Employee class stores data about an employee for the Employee Class
 * programming challenge.
 */

public class Employee {
	private String name;
	private int idNumber;
	private String department;
	private String position;

	public Employee() {
		name = "";
		idNumber = 0;
		department = "";
		position = "";
	}

	public Employee(String n, int id, String dept, String pos) {
		name = n;
		idNumber = id;
		department = dept;
		position = pos;
	}

	public Employee(String n, int id) {
		name = n;
		idNumber = id;
		department = "";
		position = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
