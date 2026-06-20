public class Application {
    public static void main(String[] args) throws Exception {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        Client ballpointPenClient = new Client(ballpointPenClient, sailor, "TUZU Forge", Color.GREY, 65);
        Client fountainPenClient = new Client(fountainPenClient, concopens, "Golden Lotus", Color.RED, 645.27);

        System.out.println(ballpointPenClient);
        System.out.println(fountainPenClient);
    }
}
