public class BallpointPen extends Pen {

    public BallpointPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color);
        super.setPrice(price);
    }

    @Override
    public String getDescription() {
        String description = "Ballpoint Pen: " + super.getBrand() + "-" + super.getModel() + "-" + super.getColor() + "-"
                + super.getPrice();
        return description;
    }
}
