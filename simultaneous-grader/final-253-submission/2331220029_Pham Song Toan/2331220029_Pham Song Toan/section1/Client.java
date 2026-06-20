public class Client {
    private Pen pen;
    private String instanceName;
    private PenFactory penFactory;
    private Brand brand;
    private String model;
    private Color color;
    private double price;

    public Client(String instanceName, PenFactory penFactory, Brand brand, String model, Color color, double price) {
        this.instanceName = instanceName;
        this.penFactory = penFactory;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public Client(PenFactory penFactory) {
        // TODO
        return "Instance Name: %s, PenFactory: %s, Brand: %s, Model: %s, Color: %s, Price: %.2f"
                .formatted(instanceName, penFactory, brand, model, color, price);
    }

    public Client(Client ballpointPenClient, Brand sailor, String string, Color grey, double i) {
        // TODO Auto-generated constructor stub
    }

    public Pen getPen() {
        return this.pen;
    }
}
