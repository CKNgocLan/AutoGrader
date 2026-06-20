import java.util.UUID;

public class Pen {

    private UUID id;
    private String brand;
    private String model;
    private double price;

    public Pen() {
    }

    public Pen(PenBuilder builder) {
        this.brand = builder.brand;
        this.model = builder.model;
        this.price = builder.price;
        this.id = UUID.randomUUID();
        this.price = builder.price;
    }

    @Override
    public String toString() {
        return "%s %s %s %s".formatted(this.getID(), this.getBrand(), this.getModel(), this.getPrice());
    }

    public static class PenBuilder {
        private UUID id;
        private String brand;
        private String model;
        private double price;

        public PenBuilder() {
        }

        public static PenBuilder newInstance() {
            return new PenBuilder();
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
