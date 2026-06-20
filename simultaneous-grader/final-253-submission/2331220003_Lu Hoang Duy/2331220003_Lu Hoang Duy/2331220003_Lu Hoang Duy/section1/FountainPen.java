
public class FountainPen extends Pen {

    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);

    }

    @Override
    public String toString() {
        return "Fountain Pen :" + FountainPen.class + "-" + FountainPen.class + "-" + FountainPen.class + "-"
                + FountainPen.class;
    }

}
