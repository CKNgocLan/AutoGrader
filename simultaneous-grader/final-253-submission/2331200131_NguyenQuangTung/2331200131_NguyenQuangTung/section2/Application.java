public class Application {
    public static void main(String[] args) throws Exception {

        Pen.PenFactory ballpointPenFactory = new Pen.BallpointPenFactory();
        Pen.PenFactory fountainPenFactory = new Pen.FountainPenFactory();

        Pen ballpointPen = ballpointPenFactory.createPenBuilder().build();
        Pen fountainPen = fountainPenFactory.createPenBuilder().build();

        System.out.println(ballpointPen);
        System.out.println(fountainPen);
    }
}