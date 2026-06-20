package Section2;

import java.util.UUID;

public class Pen {
    private UUID id;
    private String brand;
    private String model;
    private double price;

    public Pen(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.id = UUID.randomUUID();
    }

    public static class PenBuilder {
        private UUID id;
        private String brand;
        private String model;
        private double price;


        public PenBuilder setBrand(String Brand) {
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
            return new Pen(brand, model, price);
        }

        public void displayInfor() {
            System.out.println(id + " - " + brand + " - " + model + " - " + price);
        }
        @Override
        public String toString(){
            return id + " - " + brand + " - " + model + " - " + price;
        }

       
    }

    public void displayInfor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayInfor'");
    }


}
