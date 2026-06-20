public class Application {
    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        BallpointPenFactory ballpointPenFactory = new BallpointPenFactory();
        FountainPenFactory fountainPenFactory = new FountainPenFactory();

        Pen sailor1 = ballpointPenFactory.createPen(sailor, "TUZU Forge", Color.RED, 65);
        Pen concopen = fountainPenFactory.createPen(concopens, "Golden Lotus", Color.RED, 645.27);
        Client ballpointPenClient = new Client(sailor1);
        Client fountainPenClient = new Client(concopen);
        
        System.out.println(ballpointPenClient.getPen().getDescription());
        System.out.println(fountainPenClient.getPen().getDescription());
    }
}
