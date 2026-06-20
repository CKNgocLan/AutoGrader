public class BallpointPen extends Pen {

    public BallpointPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);
    }

    @Override
    public String getDescription() {
        return "Pen: " + getBrand() + "-" + getModel() + "-" + getColor()
                + "-" + getPrice();
    }

}
