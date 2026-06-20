
public class FountainPen extends Pen {

    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);
        
    }
    @Override
    public String getDescription() {
        return "Fountain Pen: %s-%s-%s-%s\n".formatted(this.brand, this.model, this.color, this.price);
    }
}
