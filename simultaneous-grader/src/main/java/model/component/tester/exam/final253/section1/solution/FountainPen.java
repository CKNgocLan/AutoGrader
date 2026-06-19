package model.component.tester.exam.final253.section1.solution;

public class FountainPen extends Pen {
    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color);
        super.setPrice(price);
    }

    @Override
    public String getDescription() {
        return String.format("Fountain Pen: %s %s (%s) - $%.2f", getBrand().getName(), getModel(), getColor(), getPrice());
    }
}