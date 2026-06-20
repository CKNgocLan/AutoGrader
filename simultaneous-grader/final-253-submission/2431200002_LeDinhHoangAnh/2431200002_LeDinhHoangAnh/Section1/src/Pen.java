import java.util.UUID;

public abstract class Pen {
    private UUID id;
    private Brand brand;
    private String model;
    private Color color;
    private double price;
    
    public Pen(Brand brand) {
        this.brand = brand;
    }
    public Pen(Brand brand, String model, Color color) {
        this.brand = brand;
        this.model = model;
        this.color = color;
    }
    public Pen(){

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
    public UUID getId() {
        return id.randomUUID();
    }
    public abstract String getDescription();
}
