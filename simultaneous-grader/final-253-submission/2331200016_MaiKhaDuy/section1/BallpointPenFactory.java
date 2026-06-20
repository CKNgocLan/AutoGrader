package section1;

import java.util.UUID;

public class BallpointPenFactory implements PenFactory{
    @Override
    public Pen createPen() {
        Brand brand = new Brand("sailor");
        Pen pen = new BallpointPen(brand, "TUZU Forge", Color.GREY);
        return pen;
    }


}
