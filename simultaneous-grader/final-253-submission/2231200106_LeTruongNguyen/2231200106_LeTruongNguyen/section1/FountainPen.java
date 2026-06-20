

public class FountainPen extends Pen {

    public FountainPen(Brand brand, String model, Color color) {
        super(brand, model, color);
        this.color = Color.RED;
        this.price = 645.27;
    }

    public String getDescription() {
        return "Fountain Pen:" + brand + "-" + model + "-" + color + "-" + price + "]";
    }
}
