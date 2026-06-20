
public class Application {
    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        PenFactory fountaiPenFactory = new FountainPenFactory();
        PenFactory ballpointPenFactory = new BallpointPenFactory();

        Client ballpointPenClient = new Client(ballpointPenFactory, sailor, "TUZU Forge", Color.GREY, 65D);
        System.out.println(ballpointPenClient.getPen().getDescription());

        Client fountainPenlient = new Client(fountaiPenFactory, concopens, "Golden Lotus", Color.RED, 645.27);
        System.out.println(fountainPenlient.getPen().getDescription());
    }
}
