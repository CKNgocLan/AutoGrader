import java.util.UUID;

public abstract class Pen {
    private UUID id;
    private Brand brand;
    private String model;
    private Color color;

    private double price;

    public Pen(Brand brand, String model, Color color) {
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public Pen(Brand brand, Color color, String model) {
        this.id = UUID.randomUUID();
        this.brand = brand;
        this.color = color;
        this.model = model;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDisciption() {
        return String.format("%s- %s- %s", brand, model, color);
    }

}
