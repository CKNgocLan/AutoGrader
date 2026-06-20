
public class Application {
    public static void main(String[] args) {

        Pen ballpointPen = Pen.PenBuilder.newInstance()
                .setBrand("Platinum").setModel("BNB-5000").setPrice(58.93).build();

        Pen fountainPen = Pen.PenBuilder.newInstance()
                .setBrand("Montblanc").setModel("LeGrand").setPrice(1145).build();

        System.out.println(ballpointPen);
        System.out.println(fountainPen);
    }
}
