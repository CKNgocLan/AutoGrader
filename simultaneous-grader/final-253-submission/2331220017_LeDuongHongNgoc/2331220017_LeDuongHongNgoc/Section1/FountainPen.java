public class FountainPen extends Pen {
    public FountainPen(String model, Brand brand, Color color, double price) {
        super(model, brand, color, price);

    }

    public void getDescription() {
        System.out.println(
                "Fountain Pen: " + brand.getName() + " - " + getModel() + " - " + getColor() + " - " + getPrice());
    }

}
