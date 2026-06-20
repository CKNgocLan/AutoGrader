public class FountainPenFactory implements PenFactory {

    @Override
    public Pen createPen(String brand, String model, Color color, double price) {
        return new FountainPen(null, model, color, price);
    }
}
