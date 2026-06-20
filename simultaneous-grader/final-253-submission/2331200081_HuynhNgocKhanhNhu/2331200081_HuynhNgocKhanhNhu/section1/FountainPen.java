public class FountainPen extends Pen {
    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);
    }
    @Override
    public Color getColor() {
        return Color.FOUNTAIN;
    }
    @Override
    public double getPrice() {
        return 645.27;
    }
}
