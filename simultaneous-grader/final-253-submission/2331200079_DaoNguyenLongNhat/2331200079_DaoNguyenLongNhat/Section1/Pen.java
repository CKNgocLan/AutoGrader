//package Section1;

import java.util.UUID;

public abstract class Pen {
    protected UUID id;
    protected Brand brand;
    protected String model;
    protected Color color;
    protected double price;

    public Pen(Brand brand, String model, Color color) {
        this.id = UUID.randomUUID();
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = 0;
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

    public abstract String getDescription();
}
