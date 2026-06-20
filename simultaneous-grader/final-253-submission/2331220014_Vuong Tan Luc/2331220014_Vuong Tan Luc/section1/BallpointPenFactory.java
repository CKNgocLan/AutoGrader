
public class BallpointPenFactory implements PenFactory {
    public Pen createPen(Brand brand, String model, Color color, double price){
        return new BallpointPen(brand, model, color);
    }




}

