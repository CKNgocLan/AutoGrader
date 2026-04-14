
import ConstantValue.EmployeeType;

public class Employee {
    private String name;
    private int idNumber;
    private String deparment;
    private String position;
    private EmployeeType employeeType;

    public Employee(String name, int idNumber, String deparment, String position) {
        this.name = name;
        this.idNumber = idNumber;
        this.deparment = deparment;
        this.position = position;
        employeeType = EmployeeType.FULL_TIME;
    }

    public Employee(String name, int idNumber) {
        this.name = name;
        this.idNumber = idNumber;
        this.deparment = "";
        this.position = "";
    }

    public Employee() {
        this.name = "";
        this.idNumber = 0;
        this.deparment = "";
        this.position = "";
    }

    public String getName() {
        return name;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public String getDeparment() {
        return deparment;
    }

    public String getPosition() {
        return position;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setDeparment(String deparment) {
        this.deparment = deparment;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "ID: " + this.idNumber + "Position: " + this.position + "Department: "
                + this.deparment;
    }

    public boolean equals(Employee employee) {
        return this.idNumber == employee.getIdNumber() && this.deparment == employee.getDeparment();
    }

}
