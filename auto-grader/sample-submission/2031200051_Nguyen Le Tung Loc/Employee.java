
public class Employee {
	private String name;
	private String department;
	private String position;
	private int idNumber;

	public Employee(String name, int idNumber, String department, String position) {
		this.name = name;
		this.idNumber = idNumber;
		this.department = department;
		this.position = position;
	}

	public Employee(String name, int idNumber) {
		this.name = name;
		this.idNumber = idNumber;
		this.department = "";
		this.position = "";
	}

	public Employee() {
		this.name = "";
		this.idNumber = 0;
		this.department = "";
		this.position = "";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public int getIdNumber() {
		return this.idNumber;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return this.position;
	}
	
	public double getTotalCost() {
		return 0;
	}
}
