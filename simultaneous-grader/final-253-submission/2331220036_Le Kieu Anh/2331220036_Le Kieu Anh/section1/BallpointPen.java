// package section1;

public class BallpointPen extends Pen {

    public BallpointPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);

    }

    @Override
    public String getDescripton() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ballpoint Pen: " + getBrand().getName() + " - " + getModel() + " - " + getColor() + " - "
                + getPrice());
        return sb.toString();
    }
}
