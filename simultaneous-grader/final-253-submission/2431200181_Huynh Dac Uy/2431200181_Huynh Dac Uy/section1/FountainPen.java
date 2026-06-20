public class FountainPen extends Pen {

    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color);
        super.setPrice(price);
    }

    @Override
    public String getDescription() {
        String description = "Fountain Pen: " + super.getBrand() + "-" + super.getModel() + "-" + super.getColor() + "-"
                + super.getPrice();
        return description;
    }
}
