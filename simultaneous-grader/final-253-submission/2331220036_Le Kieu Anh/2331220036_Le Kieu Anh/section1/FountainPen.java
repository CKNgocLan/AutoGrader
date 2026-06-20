// package section1;

public class FountainPen extends Pen {
    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);

    }

    @Override
    public String getDescripton() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "Fountain Pen: " + getBrand().getName() + " - " + getModel() + " - " + getColor() + " - " + getPrice());
        return sb.toString();
    }
}
