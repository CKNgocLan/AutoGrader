public class Application {
    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        PenFactory ballpoiPenFactory = new BallpointPenFactory();
        PenFactory fountaiPenFactory = new FountainPenFactory();

        Client ballpointPenClient = new Client(ballpoiPenFactory.createPen(sailor, "TUZU Forge", Color.GREY, 65));
        Client fountainPenClient = new Client(fountaiPenFactory.createPen(concopens, "Golden Lotus", Color.RED, 645.27));

        Pen ballpointPen = ballpointPenClient.getPen();
        System.out.println(ballpointPen.getDiscription());
        Pen fountainPen = fountainPenClient.getPen();
        System.out.println(fountainPen.getDiscription());
    }

}
