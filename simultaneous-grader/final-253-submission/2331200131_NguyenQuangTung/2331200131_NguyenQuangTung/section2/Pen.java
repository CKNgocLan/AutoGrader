import java.util.*;

public class Pen {
    private UUID id;
    private String brand;
    private String model;
    private double price;

    private Pen(PenBuilder builder) {
        this.id = UUID.randomUUID();
        this.brand = builder.brand;
        this.model = builder.model;
        this.price = builder.price;
    }

    @Override
    public String toString() {
        return id + " " + brand + " " + model + " " + price + "\n";
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

        public void setID(UUID id) {
            this.id = id;
        }
    }

    public UUID getID() {
        return id;
    }

    public void setID(UUID id) {
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

    public static abstract class PenFactory {
        public abstract Pen.PenBuilder createPenBuilder();
    }

    public static class BallpointPenFactory extends PenFactory {
        @Override
        public Pen.PenBuilder createPenBuilder() {
            return new Pen.PenBuilder().setBrand("Platinum")
                    .setModel("BNB-5000")
                    .setPrice(57.93);
        }
    }

    public static class FountainPenFactory extends PenFactory {
        @Override
        public Pen.PenBuilder createPenBuilder() {
            return new Pen.PenBuilder().setBrand("Montblanc")
                    .setModel("LeGrand")
                    .setPrice(1145);
        }
    }

}
