public class FountainPenFactory implements PenFactory {
    @Override
    public Pen createPen(brand brand, String model,Color color, double price){
        return new fountainPen(brand brand, String model,Color color, double price);
    }
    

}



