public class BallpointPen extends Pen {
    public BallpointPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);
        // super.setPrice(price);
    }

    public String getDescription() {
        return "Fountain Pen: %s - %s - %s - %s".formatted(this.getBrand().toString(), this.getModel(), this.getColor(),
                this.getPrice());
    }
}
