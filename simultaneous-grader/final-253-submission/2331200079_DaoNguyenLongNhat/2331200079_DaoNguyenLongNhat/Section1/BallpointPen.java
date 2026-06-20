//package Section1;

public class BallpointPen extends Pen {

    public BallpointPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color);
    }

    @Override
    public String getDescription() {
        return "Ballpoint Pen: " + brand.getName() + "-" + model + "-" + color + "-" + price;
    }
    
}
