import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Pen.PenBuilder ballpointPen = new Pen.PenBuilder().setBrand("Platinum").setModel("BNB-5000").setPrice(57.93);
        Pen.PenBuilder fountainPen = new Pen.PenBuilder().setBrand("Montblanc").setModel("LeGrand").setPrice(1145);

        List<Pen.PenBuilder> pens = new ArrayList<>();
        pens.add(ballpointPen);
        pens.add(fountainPen);

        for (Pen.PenBuilder pen : pens) {
            System.out.println(pen.toString());
        }
    }
}
