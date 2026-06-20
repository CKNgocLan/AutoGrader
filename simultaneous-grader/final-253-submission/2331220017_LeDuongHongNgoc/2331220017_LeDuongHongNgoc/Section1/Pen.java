import java.util.UUID;

public abstract class Pen {
    private UUID id;
    private String model;
    protected Brand brand;
    private Color color;
    private double price;
    public Pen(String model, Brand brand, Color color,double price){
        this.model=model;
        this.brand=brand;
        this.color=color;
        this.price=price;
        id=UUID.randomUUID();
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
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString(){
        return getModel()+" - " + getBrand()+" - " + getColor()+" - " + getPrice();
    }
   

}
