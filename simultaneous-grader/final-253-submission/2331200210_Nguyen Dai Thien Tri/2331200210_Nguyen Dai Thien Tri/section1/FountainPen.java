public class FountainPen extends Pen{
    public FountainPen(String brand, String model, Color color, double price) {
        super(brand, model, color);
    }
    public String getDescription() {
        return "Brand: " + getBrand() + " ||Model: " + getModel() + " ||Color:" + getColor() + " ||Price: " + getPrice();
    }
}
