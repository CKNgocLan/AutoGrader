import java.util.UUID;

public class Pen {
    private UUID id;
    private String brand;
    private String model;
    private double price;

    private Pen(PenBuilder builder) {
        this.id = builder.id;
        this.brand = builder.brand;
        this.model = builder.model;
        this.price = builder.price;
    }

    
    @Override
    public String toString(){
        return "ID: %s \n Brand: %s \n Model: %s \n Price: %.2f".formatted(this.id, this.brand, this.model, this.price);
    }

    //Builder
    public static class PenBuilder{
        //Attributes
        private UUID id;
        private String brand;
        private String model;
        private double price;

        //Constructor - ID need to be defined by random method.
        public PenBuilder() {
            this.id = UUID.randomUUID();
        }


        //attribute methods
        public PenBuilder setBrand(String brand){
            this.brand = brand;
            return this;
        }

        public PenBuilder setModel(String model){
            this.model = model;
            return this;
        }
        
        public PenBuilder setPrice(double price){
            this.price = price;
            return this;
        }

        //build method
        public Pen build(){
            return new Pen(this);
        }
    }

    public UUID getID() {
        return id;
    }


    public String getBrand() {
        return brand;
    }


    public String getModel() {
        return model;
    }


    public double getPrice() {
        return price;
    }
}
