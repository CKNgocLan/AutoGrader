import java.util.UUID;

public abstract class Pen {
    private UUID id;
    private String brand;
    private String model;
    private double price;

    public Pen(PenBuilder builder) {
        this.id = builder.id;
        this.brand = builder.brand;
        this.model = builder.model;
        this.price = builder.price;

    }

    @Override
    public String toString() {
        return brand + " " + model + " " + price;
    }

    public static class PenBuilder {
        private UUID id;
        private String brand;
        private String model;
        private double price;

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

        public class PenTypes {
            public static final String ballpointPen = "ballpointPen";
            public static final String fountainPen = "fountainPen";

        }

        public Pen build() {
            if (PenTypes.ballpointPen.equalsIgnoreCase("ballpointPen")) {
                return;
            } else {
                return;
            }
        }
    }
}
