import java.util.UUID;

public abstract class Pen {
    protected UUID id;
    protected String brand;
    protected String model;
    protected double price;
    public Pen(PenBuilder builder) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }
    public static class PenBuilder{
        protected UUID id;
        protected String brand;
        protected String model;
        protected double price;
        public PenBuilder setBrand(String brand){
            this.brand=brand;
            return this;
        }
        public PenBuilder setPrice(double price){
            this.price=price;
            return this;
        }
        public PenBuilder setModel(String model){
            this.model=model;
            return this;
        }
        public PenBuilder build(){
            return this;
        }
    }
    @Override
    public String toString() {
        return "Pen [brand=" + brand + ", model=" + model + ", price=" + price + "]";
    }
    
    
}
