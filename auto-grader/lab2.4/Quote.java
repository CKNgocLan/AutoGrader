import java.util.ArrayList;
import java.util.List;

public class Quote {
    private List<String> ingredient;
    private double laborCharge;
    private double deliveryFee;
    private Cake cake;

    public Quote(Cake cake, double laborCharge, double deliveryFee) {
        this.cake = cake;
        this.laborCharge = laborCharge;
        this.deliveryFee = deliveryFee;
        this.ingredient = new ArrayList<>();
    }

    public List<String> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<String> ingredient) {
        this.ingredient = ingredient;
    }

    public double getLaborCharge() {
        return laborCharge;
    }

    public void setLaborCharge(double laborCharge) {
        this.laborCharge = laborCharge;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    public double getPriceAfterTax() {
        return (laborCharge + deliveryFee) * (1 + Const.TAX);
    }

    @Override
    public String toString() {
        return "Ingredient: %s - Labor Charge: %s - Delivery Fee: %s - Cake: %s".formatted(ingredient, laborCharge, deliveryFee, cake);
    }
}
