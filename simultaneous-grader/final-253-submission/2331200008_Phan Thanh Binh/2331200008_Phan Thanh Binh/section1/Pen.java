
import java.util.UUID;

public abstract class Pen {
    protected UUID id;
    protected Brand brand;
    protected String modle;
    protected Color color;
    protected double price;

    public Pen(Brand brand, String modle, Color color, double price) {
        this.brand = brand;
        this.modle = modle;
        this.color = color;
        this.price = price;
    }

    
    
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    public void setModle(String modle) {
        this.modle = modle;
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
    public String getModle() {
        return modle;
    }
    public Color getColor() {
        return color;
    }
    public double getPrice() {
        return price;
    }
    public String getDescription(){
        return "";
    }
    

    


    
}
