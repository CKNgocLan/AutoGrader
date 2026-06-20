public class FountainPen extends Pen {
    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color);
        super.setPrice(price);
    }
    
    @Override
    public String getDiscription() {
        return "Fountain Pen: %s - %s - %s - %s".formatted(this.getBrand(), this.getModel(), this.getColor(), this.getPrice());
    }
    
}
