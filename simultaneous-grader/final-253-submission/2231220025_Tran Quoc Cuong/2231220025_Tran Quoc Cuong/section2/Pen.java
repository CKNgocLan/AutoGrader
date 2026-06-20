import java.util.UUID;

abstract class Pen {
    public UUID id;
    public String brand;
    public String model;
    public double price;

    private Pen (String model, String brand, double price) {
        this.id = UUID.randomUUID();
        this.model = model;
        this.brand = brand;
        this.price = price;
    }

    public Pen (PenBuilder builder) {

    }

    public UUID getID() {
        return id;
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

    public String toString() {
        return "Pen: " + brand + "-" + model + "-" + price;
    }

    public static class PenBuilder {
        public String model;
        public String brand;
        public double price;

        public PenBuilder setBrand (String brand) {
            this.brand = brand;
            return this;
        }

        public PenBuilder setModel (String model) {
            this.model = model;
            return this;
        }

        public PenBuilder setPrice (double price) {
            this.price = price;
            return this;
        }

        public Pen build() {
            validate();
            return new Pen(brand, model, price) {
            };
        }

        public void validate() {
            if (brand == null) {
                throw new IllegalArgumentException("Pen brand is required");
            }
            if (model == null) {
                throw new IllegalArgumentException("Pen model is required");
            }
            if (price < 0) {
                throw new IllegalArgumentException("Pen price must be greater than 0");
            }
        }

        public String toString() {
            return "Pen: " + brand + "-" + model + "-" + price;
        }
    }
}
