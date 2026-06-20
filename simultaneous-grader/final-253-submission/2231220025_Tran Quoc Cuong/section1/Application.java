import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        PenFactory ballpointPenFactory = new BallpointPenFactory();
        PenFactory fountainPenFactory = new FountainPenFactory();

        List<Pen> pens = new ArrayList<>();

        pens.add(ballpointPenFactory.createPen(sailor, "TUZU Forge", Color.GREY, 65));
        pens.add(fountainPenFactory.createPen(concopens, "Golden Lostus", Color.RED, 645.27));

        for (Pen pen : pens) {
            System.out.println(pen.getDescription());
        }
    }
}
