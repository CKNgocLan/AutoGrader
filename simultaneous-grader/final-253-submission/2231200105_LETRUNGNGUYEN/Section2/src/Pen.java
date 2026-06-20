
import java.util.UUID;

public class Pen<PenBuilder> {
    private static final String PenBuilder = null;
    private UUID id;
    private String brand;

    private String model;
    private double price;
    private String builder;
    
    public Pen (PenBuilder builder){
        this.builder = PenBuilder;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = UUID.randomUUID();
    }
  
    
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
   
    
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDescription(){
        return getDescription();
    }
     @Override
    public String toString(){
    return "%2s ,%2s ,%2s".formatted(brand,model,price);

}
}
