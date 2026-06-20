
public class Client {
    private Pen pen;

    public Client(PenFactory factory, Brand brand, String model, Color color, double price) {
        this.pen = factory.createPen(brand, model, color, price);
    }

    public Client(PenFactory fountainPenFactory, Brand concopens, Object model, Color red, double price) {
        //TODO Auto-generated constructor stub
    }

    public Pen getPen() {
        return pen;
    }
}
