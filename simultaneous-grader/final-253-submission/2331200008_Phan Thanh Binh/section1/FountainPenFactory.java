

public class FountainPenFactory implements PenFactory {
    @Override
    public Pen createPen(Brand brand, String modle, Color color, double price){
        return new FountainPen(brand, modle, color, price);
    }
}
