
public class BallpointPenFactory implements PenFactory {
    public Pen createPen() {
        return new BallpointPen(null, null, null, 0);
    }

    @Override
    public Pen createPen(Brand brand, String model, Color color, double price) {
        return new BallpointPen(null, null, null, 0);
    }
}
