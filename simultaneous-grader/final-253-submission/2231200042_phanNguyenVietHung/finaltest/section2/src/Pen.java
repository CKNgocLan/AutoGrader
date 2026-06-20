import java.util.UUID;

public abstract class Pen {    
    private UUID id;
    private String brand;
    private String modle;
    private double price;
    public Pen(UUID id, String brand, String modle, double price) {
        this.id = id;
        this.brand = brand;
        this.modle = modle;
        this.price = price;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModle() {
        return modle;
    }
    public void setModle(String modle) {
        this.modle = modle;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public static class  penBuilder {
        
    
        
    
    public Pen(PeBuilder builder){
        

    }
}
   
    