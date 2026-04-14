
import ConstantValue.Const;

public class GroomingInvoice {
    private Customer customer;
    private Pet pet;
    private double groomingCost;
    private double additionalCareCharges;

    public GroomingInvoice(Customer customer, Pet pet, double groomingCost, double additionalCareCharges) {
        this.customer = customer;
        this.pet = pet;
        this.groomingCost = groomingCost;
        this.additionalCareCharges = additionalCareCharges;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public double getGroomingCost() {
        return groomingCost;
    }

    public void setGroomingCost(double groomingCost) {
        this.groomingCost = groomingCost;
    }

    public double getAdditionalCareCharges() {
        return additionalCareCharges;
    }

    public void setAdditionalCareCharges(double additionalCareCharges) {
        this.additionalCareCharges = additionalCareCharges;
    }

    public double getTotalCost() {
        double subtotal = this.getGroomingCost() + this.getAdditionalCareCharges();
        return subtotal * (1 + Const.TAX);
    }

    @Override
    public String toString() {
        return "Grooming Invoice:\n" +
                "Customer: " + customer.toString() + "\n" +
                "Pet: " + pet.toString() + "\n" +
                "Grooming Cost: " + groomingCost + "\n" +
                "Additional Charges: " + additionalCareCharges + "\n" +
                "Total Cost (inc. Tax): " + String.format("%.2f", getTotalCost());
    }
}
