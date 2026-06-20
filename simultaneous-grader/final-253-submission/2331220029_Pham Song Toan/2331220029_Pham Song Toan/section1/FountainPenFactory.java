public class FountainPenFactory implements PenFactory {

    private static FountainPenFactory instance;

    public static synchronized FountainPenFactory getInstance() {
        if (instance == null) {
            instance = new FountainPenFactory();
        }
        return instance;
    }

    @Override
    public Pen createPen(Brand brand, String model, Color color, double price) {
        return new FountainPen(brand, model, color, price);
    }
}
