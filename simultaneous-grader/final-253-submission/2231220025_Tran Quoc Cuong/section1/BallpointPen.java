public class BallpointPen extends Pen{

    public BallpointPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);

    }

    public String getDescription() {
        return "Ballpoint Pen: " + brand + "-" + model + "-" + color + "-" + price;
    }
}
