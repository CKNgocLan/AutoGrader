
public class FountainPen extends Pen {

    public FountainPen(Brand brand, String modle, Color color, double price) {
        super(brand, modle, color,price);
    }

    @Override
    public String getDescription() {
        return brand.getName() + " - " + modle +" - "+ color +" - "+price;
    }
}
