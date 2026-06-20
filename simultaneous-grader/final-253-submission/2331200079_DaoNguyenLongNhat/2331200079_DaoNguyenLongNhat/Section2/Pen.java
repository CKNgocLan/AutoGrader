//package Section2;

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
    

    @Override
    public String toString() {
        return String.format("ID: %s \nBrand: %s \nModel: %s\n Price: %s", id + brand + model + price);
    }

    public static class PenBuilder {
        private UUID id;
        private String brand;
        private String model;
        private double price;

        public PenBuilder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public PenBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public PenBuilder setPrice(double price) {
            this.price = price;
            return this;
        }
        public Pen build(){
            return new Pen(this);
        }
    }
}
