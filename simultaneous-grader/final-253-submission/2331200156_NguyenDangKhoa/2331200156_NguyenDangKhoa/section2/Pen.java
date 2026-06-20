
import java.util.UUID;

public class Pen {

    protected UUID id = UUID.randomUUID();
    protected String brand;
    protected String model;
    protected double price;

    public Pen(PenBuilder builder) {
        this.brand = builder.brand;
        this.model = builder.model;
        this.price = builder.price;
    }

    @Override
    public String toString(){
        return "ID: "+id+", Brand: "+brand+", Model: "+model+", Price: "+price;
    }

    public static class PenBuilder {

        protected UUID id = UUID.randomUUID();
        protected String brand;
        protected String model;
        protected double price;

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
