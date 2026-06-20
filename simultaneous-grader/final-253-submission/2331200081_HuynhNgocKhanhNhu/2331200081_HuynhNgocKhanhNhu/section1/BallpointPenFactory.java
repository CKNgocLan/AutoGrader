public class BallpointPenFactory extends PenFactory {
    @Override
    public Pen createPen(Brand brand, String model, Color color, double price) {
        return new BallpointPenFactory(brand, model,color, price);
    }
}
