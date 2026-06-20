

public class BallpointPen extends Pen {

    public BallpointPen(Brand brand, String model, Color color) {
        super(brand, model, color);
        this.color = Color.GREY;
        this.price = 65.0;
    }

    public String getDescription() {
        return "Ballpoint Pen:" + brand + "-" + model + "-" + color + "-" + price + "]";
    }

}
