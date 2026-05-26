public class BallpointPen extends Pen {

    public BallpointPen(String name, Brand brand, double price) {
        super(name, brand, price);
        setType(PenType.BALLPOINT);
    }

    public BallpointPen(String name, Brand brand, double price, Discount discount) {
        super(name, brand, price, discount);
        setType(PenType.BALLPOINT);
    }
    
    @Override
    public double getTax() {
        return Tax._8;
    }
}
