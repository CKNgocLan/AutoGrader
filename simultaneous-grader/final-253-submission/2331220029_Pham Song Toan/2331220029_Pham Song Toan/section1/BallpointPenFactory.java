public class BallpointPenFactory implements PenFactory {
    private static BallpointPenFactory instance;

    public static synchronized BallpointPenFactory getInstance() {
        if (instance == null) {
            instance = new BallpointPenFactory();
        }
        return instance;
    }

    @Override
    public Pen createPen(Brand brand, String model, Color color, double price) {
        return new FountainPen(brand, model, color, price);
    }

}
