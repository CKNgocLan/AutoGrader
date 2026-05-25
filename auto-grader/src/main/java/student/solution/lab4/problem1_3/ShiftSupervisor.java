package student.solution.lab4.problem1_3;

public class ShiftSupervisor extends Employee {
    private double annualSalary;
    private double annualProductionBonus;

    public ShiftSupervisor(String name, String employeeNumber, String hireDate, double annualSalary, double annualProductionBonus) {
        super(name, employeeNumber, hireDate);
        setAnnualSalary(annualSalary);
        setAnnualProductionBonus(annualProductionBonus);
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        if (annualSalary < 0) throw new IllegalArgumentException("Salary cannot be negative");
        this.annualSalary = annualSalary;
    }

    public double getAnnualProductionBonus() {
        return annualProductionBonus;
    }

    public void setAnnualProductionBonus(double annualProductionBonus) {
        if (annualProductionBonus < 0) throw new IllegalArgumentException("Bonus cannot be negative");
        this.annualProductionBonus = annualProductionBonus;
    }

    public double calculateTotalSalary() {
        return annualSalary + annualProductionBonus;
    }

    @Override
    public String toString() {
        return "ShiftSupervisor{" + super.toString() +
                ", annualSalary=" + annualSalary +
                ", annualProductionBonus=" + annualProductionBonus +
                '}';
    }
}
