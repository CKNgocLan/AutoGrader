
public class Application {
    public static void main(String[] args) {
        Pen ballpointPen = new Pen.PenBuilder()
                .setBrand("Platinum")
                .setModel("BNB-5000")
                .setPrice(57.93)
                .build();
        System.out.println(ballpointPen);

        Pen fountaitPen = new Pen.PenBuilder()
                .setBrand("Montblanc")
                .setModel("LeGrand")
                .setPrice(1145)
                .build();
        System.out.println(fountaitPen);

    }
}
