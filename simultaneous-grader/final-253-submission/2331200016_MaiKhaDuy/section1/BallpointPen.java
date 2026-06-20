package section1;

public class BallpointPen extends Pen{

    public BallpointPen(Brand brand, String model, Color color) {
        super(brand, model, color);
        //TODO Auto-generated constructor stub
    }
        public String getDescription() {
        return "BallpointPen:  <" +brand.getName() +"> - <"+ getModel()+"> - <"+getColor()+">";
    }

}
