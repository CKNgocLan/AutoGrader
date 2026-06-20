public class BallpointPen extends Pen {
    public BallpointPen(Brand brand, Color color, String model, double price) {
        super(brand, color, model);
    }

    @Override
    public String toString() {
        return "BallpointPen: " + brand + "-" + model + "-" + color + "-" + price;
    }
}
