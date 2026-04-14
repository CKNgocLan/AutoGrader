
public class ServiceEstimate {
    private double costGroom;
    private double careCharges;
    private double tax;

    public ServiceEstimate(double costGroom, double careCharges, double tax) {
        this.costGroom = costGroom;
        this.careCharges = careCharges;
        this.tax = tax;
    }

    public void setCostGroom(double costGroom) {
        this.costGroom = costGroom;
    }

    public double getCostGroom() {
        return this.costGroom;
    }

    public void setCareCharges(double careCharges) {
        this.careCharges = careCharges;
    }

    public double getCareCharges() {
        return this.careCharges;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTax() {
        return this.tax;
    }

}
