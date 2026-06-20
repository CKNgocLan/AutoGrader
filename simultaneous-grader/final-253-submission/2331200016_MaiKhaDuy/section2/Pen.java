
import java.util.*;

public class Pen {
    private UUID id;
    private String brand;
    private String model;
    private double price;

    private Pen(PenBuilder builder) {
        this.brand = builder.brand;
        this.model = builder.model;
        this.price = builder.price;
    }

    @Override
    public String toString() {
        return "Pen [brand=" + brand + ", model=" + model + ", price=" + price + "]";
    }

    public static class PenBuilder {
        private String brand;
        private String model;
        private double price;

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
