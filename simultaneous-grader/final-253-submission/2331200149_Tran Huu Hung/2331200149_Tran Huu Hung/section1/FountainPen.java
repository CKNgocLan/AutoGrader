

public class FountainPen extends Pen {
    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color);
        this.setPrice(price);
    }

    @Override
    public String getDescription() {
        return "<" + brand.getName() + ">" + "-" + "<" + model + ">" + "-" + "<" + price + ">";
    }
}
