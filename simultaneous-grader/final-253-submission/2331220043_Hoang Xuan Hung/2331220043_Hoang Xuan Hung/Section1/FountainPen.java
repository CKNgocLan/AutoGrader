public class FountainPen extends Pen {
    public FountainPen(Brand brand, String model, Color color) {
        super(brand, model, color);
    }

    public String getDescription() {
        return String.format("%s - %s - %s  %.2f", getBrand(), getModel(), getColor(), getPrice());
    }
}
