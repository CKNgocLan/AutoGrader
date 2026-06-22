
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        BallpointPenFactory ballpointPenFactory = new BallpointPenFactory();
        FountainPenFactory fountainPenFactory = new FountainPenFactory();

        // List<Integer> pens = new ArrayList();
        // pens.add(ballpointPenFactory.createPen("sailor", "TUZU Forge", Color.GREY, 65));
        // pens.add(fountainPenFactory.createPen("concopens", "Golden Lotus", Color.RED, 645.27));

        // for (Pen pen : pens) {
        //     System.out.println(pen);
        // }

    }
}
