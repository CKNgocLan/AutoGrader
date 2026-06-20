import java.util.UUID;

public class Pen {

    private UUID id;
    private String brand;
    private String model;
    private double price;

    private Pen(PenBuilder builder) {
        this.id = UUID.randomUUID();
        this.brand = builder.brand;
        this.model = builder.model;
        this.price = builder.price;
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
        return "Brand " + this.brand + ",Model " + this.model + ", Price " + this.price + "";
    }

    public static class PenBuilder {
        private UUID id;
        private String brand;
        private String model;
        private double price;

        public PenBuilder() {

        }

        public PenBuilder setId(UUID id) {
            this.id = id;
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

}
