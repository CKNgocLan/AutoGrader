
import java.util.UUID;

public class Pen {

    private UUID id;
    private String brand;
    private String model;
    private double price;

    public Pen(UUID id) {
        this.id = UUID.randomUUID();
    }

    public Pen(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Pen(PenBuilder builder) {
    }

    static class PenBuilder {

        private UUID id;
        private String brand;
        private String model;
        private double price;

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

    }

    public Pen build() {
        return new Pen(brand, model, price);
    }

    @Override
    public String toString() {
        return "Pen [id=" + id + ", brand=" + brand + ", model=" + model + ", price=" + price + ", getClass()="
                + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

}
