
public class FountainPenFactory implements PenFactory {
    public Pen createPen() {
        return new FountainPen(null, null, null, 0);
    }

    @Override
    public Pen createPen(Brand brand, String model, Color color, double price) {
        return new FountainPen(null, null, null, 0);
    }
}
