
import java.util.UUID;

public abstract class Pen {

    private UUID id;
    private Brand brand;
    private String model;
    private Color color;
    private double price;

    public Pen(Brand brand, String model, Color color, double price) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public Pen(UUID id) {
        this.id = UUID.randomUUID();
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
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pen{");
        sb.append("id=").append(id);
        sb.append(", brand=").append(brand);
        sb.append(", model=").append(model);
        sb.append(", color=").append(color);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

}
