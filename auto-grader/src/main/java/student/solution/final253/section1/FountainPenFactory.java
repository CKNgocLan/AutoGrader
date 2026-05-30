package student.solution.final253.section1;

public class FountainPenFactory implements PenFactory {
    @Override
    public Pen createPen(Brand brand, String model, Color color, double price) {
        return new FountainPen(brand, model, color, price);
    }
}
