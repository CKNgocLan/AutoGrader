public class Application {
    public static void main(String[] args) throws Exception {
        Pen ballpointPen = new Pen.PenBuilder()
                .setBrand("Platinum")
                .setModel("BNB-5000")
                .setPrice(57.93)
                .build();
        System.out.println(ballpointPen);

        Pen fountainPen = new Pen.PenBuilder()
                .setBrand("Montblanc")
                .setModel("LeGrand")
                .setPrice(1145)
                .build();
        System.out.println(fountainPen);
    }
}
