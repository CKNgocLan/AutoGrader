import java.util.UUID;

public class Pen {
    private UUID id;
    private String brand;
    private String model;
    private double price;

    public Pen(PenBuilder penBuilder) {
        this.id = UUID.randomUUID();
    }

    public static class PenBuilder {
        private String brand;
        private String model;
        private double price;

        public PenBuilder() {
        }

        public Pen build() {
            Pen pen = new Pen(this);
            pen.setBrand(brand);
            pen.setModel(model);
            pen.setPrice(price);
            return pen;
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
    }

    public UUID getID() {
        return id;
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
        return "ID: %s | Brand: %s | Model: %s | Price: %s".formatted(id, brand, model, price);
    }

}
