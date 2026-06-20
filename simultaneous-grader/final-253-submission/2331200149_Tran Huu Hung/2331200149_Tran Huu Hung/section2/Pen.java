

import java.util.UUID;

public class Pen {
    private final UUID id;
    private final String brand;
    private final String model;
    private final double price;

    private Pen(PenBuilder builder) {
        this.id = UUID.randomUUID();
        this.brand = builder.brand;
        this.model = builder.model;
        this.price = builder.price;
    }

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

    @Override
    public String toString() {
        return id + " " + brand + " " + model + " " + price;
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

        public Pen build() {
            return new Pen(this);
        }
    }

}
