package Section2;


public class Applictaion {
    public static void main(String[] args) {
        Pen ballpointPen = new Pen.PenBuilder()
                .setBrand("Platinum")
                .setModel("BNB_5000")
                .setPrice(57.93).build();
        Pen fountainPen = new Pen.PenBuilder()
                .setBrand("Montblanc")
                .setModel("LeGrand")
                .setPrice(1145).build();
        ballpointPen.displayInfor();
        fountainPen.displayInfor();

    }

}
