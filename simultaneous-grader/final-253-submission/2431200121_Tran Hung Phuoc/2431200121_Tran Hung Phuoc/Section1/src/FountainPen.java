public class FountainPen extends Pen{
    public FountainPen(Brand brand, String model, Color color, double price){
        super(brand, model, color);
        super.setPrice(price);
    }
    @Override
    public String getDescription(){
        return "Fountain Pen: " + this.getBrand() + this.getModel() + this.getColor() + this.getPrice(); 
    }
}
