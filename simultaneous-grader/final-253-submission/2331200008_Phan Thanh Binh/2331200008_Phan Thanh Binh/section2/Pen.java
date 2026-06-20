

import java.util.UUID;

public class Pen {
    private UUID id;
    private String brand;
    private String model;
    private double price;

    public Pen (PenBuilder builder){
        this.id= UUID.randomUUID();
        this.brand=builder.brand;
        this.model=builder.model;
        this.price=builder.price;

        


    }

    public UUID getID() {
        return id;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public double getPrice() {
        return price;
    }
    public void setID(UUID id) {
        this.id = id;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return brand + model + price;
    }






    public static class PenBuilder{
        private UUID id;
        private String brand;
        private String model;
        private double price;

        public PenBuilder setBrand(String brand){
            this.brand=brand;
            return this;
        }
        public PenBuilder setModel(String model){
            this.model=brand;
            return this;
        }
        public PenBuilder setPrice(double price){
            this.price=price;
            return this;
        }
        public Pen build(){
            return new Pen(this);
        }


        


        
    }
}
