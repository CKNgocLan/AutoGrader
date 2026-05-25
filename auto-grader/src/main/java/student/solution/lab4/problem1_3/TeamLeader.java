package student.solution.lab4.problem1_3;

public class TeamLeader extends ProductionWorker {
    private double monthlyBonus;
    private double requiredTrainingHours;
    private double attendedTrainingHours;

    public TeamLeader(String name, String employeeNumber, String hireDate, int shift, double payRate,
                      double monthlyBonus, double requiredTrainingHours, double attendedTrainingHours) {
        super(name, employeeNumber, hireDate, shift, payRate);
        setMonthlyBonus(monthlyBonus);
        setRequiredTrainingHours(requiredTrainingHours);
        setAttendedTrainingHours(attendedTrainingHours);
    }

    public double getMonthlyBonus() {
        return monthlyBonus;
    }

    public void setMonthlyBonus(double monthlyBonus) {
        if (monthlyBonus < 0) throw new IllegalArgumentException("Monthly bonus cannot be negative");
        this.monthlyBonus = monthlyBonus;
    }

    public double getRequiredTrainingHours() {
        return requiredTrainingHours;
    }

    public void setRequiredTrainingHours(double requiredTrainingHours) {
        if (requiredTrainingHours < 0) throw new IllegalArgumentException("Required hours cannot be negative");
        this.requiredTrainingHours = requiredTrainingHours;
    }

    public double getAttendedTrainingHours() {
        return attendedTrainingHours;
    }

    public void setAttendedTrainingHours(double attendedTrainingHours) {
        if (attendedTrainingHours < 0) throw new IllegalArgumentException("Attended hours cannot be negative");
        this.attendedTrainingHours = attendedTrainingHours;
    }

    public void addTrainingHours(double hours) {
        if (hours < 0) throw new IllegalArgumentException("Hours to add must be non-negative");
        this.attendedTrainingHours += hours;
    }

    public boolean hasCompletedTraining() {
        return attendedTrainingHours >= requiredTrainingHours;
    }

    @Override
    public String toString() {
        return "TeamLeader{" + super.toString() +
                ", monthlyBonus=" + monthlyBonus +
                ", requiredTrainingHours=" + requiredTrainingHours +
                ", attendedTrainingHours=" + attendedTrainingHours +
                '}';
    }

    public double calculateTotalSalary(double monthlyHours) {
        return getPayRate() * monthlyHours + monthlyBonus;
    }
}
