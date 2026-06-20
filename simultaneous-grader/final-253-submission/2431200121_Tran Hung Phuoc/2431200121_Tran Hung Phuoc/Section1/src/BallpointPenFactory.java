public class BallpointPenFactory implements PenFactory{
    @Override
    public Pen createPen(Brand brand, String model, Color color, double price){
    return new BallPointPen( brand, model, color,price);
    }
}