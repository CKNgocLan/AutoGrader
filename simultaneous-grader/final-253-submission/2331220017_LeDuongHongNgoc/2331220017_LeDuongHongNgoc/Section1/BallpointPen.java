public class BallpointPen extends Pen {
    public BallpointPen(String model, Brand brand, Color color, double price) {
        super(model, brand, color, price);

    }

    public void getDescription() {
        System.out.println(
                "Ballpoint Pen: " + brand.getName() + " - " + getModel() + " - " + getColor() + " - " + getPrice());
    }

}
