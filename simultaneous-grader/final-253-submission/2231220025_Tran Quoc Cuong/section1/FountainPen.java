public class FountainPen extends Pen{

    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);
    }
    
    public String getDescription() {
        return "Fountain Pen: " + brand + "-" + model + "-" + color + "-" + price;
    }
}
