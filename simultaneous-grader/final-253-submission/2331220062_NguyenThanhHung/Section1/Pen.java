import java.util.UUID;

public abstract class Pen {
    private static final String Description = null;
    protected UUID id;
    protected Brand brand;
    protected String model;
    protected Color color;
    protected double price;

    public Pen(Brand brand, Color color, String model ) {
        this.brand = brand;
        this.color = color;
        this.model = model;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Color getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }
    
    public String getDescription() {
        return Description;
    }
  
}
