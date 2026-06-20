import java.util.UUID;

public abstract class Pen {

    protected UUID id;
    protected String model;
    protected Brand brand;
    protected Color color;
    protected double price;

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

    public String getDesciption() {
        return "pen";
    }
}
