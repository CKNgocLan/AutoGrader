
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Pen ballpointPen = new Pen("Platinum", "BNB-5000", 57.93);
        Pen fountainPen = new Pen("Montblanc", "LeGrand", 1145);

        Pen ballpointpen = new Pen(new Pen.PenBuilder());
        Pen fountainpen = new Pen(new Pen.PenBuilder());

        List<Integer> pens = new ArrayList();
        pens.add(ballpointpen.Pen("Platinnum", "BNB-5000", 57.93));
        pens.add(fountainpen.Pen("Montblanc", "LeGrand", 1145));

        for (Pen pen : pens) {
            System.out.println(pen);
        }

    }
}
