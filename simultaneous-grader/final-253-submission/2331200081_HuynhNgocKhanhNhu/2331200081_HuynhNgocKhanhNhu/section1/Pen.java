import java.util.UUID;

public abstract class Pen {
    private UUID id;
    private String brand;
    private String model;
    private String color;
    private double price;
    public Pen (UUID id, String brand, String model, String color, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel(){
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return"ID: %s \nBrand: %s \nModel: %s \nColor: %s \nPrice: %.2f"
        .formatted(getId(), getBrand(), getModel(), getColor(), getPrice());
    }
    public abstract Color getColor();
    public abstract double getPrice();
}