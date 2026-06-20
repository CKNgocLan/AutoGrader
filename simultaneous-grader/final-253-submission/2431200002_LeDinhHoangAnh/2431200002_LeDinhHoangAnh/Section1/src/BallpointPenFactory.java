public class BallpointPenFactory implements PenFactory{

@Override
public Pen createPen(String brand, String model, Color color, double price) {
    return new BallpointPen(null, model, color, price);
}
}
