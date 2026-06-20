package section1;

public class FoutainPen extends Pen{

    public FoutainPen(Brand brand, String model, Color color) {
        super(brand, model, color);
        //TODO Auto-generated constructor stub
    }
    public String getDescription() {
        return "FoutainPen: <" +brand.getName() +"> - <"+ getModel()+"> - <"+getColor()+">";
    }

}
