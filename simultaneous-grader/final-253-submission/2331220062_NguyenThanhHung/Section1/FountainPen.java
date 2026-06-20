public class FountainPen extends Pen {

    public FountainPen(Brand brand, Color color, String model, double price) {
        super(brand, color, model);
    }
    
    @Override
    public String toString() {
        return "BallpointPen: " + brand + "-" + model + "-" + color + "-" + price;
    }
    
}
