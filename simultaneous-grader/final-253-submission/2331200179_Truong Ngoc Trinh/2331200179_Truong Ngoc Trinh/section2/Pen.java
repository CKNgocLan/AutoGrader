
import java.util.UUID;

public class Pen {
    private UUID id;
    private String brand;
    private String model;
    private double price;

    public Pen(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.id = UUID.randomUUID();
    }

    public Pen(PenBuilder builder) {
        this.brand = builder.getBrand();
        this.model = builder.getModel();
        this.price = builder.getPrice();
        this.id = builder.getID();

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

        public PenBuilder(String brand, String model, double price) {
            this.brand = brand;
            this.model = model;
            this.price = price;
            this.id = UUID.randomUUID();
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
            return new Pen(getBrand(), getModel(), getPrice());
        }

        public UUID getID() {
            return id;
        }

        public void setID(UUID id) {
            this.id = id;
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

    }

    public UUID getID() {
        return id;
    }

    public void setID(UUID id) {
        this.id = id;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
