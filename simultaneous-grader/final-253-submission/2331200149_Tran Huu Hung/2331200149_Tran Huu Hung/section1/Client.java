

public class Client {
    private PenFactory penFactory;

    public Client(PenFactory penFactory) {
        this.penFactory = penFactory;
    }

    public Pen getPen(Brand brand, String model, Color color, double price) {
        return this.penFactory.createPen(brand, model, color, price);
    }
}
