public class BallpointPen extends Pen {

    public BallpointPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color);
        super.setPrice(price);
    }

    @Override
    public String getDescription() {
        return "Ballpoint Pen: %s-%s-%s-%s".formatted(super.getBrand(), super.getModel(), super.getColor(),
                super.getPrice());
    }

}
