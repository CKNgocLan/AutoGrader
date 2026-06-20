
import java.util.UUID;

public class Pen {
    private UUID id;
    private String brand;
    private String model;
    private double price;

    public Pen(PenBuilder penBuilder) {
        this.id = penBuilder.id;
        this.brand = penBuilder.brand;
        this.model = penBuilder.model;
        this.price = penBuilder.price;
    }

    public static class PenBuilder {
        private UUID id;
        private String model;
        private String brand;
        private double price;

        public PenBuilder setID() {
            this.id = UUID.randomUUID();
            return this;
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

    public UUID getID() {
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

    @Override
    public String toString() {
        return String.format("ID = %s, Brand = %s, Model = %s. Price = %.1f", getID(), getBrand(), getModel(), getPrice());
    }
}
