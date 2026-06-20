import java.util.UUID;

public abstract class Pen {    
    private UUID id;
    private Brand brand;
    private String modle;
    private Color color;
    private double price;
    public Pen(Brand brand, String modle, Color color) {
        this.brand = brand;
        this.modle = modle;
        this.color = color;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.Brand = brand;
    }
    public String getModle() {
        return modle;
    }
    public void setModle(String modle) {
        this.modle = modle;
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
        return "pen [id= "id" + ", brand" + ", modle" + " , color " + " , price"]";
    }
    

}
