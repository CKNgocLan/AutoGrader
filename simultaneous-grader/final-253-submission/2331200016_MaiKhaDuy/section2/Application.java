
import java.util.*;

public class Application {
    public static void main(String[] args) {
        Pen ballpointpen = new Pen.PenBuilder()
                .setBrand("Platinum")
                .setModel("BNB-5000")
                .setPrice(57.93)
                .build();


        Pen fountainpen = new Pen.PenBuilder()
                .setBrand("Montblanc")
                .setModel("LeGrand")
                .setPrice(1145)
                .build();

        System.out.println(ballpointpen);
        System.out.println(fountainpen);
    }
}
