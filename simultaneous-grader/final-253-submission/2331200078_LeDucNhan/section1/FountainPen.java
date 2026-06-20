
public class FountainPen extends Pen {

    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color);
        setPrice(price);

    }

    @Override
    public String getDescription() {
        return getBrand() + "-" + getModel() + "-" + getColor() + "-" + getPrice();
    }

}
