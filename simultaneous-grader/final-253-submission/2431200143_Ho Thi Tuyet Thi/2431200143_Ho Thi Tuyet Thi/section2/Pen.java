import java.util.*;

public class Pen {
    private UUID id;
    private String brand;
    private String model;
    private double price;

    public UUID getId() {
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

    public Pen(PenBuilder penBuilder) {
        this.id = UUID.randomUUID();
        this.brand = penBuilder.brand;
        this.model = penBuilder.model;
        this.price = penBuilder.price;
    }

    @Override
    public String toString() {
        return "ID: %s - Brand: %s - Model: %s - Price: %s".formatted(this.id, this.brand, this.model, this.price);
    }

    public static class PenBuilder {
        protected UUID id;
        protected String brand;
        protected String model;
        protected double price;

        public PenBuilder() {
        }

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

        public Pen build() {
            return new Pen(this);
        }
    }

    
}
