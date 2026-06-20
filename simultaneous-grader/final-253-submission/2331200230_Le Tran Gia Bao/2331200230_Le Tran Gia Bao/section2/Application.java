
public class Application {
    public static void main(String[] args) {
        Pen ballpointPen = new Pen.PenBuilder()
                .setBrand("Platinum")
                .setModel("BNB-5000")
                .setPrice(57.93)
                .build();

        Pen fountainPen = new Pen.PenBuilder()
                .setBrand("Montblanc")
                .setModel("LeGrand")
                .setPrice(1145.0)
                .build();

        System.out.println(ballpointPen.toString());
        System.out.println(fountainPen.toString());
    }
}
