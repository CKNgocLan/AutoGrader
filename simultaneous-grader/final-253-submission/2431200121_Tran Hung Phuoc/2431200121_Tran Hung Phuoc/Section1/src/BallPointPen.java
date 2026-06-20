public class BallPointPen extends Pen{
    public BallPointPen(Brand brand, String model, Color color, double price){
        super(brand, model, color);
        super.setPrice(price);
    }

    @Override
    public String getDescription(){
        return "Ballpoint Pen: " + this.getBrand() + this.getModel() + this.getColor() + this.getPrice(); 
    }
}
