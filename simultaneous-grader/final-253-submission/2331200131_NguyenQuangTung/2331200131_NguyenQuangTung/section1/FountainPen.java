public class FountainPen extends Pen {

    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);
    }

    @Override
    public String getDescription() {
        return "Fountain Pen: " + getBrand() + " " + getModel() + " " + getColor() + " " + getPrice();
    }

}
