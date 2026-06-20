public class FountainPen extends Pen {
    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color);
        super.setPrice(price);
    }

    @Override
    public String getDescription() {
        return "%s - %s - %s - %s".formatted(super.getBrand().toString(), super.getModel(), super.getColor(),
                super.getPrice());
    }
}
