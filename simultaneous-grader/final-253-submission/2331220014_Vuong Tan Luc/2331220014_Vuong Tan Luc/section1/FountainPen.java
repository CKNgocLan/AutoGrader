
public class FountainPen extends Pen {
    public FountainPen(Brand brand, String model, Color color){
        super(brand, model, color);
    }

    public String getDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append("fountainPen");
        return sb.toString();
    }
}
