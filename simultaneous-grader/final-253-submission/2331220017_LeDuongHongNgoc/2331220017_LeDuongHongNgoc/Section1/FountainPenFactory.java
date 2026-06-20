public class FountainPenFactory extends PenFactory{
    
    @Override
    public Pen createPen(Brand brand, String model, Color color, double price){
        return new FountainPen(model, brand, color, price);
    }


}
