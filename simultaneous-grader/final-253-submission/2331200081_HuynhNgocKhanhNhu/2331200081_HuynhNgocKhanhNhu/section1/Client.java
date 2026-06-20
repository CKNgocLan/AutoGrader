import java.util.UUID;

public class Client {
    private UUID id;
    private Brand brand;
    private String model;
    private Color color;
    private double price;
    public Client(ClientBuilder builder) {
        this.id = builder.id;
        this.brand = builder.brand;
        this.model = builder.model;
        this.color = builder.color;
        this.price = builder.price;
    }
    @Override
    public String toString() {
        return 
    }
}
