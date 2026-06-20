
public class BallpointPenFactory implements PenFactory {
     @Override
    public Pen createPen(Brand brand, String modle, Color color, double price){
        return new BallpointPen(brand, modle, color, price);
    }
}
