public class Car {
    private String make;
    private String model;
    private int period;
    private int mileageLimit;
    private Customer customer;

    public Car(String make, String model, int period, int mileageLimit, Customer customer) {
        this.make = make;
        this.model = model;
        this.period = period;
        this.mileageLimit = mileageLimit;
        this.customer = customer;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getMileageLimit() {
        return mileageLimit;
    }

    public void setMileageLimit(int mileageLimit) {
        this.mileageLimit = mileageLimit;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Make: %s - Model: %s - Period: %s - Mileage Limit: %s - Customer: %s".formatted(make, model, period, mileageLimit, customer.getName());
    }
}
