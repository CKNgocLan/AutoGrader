
public class Client {
    private Pen pen;

    public Client(PenFactory factory, Brand brand, String model, Color color, Double price) {
        this.pen = factory.createPen(brand, model, color, price);
    }

    public Pen getPen() {
        return this.pen;
    }

}
