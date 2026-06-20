import java.util.UUID;

public abstract class Pen {
    private UUID id;
    protected Brand brand;
    protected String model;
    protected Color color;
    protected double price;

    public Pen (Brand brand, String model, Color color, double price) {
        this.id = UUID.randomUUID();
        this.model = model;
        this.brand = brand;
        this.color = color;
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

    public String getDescription() {
        return "Pen: " + "brand=" + brand +", model=" + model + ", color=" + color;
    }
}
