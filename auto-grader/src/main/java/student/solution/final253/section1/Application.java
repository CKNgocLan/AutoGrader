package student.solution.final253.section1;

public class Application {
    public static void main(String[] args) throws Exception {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        BallpointPenFactory ballpointPenFactory = new BallpointPenFactory();
        FountainPenFactory fountainPenFactory = new FountainPenFactory();

        Client ballpointPenClient = new Client(ballpointPenFactory, sailor, "TUZU Forge", Color.GREY, 65);
        Client fountainPenClient = new Client(fountainPenFactory, concopens, "Golden Lotus", Color.RED, 645.27);

        System.out.println(ballpointPenClient.getPen().getDescription());
        System.out.println(fountainPenClient.getPen().getDescription());
    }
}
