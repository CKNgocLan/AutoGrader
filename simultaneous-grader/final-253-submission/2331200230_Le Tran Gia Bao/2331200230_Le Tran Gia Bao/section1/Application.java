
public class Application {
    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        PenFactory ballpointPenFactory = new BallpointPenFactory();
        PenFactory fountainPenFactory = new FountainPenFactory();

        Client ballpointPenClient = new Client(ballpointPenFactory, sailor, "TUZU Forge", Color.GREY, 65.0);
        // Client fountainPenClient = new Client(fountainPenFactory, concopens,
        // model:"Golden Lotus", Color.RED, 645.27 );

        System.out.println(ballpointPenClient.getPen().getDescription());
        // System.out.println(fountainPenClient.getPen().getDescription());
    }
}
