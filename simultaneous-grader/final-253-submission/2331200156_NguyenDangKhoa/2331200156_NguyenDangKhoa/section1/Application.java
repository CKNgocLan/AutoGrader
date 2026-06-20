public class Application {
    public static void main(String[] args) throws Exception {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        PenFactory ballpointPenFactory = new BallpointPenFactory();
        PenFactory fountainPenFactory = new FountainPenFactory();

        Pen ballpointPenClient = ballpointPenFactory.createPen(sailor, "TUZU Forge", Color.Grey, 65.0);
        Pen fountainPenClient = fountainPenFactory.createPen(concopens, "Golden Lotus", Color.Red, 645.27);

        System.out.println(ballpointPenClient.getDescription());
        System.out.println(fountainPenClient.getDescription());
    }
}
