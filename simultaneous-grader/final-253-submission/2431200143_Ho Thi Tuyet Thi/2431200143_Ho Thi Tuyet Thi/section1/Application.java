public class Application {
    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        PenFactory ballpointPenFactory = new BallpointPenFactory();
        PenFactory fountainPenFactory = new FountainPenFactory();

        Client ballpointClient = new Client(new BallpointPen(sailor, "TUZU Forge", Color.GREY, 65), ballpointPenFactory);
        Client fountainClient = new Client(new FountainPen(concopens, "Golden Lotus", Color.RED, 645.27), fountainPenFactory);

        System.out.println(ballpointClient.getPen().getDescription());
        System.out.println(fountainClient.getPen().getDescription());
    }
}
