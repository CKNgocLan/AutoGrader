public class FountainPen extends Pen {
    
    private double price;

    public FountainPen(Brand brand, String model, Color color ,Double price) {
        super(brand, model, color);
        this.price = 20.0;
    }
     @Override
    public String toString(){
    return "%2s ,%2s ,%2s".formatted(getBrand(), getModel() , getColor() , price);
    
}
}
