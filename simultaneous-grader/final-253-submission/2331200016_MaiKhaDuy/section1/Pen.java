package section1;

import java.util.*;

public abstract class Pen {
    private UUID id;
    public Brand brand;
    private String model;
    private Color color;

    public Pen(Brand brand, String model, Color color) {
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
    @Override
    public String toString() {
        return "Pen [id=" + id + ", brand=" + brand + ", model=" + model + ", color=" + color + "]";
    }


}
