package student.solution.lab4.problem1_3;

public class ProductionWorker extends Employee {
    // shift: 1 = day, 2 = night
    private int shift;
    private double payRate;

    public ProductionWorker(String name, String employeeNumber, String hireDate, int shift, double payRate) {
        super(name, employeeNumber, hireDate);
        setShift(shift);
        setPayRate(payRate);
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        if (shift != 1 && shift != 2) {
            throw new IllegalArgumentException("Shift must be 1 (day) or 2 (night)");
        }
        this.shift = shift;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        if (payRate < 0) {
            throw new IllegalArgumentException("Pay rate cannot be negative");
        }
        this.payRate = payRate;
    }

    public String getShiftName() {
        return shift == 1 ? "Day" : "Night";
    }

    @Override
    public String toString() {
        return "ProductionWorker{" + super.toString() +
                ", shift=" + getShiftName() +
                ", payRate=" + payRate +
                '}';
    }
}
