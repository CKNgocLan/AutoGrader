
public class BallpointPen extends Pen {
    public BallpointPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color);
        setPrice(price);
    }

	@Override
    public String getDescription() {
        return String.format("Ballpoint Pen: %s %s (%s) - $%.2f", getBrand().getName(), getModel(), getColor(),
                getPrice());
    }
}
