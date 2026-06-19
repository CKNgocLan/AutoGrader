package model.component.tester.exam.final253.section1.solution;

public class BallpointPenFactory implements PenFactory {
    @Override
    public Pen createPen(Brand brand, String model, Color color, double price) {
        return new BallpointPen(brand, model, color, price);
    }
}
