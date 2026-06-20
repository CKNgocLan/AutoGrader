import java.util.*;
public abstract class Pen {
    private UUID id;
    private Brand brand;
    private String model;
    private Color color;
    private double price;

    public Pen(Brand brand, String model, Color color){
        this.model = model;
        this.color = color;
        this.brand = brand;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
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

    public abstract String getDescription();
}
