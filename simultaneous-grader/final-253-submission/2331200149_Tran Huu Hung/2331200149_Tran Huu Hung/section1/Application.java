

public class Application {
    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        BallpointPenFactory ballpointPenFactory = new BallpointPenFactory();
        FountainPenFactory fountainPenFactory = new FountainPenFactory();

        Client ballpointPenClient = new Client(ballpointPenFactory);
        Pen ballpointPen = ballpointPenClient.getPen(sailor, "TUZU Forge", Color.SILVER, 65.0);

        Client fountainPenClient = new Client(fountainPenFactory);
        Pen foutainPen = fountainPenClient.getPen(concopens, "TUZU Forge", Color.RED, 645.27);

        System.out.println(ballpointPen);
        System.out.println(foutainPen);
    }
}
