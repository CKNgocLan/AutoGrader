public class BallpointPenFactory {
     public BallpointPenFactory(String brand, String model, Color color, double price) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
    }
     private String brand;
    private String model;
    private Color color;
    private double price;
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
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
    public FoundtainPenFactory creatPen(String brand, String model, Color color, double price){
        return new FoundtainPenFactory(brand, model, color, price);

    }
}

