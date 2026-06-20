//package Section1;

public class FountainPen extends Pen {

    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color);
    }

    @Override
    public String getDescription() {
        return "Fountain Pen: " + brand.getName() + "-" + model + "-" + color + "-" + price;
    }
    
}
