
public class BallpointPen extends Pen {

    public BallpointPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBrand() + " ");
        sb.append(getModel() + " ");
        sb.append(getColor() + " ");
        sb.append(getPrice() + " ");
        return sb.toString();
    }
}
