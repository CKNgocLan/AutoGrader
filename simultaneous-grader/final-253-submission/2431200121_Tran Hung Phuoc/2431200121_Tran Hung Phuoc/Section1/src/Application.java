public class Application {
    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("Concopens");

        PenFactory ballpointPenFactory = new BallpointPenFactory();
        PenFactory fountainPenFactory = new FountainPenFactory();

        Client ballpointPenClient = new Client(ballpointPenFactory.createPen(sailor, " TUZU Forge ", Color.GREY, 65));
        Client fountainPenClient = new Client(fountainPenFactory.createPen(concopens, " Golden Lotus ", Color.RED, 645.27));

        Pen ballPointPen = ballpointPenClient.getPen();
        System.out.println(ballPointPen);
        Pen fountainPen = fountainPenClient.getPen();
        System.out.println(fountainPen);
    }
}
