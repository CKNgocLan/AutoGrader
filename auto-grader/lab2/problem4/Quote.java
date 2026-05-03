
public class Quote {
    private double labourCharge;
    private double deliveryFee;
    private double ingredientCost;

    public Quote(double deliveryFee, double ingredientCost, double labourCharge) {
        this.deliveryFee = deliveryFee;
        this.ingredientCost = ingredientCost;
        this.labourCharge = labourCharge;
    }

    public Quote() {
        this.deliveryFee = 0;
        this.ingredientCost = 0;
        this.labourCharge = 0;
    }

    public double getLabourCharge() {
        return labourCharge;
    }

    public void setLabourCharge(double labourCharge) {
        this.labourCharge = labourCharge;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double getIngredientCost() {
        return ingredientCost;
    }

    public void setIngredientCost(double ingredientCost) {
        this.ingredientCost = ingredientCost;
    }

    public double getTotal() {
        return labourCharge + ingredientCost + deliveryFee;
    }

    public double getTotalAfterTax(double tax) {
        return getTotal() * (1 + tax / 100);
    }
}
