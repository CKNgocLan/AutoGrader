import java.util.UUID;

public class Pen {
        private UUID id;
        private String brand;
        private String model;
        private double price;

    public Pen(PenBuilder builder) {
        this.id = builder.id;
        this.brand = builder.brand;
        this.model = builder.model;
        this.price = builder.price;
    }

    public static class PenBuilder{
        private UUID id;
        private String brand;
        private String model;
        private double price;

    public UUID getId() {
        return UUID.randomUUID();
    }

    public String getModel() {
        return model;
    }

        public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Pen build(){
        return new Pen(this);
    }
 }

    public UUID getId() {
        return UUID.randomUUID();
    }

    public String getModel() {
        return model;
    }

        public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}

    @Override
    public String toString(){
        return " Model: " + model + " Price: " + this.price;
    }
