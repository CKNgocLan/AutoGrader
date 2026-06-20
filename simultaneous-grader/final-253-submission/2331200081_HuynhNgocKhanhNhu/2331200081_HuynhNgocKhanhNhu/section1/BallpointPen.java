public class BallpointPen extends Pen{
    public BallpointPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);
    }
    @Override
    public Color getColor() {
        return Color.BALLPOINT;
    }
    @Override
    public double getPrice() {
        return 65;
    }
}
