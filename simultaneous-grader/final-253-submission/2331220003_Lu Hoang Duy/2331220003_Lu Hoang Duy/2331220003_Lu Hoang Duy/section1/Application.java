
public class Application {
    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");
        BallpointPenFactory ballpointPenFactory = null;
        FountainPenFactory fountainPenFactory = null;
        Client ballpointPenClient = new Client(ballpointPenFactory);
        Pen ballPen = ballpointPenClient.getPen();
        Client fountainPenClient = new Client(fountainPenFactory);
        Pen fountainPen = fountainPenClient.getPen();
        System.out.println(ballpointPenClient);
        System.out.println(fountainPenClient);
    }
}
