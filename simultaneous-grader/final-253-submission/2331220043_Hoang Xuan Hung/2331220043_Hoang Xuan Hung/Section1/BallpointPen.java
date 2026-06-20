public class BallpointPen extends Pen {
    public BallpointPen(Brand brand, String model, Color color) {
        super(brand, model, color);
        
    }
    
    public String getDescription() {
        return String.format("%s - %s - %s - %.2f", getBrand(), getModel(), getColor(), getPrice());
    }

}
    