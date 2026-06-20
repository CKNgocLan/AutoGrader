
import java.util.UUID;

public abstract class Pen {

    protected  UUID id = UUID.randomUUID();
    protected String model;
    protected Brand brand;
    protected Color color;
    protected double price;

    public Pen(Brand brand, String model, Color color, double price) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public abstract String getDescription();

    public UUID getId() {
        return id;
    }
}
