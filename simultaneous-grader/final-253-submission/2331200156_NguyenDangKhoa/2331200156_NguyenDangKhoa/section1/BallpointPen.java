
public class BallpointPen extends Pen {


    public BallpointPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);
        //TODO Auto-generated constructor stub
    }
    @Override
    public String getDescription() {
        return "Ballpoint Pen: %s-%s-%s-%s\n".formatted(this.brand, this.model, this.color, this.price);
    }
}
