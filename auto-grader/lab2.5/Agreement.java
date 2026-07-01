public class Agreement {
    private Car car;
    private String purpose;
    private double baseRentalFee;
    private double mileageFee;

    public Agreement(Car car, String purpose, double baseRentalFee, double mileageFee) {
        this.car = car;
        this.purpose = purpose;
        this.baseRentalFee = baseRentalFee;
        this.mileageFee = mileageFee;
    }

    public double getRentalCostAfterTax() {
        return (1 + Const.TAX) * (baseRentalFee + mileageFee);
    }

    @Override
    public String toString() {
        return "Purpose: %s - Base Rental Fee: %s - Mileage Fee: %s - Car: %s".formatted(purpose, baseRentalFee, mileageFee, car);
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public double getBaseRentalFee() {
        return baseRentalFee;
    }

    public void setBaseRentalFee(double baseRentalFee) {
        this.baseRentalFee = baseRentalFee;
    }

    public double getMileageFee() {
        return mileageFee;
    }

    public void setMileageFee(double mileageFee) {
        this.mileageFee = mileageFee;
    }
}
