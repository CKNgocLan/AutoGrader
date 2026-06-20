public class BallpointPen extends Pen {

    public BallpointPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color);
        super.setPrice(price);
    }

    @Override
    public String getDescription() {
        return "Fountain Pen: %s - %s - %s - %.2f".formatted(super.getBrand(), super.getModel(), super.getColor(), super.getPrice());
    }
    
}
