import java.util.UUID;

public abstract class Pen {
    private static final String Description = null;
    protected UUID id;
    protected String brand;
    protected String model;
    protected double price;

    protected Pen(PenBuilder builder) {
        this.brand = brand;
        this.id = id;
        this.model = model;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + "-" + brand + "-" + model + "-" + price;
    }

    public static class PenBuilder {
        protected UUID id;
        protected String brand;
        protected String model;
        protected double price;

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
            if (this.brand.equals("ballpointPen")) {
                return new ;
            } else if (this.brand.equals("fountainPen")) {
                return new ;
            }
        }
    }

}
