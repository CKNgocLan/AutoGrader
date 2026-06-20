
import java.util.UUID;

public abstract class Pen {
    public static String Brand;
    private UUID id;
    protected Brand brand;
    private String model;
    private Color color;
    private double price;

    public Pen(Brand brand, String model, Color color, double price) {
        this.id = UUID.randomUUID();
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public Pen(Brand brand, String model, Color color) {
        this.id = UUID.randomUUID();
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    @Override
    public String toString() {
        return "Pen :" + brand + "-" + model + "-" + color + "-" + price;
    }
}
