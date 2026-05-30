
import java.util.UUID;

public abstract class Pen {
    private UUID id;
    private Brand brand;
    private String model;
    private Color color;
    private double price;

    public Pen(Brand brand, String model, Color color) {
        this.id = UUID.randomUUID();
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UUID getID() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public Brand getBrand() {
        return brand;
    }

    public Color getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getDescription();
}
