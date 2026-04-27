package problem3;

public class ServiceEstimate {
    private double groomingCost;
    private double additionalCareCost;
    private double tax;
    private Pet pet;

    public ServiceEstimate() {
    }

    public double getGroomingCost() {
        return groomingCost;
    }

    public void setGroomingCost(double groomingCost) {
        this.groomingCost = groomingCost;
    }

    public double getAdditionalCareCost() {
        return additionalCareCost;
    }

    public void setAdditionalCareCost(double additionalCareCost) {
        this.additionalCareCost = additionalCareCost;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public double getTotalCostAfterTax() {
        return (1 + tax) * (groomingCost + additionalCareCost);
    }

    @Override
    public String toString() {
        return "Grooming Cost: %s - Care Cost: %s - Pet: %s".formatted(groomingCost, additionalCareCost, pet);
    }
}
