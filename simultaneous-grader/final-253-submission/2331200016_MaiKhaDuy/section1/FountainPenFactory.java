package section1;

public class FountainPenFactory implements PenFactory{

    @Override
    public Pen createPen() {
        Brand brand = new Brand("concopens");
        Pen pen = new BallpointPen(brand, "Golden Lotus", Color.RED);
        return pen;
    }

}
