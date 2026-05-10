public class BallpointPen extends Pen {
    public BallpointPen(String name, Brand brand, double price) {
        super(name, brand, price);
        super.setType(PenType.BALLPOINT);
    }

    public BallpointPen(String name, Brand brand, double price, Discount discount) {
        super(name, brand, price);
        super.setType(PenType.BALLPOINT);
        super.setDiscount(discount);
    }

    @Override
    public double getTax() {
        return Tax._8;
    }
}
