
public class Application {
    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");
        PenFactory ballpointPenFactory = new BallpointPenFactory();
        PenFactory fountaiPenFactory = new FountainPenFactory();
        Client ballpointPenClient = new Client(ballpointPenFactory, sailor, " TUZU Forge", Color.GREY, 65);
        System.out.println(ballpointPenClient.getPen().getDescripton());
    }

}
