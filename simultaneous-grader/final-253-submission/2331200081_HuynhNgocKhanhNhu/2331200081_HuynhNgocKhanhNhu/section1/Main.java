public class Main {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Client client = new Client();
        PenFactory ballpointPen = new BallpointPenFatory();
        PenFactory fountainPen = new FountainPenfactory();
        Brand brand1 = ballpointPen.createPen("sailor", "TUZU Forge", GREY, 65);
        Brand brand2 = fountainPen.createPen("concopens", "Golden Lotus", RED, 645.27);

        pen.addBrand(brand1);
        pen.addBrand(brand2);

        Client c1 = new Client("ballpointPen");
        Client c2 = new Client("fountainPen");

        pen.addClient(c1);
        pen.addClient(c2);

        System.out.println();
    }
}
