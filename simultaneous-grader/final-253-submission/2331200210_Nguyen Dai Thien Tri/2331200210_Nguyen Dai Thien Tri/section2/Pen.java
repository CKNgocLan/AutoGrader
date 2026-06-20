import java.util.UUID;

public class Pen {
    private UUID id;
    private String brand;
    private String model;
    private double price;

    public UUID getId() {
        return UUID.randomUUID();
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

    public Pen(PenBuilder builder) {

    }

    @Override
    public String toString() {
        return "ID: " + getId() + " ||Brand: " + getBrand() + " ||Model " + getModel() + " ||Price " + getPrice();
    }

    public static class PenBuilder {
        private UUID id;
        private String brand;
        private String model;
        private double price;

        public PenBuilder setBrand(String brand) {

        }

        public PenBuilder setModel(String model) {

        }

        public PenBuilder setPrice(double price) {

        }

        public Pen build() {

        }

    }
}
