import java.util.UUID;
import java.util.*;

public class Pen {
    public Pen(PenBuilder Builder) {
        this.id = UUID.randomUUID();
        this.brand = Builder.brand;
        this.model = Builder.model;
        this.color = Builder.color;
        this.price = Builder.price;
    }

    private UUID id;
    private Brand brand;
    private String model;
    private Color color;

    private double price;

    public static class PenBuilder {
        private Brand brand;
        private String model;
        private Color color;
        private double price;

        public PenBuilder setBrand(Brand brand) {
            this.brand = brand;
            return this;
        }

        public PenBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public PenBuilder setColor(Color color) {
            this.color = color;
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

    @Override
    public String toString() {
        return "Pen{" +
                "id=" + id +
                ", brand=" + brand +
                ", model='" + model + '\'' +
                ", color=" + color +
                ", price=" + price +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
