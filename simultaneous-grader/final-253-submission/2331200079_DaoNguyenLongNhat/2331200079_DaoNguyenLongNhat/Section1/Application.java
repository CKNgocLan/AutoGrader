//package Section1;

import java.util.UUID;

public class Application {
    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        PenFactory ballpointPenFactory = new BallpointPenFactory();
        PenFactory fountainPenFactory = new FountainPenFactory();

        Client ballpointPenClient = new Client(ballpointPenFactory, sailor, "TUZU Forge", Color.GREY, 65);
        Client fountainPenClient = new Client(fountainPenFactory, concopens, "Golden Lotus", Color.RED , 645.27);
    }
}
