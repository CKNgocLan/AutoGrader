
public class BallpointPen extends Pen {
    public BallpointPen(Brand brand, String model, Color color) {
        super(brand, model, color);
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("ballpointPen");
        return sb.toString();
    }

}
