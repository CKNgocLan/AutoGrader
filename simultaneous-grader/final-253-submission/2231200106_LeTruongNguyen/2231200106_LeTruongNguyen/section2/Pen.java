

import java.util.UUID;

public class Pen {
    private UUID id;
    private String brand;
    private String model;
    private double price;

    public Pen(PenBuilder builder) {

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
            return new Pen(this);
        }
    }

    public class BallpointPen extends Pen {
        public BallpointPen(PenBuilder builder) {
            super(builder);
        }
    }

    public class FountainPen extends Pen {
        public FountainPen(PenBuilder builder) {
            super(builder);
        }
    }

    public class ColoredPen extends Pen {
        private String color;

        public ColoredPen(PenBuilder builder, String color) {
            super(builder);
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

    @Override
    public String toString() {
        return "Pen [id=" + id + ", brand=" + brand + ", model=" + model + ", price=" + price + "]";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public class Client {
        protected Pen pen;

        public Client(Pen pen) {
            this.pen = pen;
        }

        public Pen getPen() {
            return pen;
        }

    }
}
