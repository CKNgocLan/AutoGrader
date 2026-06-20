public class Application {
    public static void main(String[] args) {
            Pen ballpointPen = new Pen.PenBuilder("Platinum", "BNB-5000", 57.93).build();
            Pen fountainPen = new Pen.PenBuilder("Montblanc", "LeGrand", 1145).build();
            System.out.println(ballpointPen);
            System.out.println(fountainPen);

        }
}
