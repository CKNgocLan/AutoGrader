import java.util.UUID;

public class Pen {
    private UUID id;
    private String brand;
    private String model;
    private double price;

    public Pen(UUID id, String brand, String model, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Pen(PenBuilder builder) {
        // TODO
    }

    @Override
    public String toString() {
        return "ID= %s, Brand= %s, Model= %s, Price= %.2f".formatted(id, brand, model, price);
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
            Pen pen;
            if (brand == "ballpointPen") {
                // TODO
            } else if (brand == "fountainPen") {
                // TODO
            } else
                throw new IllegalStateException("Please type a valid brand");
        }
        // return ...
    }
}
