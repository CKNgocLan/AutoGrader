public class FountainPen extends Pen{

    public FountainPen(String name, Brand brand, double price) {
        super(name, brand, price);
        setType(PenType.FOUNTAIN);
    }

    @Override
    public double getTax() {
        return Tax._10;
    }

}
