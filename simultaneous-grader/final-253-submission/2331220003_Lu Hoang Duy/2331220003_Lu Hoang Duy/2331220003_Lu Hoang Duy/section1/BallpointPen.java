
public class BallpointPen extends Pen {

    public BallpointPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);
    }

    @Override
    public String toString() {
        return "Ballpoint Pen :" + BallpointPen.class + "-" + BallpointPen.class + "-" + BallpointPen.class + "-"
                + BallpointPen.class;
    }

}
