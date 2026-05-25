package student.solution.lab4.problem1_3;

public class Employee {
    private String name;
    private String number;
    private String hireDate;

    public Employee(String name, String number, String hireDate) {
        this.name = name;
        this.number = number;
        this.hireDate = hireDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (!isValidNumber(number)) {
            throw new IllegalArgumentException("Invalid employee number format.");
        }
        this.number = number;
    }

    public boolean isValidNumber(String number) {
        if (number == null) {
            return false;
        }
        return number.matches("\\d{3}-[A-M]");
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", hireDate='" + hireDate + '\'' +
                '}';
    }
}
